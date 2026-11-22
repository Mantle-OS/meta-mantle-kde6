"""Dispatch the independent KDE discovery, cache, and generation operations.

Version choice and argument parsing belong to the CLI. This module owns the
ordered workflow and keeps operations independent of the user's cwd.
"""

import json
import os
import re
import tempfile
from datetime import datetime, timedelta, timezone
from pathlib import Path
from typing import Callable

from . import utils
from .cache import Cache, CacheError, FAMILY_DIRS
from .frameworks import Frameworks
from .plasma import Plasma
from .release_services import ReleaseServices
from .schema import SCHEMA_VERSION, VersionIndex


UPDATERS = {
    "framework": Frameworks,
    "plasma": Plasma,
    "release-services": ReleaseServices,
}
DEFAULT_REFRESH_HOURS = 24
_ARCHIVE_VERSION = re.compile(r"-([0-9]+\.[0-9]+\.[0-9]+)\.tar\.xz$")


def _now() -> str:
    return datetime.now(timezone.utc).isoformat(timespec="seconds").replace("+00:00", "Z")


def _numeric_version(value: str) -> tuple[int, ...]:
    return tuple(int(part) for part in value.split("."))


def _selected_updaters(target: str, cache: Cache):
    if target == "all":
        names = tuple(UPDATERS)
    elif target in UPDATERS:
        names = (target,)
    else:
        raise ValueError(f"Unsupported KDE target: {target}")
    return [UPDATERS[name](cache) for name in names]


def _index_or_empty(cache: Cache) -> VersionIndex:
    if (cache.root / "versions.msgpack.zst").is_file():
        return cache.load_version_index()
    return VersionIndex(SCHEMA_VERSION, _now(), [], [], [])


def _discover(cache: Cache, updaters: list, *, force: bool, refresh_hours: int) -> VersionIndex:
    if refresh_hours < 0:
        raise ValueError("refresh_hours cannot be negative")
    index = _index_or_empty(cache)
    status = cache.load_status()
    refreshed = []
    now = datetime.now(timezone.utc)

    for updater in updaters:
        family = updater.base_name
        prior = status.families.get(family)
        checked = None if prior is None else prior.versions_checked_at
        fresh = False
        if checked:
            checked_at = datetime.fromisoformat(checked.replace("Z", "+00:00"))
            fresh = now - checked_at < timedelta(hours=refresh_hours)

        versions = getattr(index, family)
        if not force and fresh and versions:
            print(f"{family}: discovery cache is fresh ({len(versions)} versions)")
            continue

        versions = utils.get_version(family)
        if not versions:
            raise ValueError(f"No upstream release directories found for {family}")
        setattr(index, family, versions)
        refreshed.append(family)
        # Discovery placeholders aren't complete release snapshots.
        for directory in versions:
            cache.release_dir(family, directory).mkdir(parents=True, exist_ok=True)
        print(f"{family}: discovered {len(versions)} release directories")

    if refreshed:
        index.fetched_at = _now()
        cache.save_version_index(index, families=tuple(refreshed))
    return index


def _frameworks_release_versions(directory: str) -> list[str]:
    """Return real full patch versions advertised by a Frameworks directory."""
    listing_url = f"{utils.FRAMEWORKS_URL}{directory}/"
    candidates = set()
    for entry in utils.parse_req(listing_url):
        match = _ARCHIVE_VERSION.search(entry["name"])
        if match and ".".join(match.group(1).split(".")[:2]) == directory:
            candidates.add(match.group(1))
    if not candidates:
        raise ValueError(f"No full Frameworks release versions found in {listing_url}")
    return sorted(candidates, key=_numeric_version)


def _resolve_frameworks_version(directory: str, cache: Cache) -> str:
    """Select the latest real Frameworks patch version in a directory."""
    return _frameworks_release_versions(directory)[-1]


def _validate_choices(index: VersionIndex, updaters: list, selections: dict[str, str]) -> None:
    expected = {updater.base_name for updater in updaters}
    if set(selections) != expected:
        raise ValueError(f"Expected version selections for {sorted(expected)}, got {sorted(selections)}")
    for updater in updaters:
        version = selections[updater.base_name]
        utils.get_source_url(updater.base_name, version)
        directory = updater._indexed_directory(version)
        if directory not in getattr(index, updater.base_name):
            raise ValueError(f"{updater.base_name} version {version} is not in cached discovery index")


def _write_selected_versions(selections: dict[str, str]) -> None:
    """Publish all selected versions in one atomic write, preserving other keys."""
    if utils.VERSION_FILE is None:
        raise RuntimeError("VERSION_FILE has not been initialized by the entry point")
    versions = utils.local_versions()
    versions.update(selections)
    path = utils.VERSION_FILE
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


def run(target: str = "all", *, update_versions: bool = False, update_cache: bool = False,
        generate: bool = False, force: bool = False, refresh_hours: int = DEFAULT_REFRESH_HOURS,
        select_versions: Callable[[VersionIndex, list, Cache], dict[str, str] | str] | None = None,
        cache: Cache | None = None) -> None:
    """Execute requested operations in discovery -> cache -> generation order."""
    if not (update_versions or update_cache or generate):
        raise ValueError("Select --update-versions, --update-cache, or --generate")
    if cache is None:
        if utils.CACHE_DIR is None:
            raise RuntimeError("CACHE_DIR has not been initialized by the entry point")
        cache = Cache(utils.CACHE_DIR)
    updaters = _selected_updaters(target, cache)

    if update_versions:
        _discover(cache, updaters, force=force, refresh_hours=refresh_hours)

    if update_cache:
        try:
            index = cache.load_version_index()
        except CacheError as exc:
            raise CacheError(f"{exc}. Run --update-versions first.") from exc
        if select_versions is None:
            raise ValueError("--update-cache requires an explicit version selection handler")
        selections = select_versions(index, updaters, cache)
        if selections == "all":
            # Cache every indexed release, including every advertised Frameworks
            # patch version. This operation does not select a single Mantle version.
            for updater in updaters:
                family = updater.base_name
                available = sorted(getattr(index, family), key=_numeric_version)
                if not available:
                    raise CacheError(f"No indexed versions for {family}; run --update-versions first")
                for directory in available:
                    versions = (_frameworks_release_versions(directory)
                                if family == "frameworks" else [directory])
                    for version in versions:
                        release = updater.update_cache(version)
                        print(f"{family} {version}: cached {len(release.packages)} packages")
        else:
            _validate_choices(index, updaters, selections)
            _write_selected_versions(selections)
            for updater in updaters:
                version = selections[updater.base_name]
                release = updater.update_cache(version)
                print(f"{updater.base_name} {version}: cached {len(release.packages)} packages")

    if generate:
        # Preflight every family before writing any candidate output.
        for updater in updaters:
            updater.comparison()
        for updater in updaters:
            output = updater.build_final_list()
            print(f"Generated {output}")


__all__ = ["run", "UPDATERS", "DEFAULT_REFRESH_HOURS"]
