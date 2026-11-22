"""Strict checksum-only OpenEmbedded manifest parsing and generation.

This module works entirely offline. It does not select versions, fetch upstream
metadata, or modify developer-supplied input files.
"""

import os
import re
import tempfile
from dataclasses import dataclass
from pathlib import Path
from typing import Callable

from .schema import Package, Release
from . import utils

KEY_PATTERN = re.compile(r"^[A-Za-z0-9][A-Za-z0-9+_.-]*$")
CHECKSUM_LINE = re.compile(r'^SRC_URI\[([A-Za-z0-9][A-Za-z0-9+_.-]*)\.sha256sum\] = "([0-9a-fA-F]{64})"$')
SHA256 = re.compile(r'^[0-9a-f]{64}$')


class ManifestError(ValueError):
    """An invalid checksum input or an ambiguous upstream package mapping."""


@dataclass(frozen=True)
class ChecksumEntry:
    key: str
    sha: str


@dataclass(frozen=True)
class Comparison:
    """A comparison in local input order, plus upstream/local differences."""

    entries: list[ChecksumEntry]
    missing: list[Package]  # Upstream packages without any local checksum key.
    removed: list[ChecksumEntry]  # Local keys without an upstream package.


def parse_checksums(path: Path) -> list[ChecksumEntry]:
    """Parse a checksum-only input. Every line must be a complete assignment.

    Empty files, blank lines, leading whitespace, comments, malformed assignments,
    duplicate keys, and invalid checksums are all fatal.
    """
    try:
        contents = path.read_text(encoding="utf-8")
    except (OSError, UnicodeError) as exc:
        raise ManifestError(f"Cannot read checksum input {path}: {exc}") from exc

    if not contents:
        raise ManifestError(f"Checksum input is empty: {path}")

    entries = []
    seen = set()

    for lineno, line in enumerate(contents.splitlines(), 1):
        if not line.startswith("SRC_URI["):
            raise ManifestError(f"{path}:{lineno}: expected SRC_URI[ at column zero")

        try:
            line = utils.normalize_checksum_line(line)
        except ValueError as exc:
            raise ManifestError(f"{path}:{lineno}: {exc}") from exc

        match = CHECKSUM_LINE.fullmatch(line)

        if match is None:
            raise ManifestError(f"{path}:{lineno}: malformed SHA-256 assignment")

        key, sha = match.groups()

        if key in seen:
            raise ManifestError(f"{path}:{lineno}: duplicate checksum key {key!r}")

        seen.add(key)
        entries.append(ChecksumEntry(key=key, sha=sha.lower()))

    return entries


def compare_checksums(
    entries: list[ChecksumEntry],
    release: Release,
    resolve_name: Callable[[str], str],
) -> Comparison:
    """Match local checksum keys to upstream names using caller's family rules.

    Multiple local keys may resolve to the same upstream archive; this is a
    supported alias case. Unmatched local keys retain their historical SHA.
    """
    upstream = {}
    for package in release.packages:
        if not package.name or package.name in upstream:
            raise ManifestError(f"Invalid or duplicate upstream package: {package.name!r}")
        if SHA256.fullmatch(package.sha) is None:
            raise ManifestError(f"Invalid upstream checksum for {package.name!r}")
        upstream[package.name] = package

    result = []
    removed = []
    matched = set()
    seen_keys = set()
    for entry in entries:
        if entry.key in seen_keys:
            raise ManifestError(f"Duplicate local checksum key: {entry.key!r}")
        seen_keys.add(entry.key)
        if KEY_PATTERN.fullmatch(entry.key) is None or SHA256.fullmatch(entry.sha) is None:
            raise ManifestError(f"Invalid local checksum entry: {entry.key!r}")
        name = resolve_name(entry.key)
        if not isinstance(name, str) or not name or name != name.strip() or "/" in name or "\\" in name:
            raise ManifestError(f"Invalid upstream name for local key {entry.key!r}: {name!r}")
        package = upstream.get(name)
        if package is None:
            removed.append(entry)
            result.append(entry)
        else:
            matched.add(name)
            result.append(ChecksumEntry(key=entry.key, sha=package.sha))

    missing = [package for package in release.packages if package.name not in matched]
    return Comparison(entries=result, missing=missing, removed=removed)


def render_checksums(entries: list[ChecksumEntry]) -> str:
    """Render only checksum assignments in input order, with a final newline."""
    if not entries:
        raise ManifestError("Cannot render an empty checksum manifest")
    seen = set()
    lines = []
    for entry in entries:
        if entry.key in seen or KEY_PATTERN.fullmatch(entry.key) is None:
            raise ManifestError(f"Duplicate or invalid checksum key: {entry.key!r}")
        if SHA256.fullmatch(entry.sha) is None:
            raise ManifestError(f"Invalid checksum for {entry.key!r}")
        seen.add(entry.key)
        lines.append(f'SRC_URI[{entry.key}.sha256sum] = "{entry.sha}"')
    return "\n".join(lines) + "\n"


def write_checksums(path: Path, entries: list[ChecksumEntry]) -> None:
    """Atomically publish a generated fragment. Never call with the input path."""
    data = render_checksums(entries).encode("utf-8")
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



