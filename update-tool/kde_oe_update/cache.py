"""Local, validated storage for KDE release snapshots and discovery indexes.

No network operations, version selection, or BitBake parsing belong here.
The caller supplies the cache root (normally utils.CACHE_DIR).
"""

import hashlib
import json
import os
import re
import tempfile
from datetime import datetime, timezone
from pathlib import Path

import msgspec
import zstandard as zstd

from .schema import CacheStatus, FamilyStatus, Package, Release, SCHEMA_VERSION, VersionIndex


FAMILY_DIRS = {
    "frameworks": "frameworks",
    "plasma": "plasma",
    "release_services": "release-service",
}
VERSION_PATTERN = re.compile(r"^[0-9]+(?:\.[0-9]+){1,2}$")
SHA256_PATTERN = re.compile(r"^[0-9a-f]{64}$")
MAX_DECOMPRESSED_SIZE = 64 * 1024 * 1024


class CacheError(ValueError):
    """Missing, invalid, incomplete, or incompatible cached data."""


def _utc_timestamp(value: str) -> None:
    if not isinstance(value, str) or not value.endswith("Z"):
        raise CacheError(f"Expected UTC ISO 8601 timestamp ending in Z: {value!r}")
    try:
        parsed = datetime.fromisoformat(value)
    except ValueError as exc:
        raise CacheError(f"Invalid timestamp: {value!r}") from exc
    if parsed.tzinfo is None or parsed.utcoffset().total_seconds() != 0:
        raise CacheError(f"Timestamp must be UTC: {value!r}")


def _now() -> str:
    return datetime.now(timezone.utc).isoformat(timespec="seconds").replace("+00:00", "Z")


def _version(value: str) -> None:
    if not isinstance(value, str) or VERSION_PATTERN.fullmatch(value) is None:
        raise CacheError(f"Invalid KDE version: {value!r}")


def _family(value: str) -> None:
    if value not in FAMILY_DIRS:
        raise CacheError(f"Unknown KDE release family: {value!r}")


def _schema(version: int) -> None:
    if type(version) is not int or version != SCHEMA_VERSION:
        raise CacheError(f"Unsupported cache schema version: {version!r}")


def _validate_release(release: Release) -> None:
    _schema(release.schema_version)
    _family(release.family)
    _version(release.version)
    _utc_timestamp(release.fetched_at)
    if not release.source_url.startswith("https://"):
        raise CacheError("Release source_url must be HTTPS")
    if not release.packages:
        raise CacheError("Cannot store an empty release as complete")
    names = set()
    for package in release.packages:
        if not package.name or package.name in names or "/" in package.name or "\\" in package.name:
            raise CacheError(f"Duplicate or invalid upstream package name: {package.name!r}")
        names.add(package.name)
        if not package.url.startswith("https://") or not package.sha_url.startswith("https://"):
            raise CacheError(f"Invalid archive/checksum URL for {package.name}")
        if package.sha_url != f"{package.url}.sha256":
            raise CacheError(f"Checksum URL does not match archive for {package.name}")
        if SHA256_PATTERN.fullmatch(package.sha) is None:
            raise CacheError(f"Invalid SHA-256 for {package.name}")


def _validate_index(index: VersionIndex) -> None:
    _schema(index.schema_version)
    _utc_timestamp(index.fetched_at)
    for family in FAMILY_DIRS:
        versions = getattr(index, family)
        if len(set(versions)) != len(versions):
            raise CacheError(f"Duplicate discovered versions for {family}")
        for version in versions:
            _version(version)


def _validate_status(status: CacheStatus) -> None:
    _schema(status.schema_version)
    for family, state in status.families.items():
        _family(family)
        if state.versions_checked_at is not None:
            _utc_timestamp(state.versions_checked_at)
        for version in state.discovered_versions:
            _version(version)
        for version, timestamp in state.cached_versions.items():
            _version(version)
            _utc_timestamp(timestamp)


def _atomic_write(path: Path, data: bytes) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    temporary = None
    try:
        with tempfile.NamedTemporaryFile(dir=path.parent, prefix=f".{path.name}.", suffix=".tmp", delete=False) as file:
            temporary = Path(file.name)
            file.write(data)
            file.flush()
            os.fsync(file.fileno())
        temporary.replace(path)
    finally:
        if temporary is not None:
            temporary.unlink(missing_ok=True)


def _encode(value: object) -> bytes:
    return zstd.ZstdCompressor(level=3).compress(msgspec.msgpack.encode(value))


