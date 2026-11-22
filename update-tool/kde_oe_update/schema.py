"""Typed data contracts for KDE release discovery and cached snapshots.

This module defines data only. Encoding, compression, validation, and I/O
belong to the cache and workflow modules.
"""

import msgspec


SCHEMA_VERSION = 1


class Package(msgspec.Struct):
    """One upstream source archive with its verified SHA-256 checksum."""

    name: str
    url: str
    sha_url: str
    sha: str


class Release(msgspec.Struct):
    """A complete package snapshot for one release-family version."""

    schema_version: int
    family: str
    version: str
    source_url: str
    fetched_at: str
    packages: list[Package]


class VersionIndex(msgspec.Struct):
    """Discovered upstream version directories for the three families."""

    schema_version: int
    fetched_at: str
    frameworks: list[str]
    plasma: list[str]
    release_services: list[str]


class FamilyStatus(msgspec.Struct):
    """Per-family discovery state and successful cache-fill timestamps."""

    versions_checked_at: str | None = None
    discovered_versions: list[str] = msgspec.field(default_factory=list)
    cached_versions: dict[str, str] = msgspec.field(default_factory=dict)


class CacheStatus(msgspec.Struct):
    """Human-readable cache/last.json state for all release families."""

    schema_version: int = SCHEMA_VERSION
    families: dict[str, FamilyStatus] = msgspec.field(default_factory=dict)
