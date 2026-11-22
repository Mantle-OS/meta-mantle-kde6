"""Shared workflow for KDE Frameworks, Plasma, and Release Service.

The entry point initializes paths in utils. This module does not own CLI prompts,
release discovery scheduling, or storage formats; those are handled elsewhere.
"""

import json
import os
import tempfile
from datetime import datetime, timezone
from pathlib import Path

from . import utils
from .cache import Cache, CacheError, FAMILY_DIRS
from .manifest import ChecksumEntry, Comparison, compare_checksums, parse_checksums, write_checksums
from .schema import Package, Release, SCHEMA_VERSION


def _now() -> str:
    return datetime.now(timezone.utc).isoformat(timespec="seconds").replace("+00:00", "Z")


def _version_tuple(version: str) -> tuple[int, ...]:
    return tuple(int(part) for part in version.split("."))


class KDE:
    """Family-specific subclasses supply the source and local naming rules."""

    base_name: str = ""
    base_url: str = ""
    override_map: dict[str, str] = {}

    def __init__(self, cache: Cache | None = None):
        if self.base_name not in FAMILY_DIRS:
            raise ValueError(f"Unsupported KDE family: {self.base_name!r}")
        if cache is None:
            if utils.CACHE_DIR is None:
                raise RuntimeError("CACHE_DIR has not been initialized by the entry point")
            cache = Cache(utils.CACHE_DIR)
        self.cache = cache

    @property
    def input_class(self) -> str:
        return utils.INPUT_CLASSES[self.base_name]

    def _selected_version(self) -> str:
        return utils.local_versions()[self.base_name]

    def _indexed_directory(self, version: str) -> str:
        # Frameworks uses major.minor directories for full patch releases.
        return ".".join(version.split(".")[:2]) if self.base_name == "frameworks" else version

    def _check_indexed(self, version: str) -> None:
        # Ensure the selected version belongs to an already discovered directory.
        index = self.cache.load_version_index()
        directory = self._indexed_directory(version)
        if directory not in getattr(index, self.base_name):
            raise ValueError(
                f"{self.base_name} {version} is not in the cached discovery index "
                f"(directory {directory!r}); run --update-versions"
            )

    def check_version(self) -> dict[str, object]:
        """Compare selected version to the local index without mirror access.

        Frameworks' newest *directory* is not necessarily the newest patch
        release. Do not invent a full release version from a directory name.
        """
        index = self.cache.load_version_index()
        available = getattr(index, self.base_name)
        if not available:
            raise CacheError(f"No discovered versions for {self.base_name}; run --update-versions")
        current = self._selected_version()
        newest_directory = max(available, key=_version_tuple)
        current_directory = self._indexed_directory(current)
        return {
            "current": current,
            "latest_directory": newest_directory,
            "newer_directory_available": _version_tuple(newest_directory) > _version_tuple(current_directory),
        }

    def update_version(self, version: str) -> None:
        """Persist an explicitly chosen version, retaining unrelated settings.

        The caller owns prompting. This method does not fetch or infer versions.
        """
        utils.get_source_url(self.base_name, version)  # Validates syntax.
        self._check_indexed(version)
        versions = utils.local_versions()
        versions[self.base_name] = version.strip()
        if utils.VERSION_FILE is None:
            raise RuntimeError("VERSION_FILE has not been initialized by the entry point")
        path = utils.VERSION_FILE
        path.parent.mkdir(parents=True, exist_ok=True)
        temporary = None
        try:
            with tempfile.NamedTemporaryFile(mode="w", encoding="utf-8", dir=path.parent,
                                             prefix=f".{path.name}.", suffix=".tmp", delete=False) as file:
                temporary = Path(file.name)
                json.dump(versions, file, indent=4)
                file.write("\n")
                file.flush()
                os.fsync(file.fileno())
            temporary.replace(path)
        finally:
            if temporary is not None:
                temporary.unlink(missing_ok=True)

    def local_names(self, name: str) -> str:
        """Map an existing local checksum key to its upstream archive name."""
        if name in self.override_map:
            return self.override_map[name]
        if name.endswith("6"):
            return name[:-1]
        return name

    def update_cache(self, version: str | None = None) -> Release:
        """Return cached release or populate one complete immutable snapshot.

        The CLI selects versions; no interactive I/O or discovery takes place.
        Existing valid snapshots require zero network requests.
        """
        version = self._selected_version() if version is None else version.strip()
        utils.get_source_url(self.base_name, version)  # Validate before paths.

        try:
            self._check_indexed(version)
        except CacheError as exc:
            raise CacheError(f"{exc}. Run --update-versions first.") from exc

        if self.cache.has_release(self.base_name, version):
            return self.cache.load_release(self.base_name, version)

        packages = utils.get_packages(self.base_name, version)
        if not packages:
            raise ValueError(f"No upstream packages found for {self.base_name} {version}")

        utils.get_sha(packages)

        release = Release(
            schema_version=SCHEMA_VERSION,
            family=self.base_name,
            version=version,
            source_url=utils.get_source_url(self.base_name, version),
            fetched_at=_now(),
            packages=[Package(**package) for package in packages],
        )

        self.cache.save_release(release)
        return release

    def comparison(self, version: str | None = None) -> Comparison:
        version = self._selected_version() if version is None else version

        # Generate always requires a developer-supplied checksum-only baseline.
        utils.has_input(self.base_name)

        entries = parse_checksums(utils.INPUT_DIR / self.input_class)
        release = self.cache.load_release(self.base_name, version)
        return compare_checksums(entries, release, self.local_names)

    def list_missing(self, comparison: Comparison | None = None) -> list[Package]:
        comparison = self.comparison() if comparison is None else comparison
        return comparison.missing

    def list_removed(self, comparison: Comparison | None = None):
        comparison = self.comparison() if comparison is None else comparison
        return comparison.removed

    def upstream_to_local(self, name: str) -> str:
        matches = [
            local for local, upstream in self.override_map.items()
            if upstream == name
        ]

        if len(matches) > 1:
            raise ValueError(f"Ambiguous upstream package mapping: {name}")

        if matches:
            return matches[0]

        return f"{name}6"

    def build_final_list(self, version: str | None = None) -> Path:
        """Generate existing and missing checksum-only candidates."""
        version = self._selected_version() if version is None else version
        comparison = self.comparison(version)

        if utils.OUTPUT_DIR is None:
            raise RuntimeError("OUTPUT_DIR has not been initialized by the entry point")

        directory = utils.OUTPUT_DIR / FAMILY_DIRS[self.base_name] / version
        target = directory / self.input_class
        missing_target = directory / f"missing-{FAMILY_DIRS[self.base_name]}.bbclass"

        missing_entries = [
            ChecksumEntry(key=self.upstream_to_local(package.name), sha=package.sha)
            for package in comparison.missing
        ]

        for package in comparison.missing:
            print(f"MISSING local checksum/recipe: {package.name} ({package.sha})")

        for entry in comparison.removed:
            print(f"REMOVED upstream; retaining historical checksum: {entry.key} ({entry.sha})")

        write_checksums(target, comparison.entries)

        if missing_entries:
            write_checksums(missing_target, missing_entries)
        else:
            missing_target.unlink(missing_ok=True)

        return target