def _decode(path: Path, type_: type):
    try:
        compressed = path.read_bytes()
        raw = zstd.ZstdDecompressor().decompress(compressed, max_output_size=MAX_DECOMPRESSED_SIZE)
        return msgspec.msgpack.decode(raw, type=type_)
    except (OSError, zstd.ZstdError, msgspec.DecodeError, msgspec.ValidationError) as exc:
        raise CacheError(f"Cannot decode cache file {path}: {exc}") from exc


class Cache:
    """Persistence service for one updater cache root."""

    def __init__(self, root: Path):
        self.root = Path(root)

    def release_dir(self, family: str, version: str) -> Path:
        _family(family)
        _version(version)
        return self.root / FAMILY_DIRS[family] / version

    def has_release(self, family: str, version: str) -> bool:
        """Return False for missing/incomplete pairs; reject corrupt existing pairs."""
        directory = self.release_dir(family, version)
        binary = directory / "packages.msgpack.zst"
        manifest = directory / "packages.manifest.json"
        if not binary.is_file() or not manifest.is_file():
            return False
        self.load_release(family, version)
        return True

    def save_release(self, release: Release) -> None:
        _validate_release(release)
        directory = self.release_dir(release.family, release.version)
        binary_data = _encode(release)
        digest = hashlib.sha256(binary_data).hexdigest()
        manifest = {
            "schema_version": SCHEMA_VERSION,
            "family": release.family,
            "version": release.version,
            "fetched_at": release.fetched_at,
            "package_count": len(release.packages),
            "cache_sha256": digest,
            "packages": {package.name: package.sha for package in release.packages},
        }
        _atomic_write(directory / "packages.msgpack.zst", binary_data)
        _atomic_write(directory / "packages.manifest.json", (json.dumps(manifest, indent=2) + "\n").encode("utf-8"))
        # The pair is published and validated before advancing the fill timestamp.
        self.load_release(release.family, release.version)
        status = self.load_status()
        family_status = status.families.setdefault(release.family, FamilyStatus())
        family_status.cached_versions[release.version] = _now()
        self.save_status(status)

    def load_release(self, family: str, version: str) -> Release:
        directory = self.release_dir(family, version)
        binary_path = directory / "packages.msgpack.zst"
        manifest_path = directory / "packages.manifest.json"
        if not binary_path.is_file() or not manifest_path.is_file():
            raise CacheError(f"Incomplete or missing release cache: {directory}")
        try:
            binary_data = binary_path.read_bytes()
            manifest = json.loads(manifest_path.read_text(encoding="utf-8"))
        except (OSError, UnicodeError, json.JSONDecodeError) as exc:
            raise CacheError(f"Cannot read release manifest: {manifest_path}") from exc
        release = _decode(binary_path, Release)
        _validate_release(release)
        if release.family != family or release.version != version:
            raise CacheError(f"Release identity mismatch: {directory}")
        expected = {
            "schema_version": SCHEMA_VERSION,
            "family": family,
            "version": version,
            "fetched_at": release.fetched_at,
            "package_count": len(release.packages),
            "cache_sha256": hashlib.sha256(binary_data).hexdigest(),
            "packages": {package.name: package.sha for package in release.packages},
        }
        if manifest != expected:
            raise CacheError(f"Release manifest and binary snapshot disagree: {directory}")
        return release

    def save_version_index(self, index: VersionIndex, families: tuple[str, ...] | None = None) -> None:
        _validate_index(index)
        selected = tuple(FAMILY_DIRS) if families is None else families
        for family in selected:
            _family(family)
        _atomic_write(self.root / "versions.msgpack.zst", _encode(index))
        status = self.load_status()
        for family in selected:
            family_status = status.families.setdefault(family, FamilyStatus())
            family_status.versions_checked_at = index.fetched_at
            family_status.discovered_versions = list(getattr(index, family))
        self.save_status(status)

    def load_version_index(self) -> VersionIndex:
        path = self.root / "versions.msgpack.zst"
        if not path.is_file():
            raise CacheError(f"Missing discovered version index: {path}")
        index = _decode(path, VersionIndex)
        _validate_index(index)
        return index

    def save_status(self, status: CacheStatus) -> None:
        _validate_status(status)
        _atomic_write(self.root / "last.json", msgspec.json.encode(status) + b"\n")

    def load_status(self) -> CacheStatus:
        path = self.root / "last.json"
        if not path.is_file():
            return CacheStatus()
        try:
            status = msgspec.json.decode(path.read_bytes(), type=CacheStatus)
        except (OSError, msgspec.DecodeError, msgspec.ValidationError) as exc:
            raise CacheError(f"Invalid cache status: {path}") from exc
        _validate_status(status)
        return status
