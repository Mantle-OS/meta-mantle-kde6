"""KDE mirror discovery and local updater configuration helpers.

The entry point initializes the shared paths. This module performs no cache
serialization; cache.py owns all persistent release snapshots.
"""

import json
import re
import requests

from pathlib import Path
from bs4 import BeautifulSoup
from urllib.parse import unquote, urljoin, urlsplit


SCRIPT_DIR: Path | None = None
VERSION_FILE: Path | None = None
INPUT_DIR: Path | None = None
CACHE_DIR: Path | None = None
OUTPUT_DIR: Path | None = None

KDE_MIRROR = "https://download.kde.org"
PLASMA_URL = f"{KDE_MIRROR}/stable/plasma/"
FRAMEWORKS_URL = f"{KDE_MIRROR}/stable/frameworks/"
RELEASE_SERVICES_URL = f"{KDE_MIRROR}/stable/release-service/"

VERSION_URLS = {
    "plasma": PLASMA_URL,
    "frameworks": FRAMEWORKS_URL,
    "release_services": RELEASE_SERVICES_URL,
}

INPUT_CLASSES = {
    "frameworks": "kf6-checksums.bbclass",
    "plasma": "plasma6-checksums.bbclass",
    "release_services": "rs6-checksums.bbclass",
}

VERSION_KEYS = (
    "frameworks",
    "release_services",
    "plasma",
    "plasma_wayland_protocols",
)

_VERSION_PATTERN = re.compile(r"[0-9]+(?:\.[0-9]+){1,2}\Z")


# Normalize whitespace around BitBake checksum assignments.
_CHECKSUM_SPACING = re.compile(
    r'^(SRC_URI\[[^\]\r\n]+\])[ \t]*=[ \t]*("[^"\r\n]*")$'
)

def normalize_checksum_line(line: str) -> str:
    if not line.startswith("SRC_URI["):
        raise ValueError("Expected SRC_URI[ at column zero")

    match = _CHECKSUM_SPACING.fullmatch(line)

    if match is None:
        raise ValueError("Malformed checksum assignment")

    return f"{match.group(1)} = {match.group(2)}"

def has_input(type: str) -> bool:
    """Require a manually supplied checksum-only bbclass for generation."""
    if type not in INPUT_CLASSES:
        raise ValueError(f"Unknown KDE release type: {type}")
    if INPUT_DIR is None:
        raise RuntimeError("INPUT_DIR has not been initialized by the entry point")

    path = INPUT_DIR / INPUT_CLASSES[type]
    if not path.is_file():
        raise FileNotFoundError(f"Required input bbclass missing: {path}")
    return True


def get_source_url(type: str, version: str) -> str:
    """Resolve a full release version to its KDE source archive directory."""
    if type not in VERSION_URLS:
        raise ValueError(f"Unknown KDE release type: {type}")

    version = version.strip()
    if not _VERSION_PATTERN.fullmatch(version):
        raise ValueError(f"Invalid KDE release version: {version!r}")

    # Frameworks publishes archives under major.minor, e.g. 6.32/.
    version_dir = ".".join(version.split(".")[:2]) if type == "frameworks" else version
    url = f"{VERSION_URLS[type]}{version_dir}/"
    if type == "release_services":
        url += "src/"
    return url


def local_versions() -> dict[str, str]:
    """Read and validate Mantle's explicitly selected KDE versions."""
    if VERSION_FILE is None:
        raise RuntimeError("VERSION_FILE has not been initialized by the entry point")
    if not VERSION_FILE.is_file():
        raise FileNotFoundError(f"Missing version configuration: {VERSION_FILE}")

    with VERSION_FILE.open("r", encoding="utf-8") as file:
        data = json.load(file)

    if not isinstance(data, dict):
        raise ValueError(f"Invalid version configuration (expected object): {VERSION_FILE}")

    missing = [key for key in VERSION_KEYS if key not in data]
    if missing:
        raise ValueError(f"Missing version keys in {VERSION_FILE}: {', '.join(missing)}")

    for key in VERSION_KEYS:
        value = data[key]
        if not isinstance(value, str) or not _VERSION_PATTERN.fullmatch(value.strip()):
            raise ValueError(f"Invalid version for {key} in {VERSION_FILE}: {value!r}")
        data[key] = value.strip()

    return data


# Parse a KDE directory listing.
def parse_req(url: str) -> list[dict[str, str]]:
    response = requests.get(url, timeout=30)
    response.raise_for_status()

    soup = BeautifulSoup(response.text, "html.parser")
    base = urlsplit(response.url)
    base_path = base.path.rstrip("/") + "/"
    results = []
    seen = set()

    for link in soup.select("a[href]"):
        entry_url = urljoin(response.url, link["href"])
        parsed = urlsplit(entry_url)

        # Only accept entries directly inside the requested directory.
        if parsed.scheme != base.scheme or parsed.netloc != base.netloc:
            continue

        if not parsed.path.startswith(base_path):
            continue

        relative = parsed.path[len(base_path):]

        if not relative or "/" in relative.rstrip("/"):
            continue

        if parsed.query or parsed.fragment:
            continue

        name = unquote(relative)

        if name in seen:
            continue

        seen.add(name)
        results.append({
            "name": name,
            "url": entry_url,
        })

    if not results:
        raise ValueError(f"No directory entries found: {url}")

    return results


def get_packages(type: str, version: str) -> list[dict[str, str]]:
    """Discover archives for an explicitly selected version, without reading versions.json."""
    url = get_source_url(type, version)
    version = version.strip()
    entries = parse_req(url)
    packages = []
    suffix = f"-{version}.tar.xz"

    for entry in entries:
        filename = entry["name"].strip()
        # Skip signatures, checksum files, and unrelated entries.
        if not filename.endswith(suffix):
            continue
        name = filename.removesuffix(suffix)
        if not name:
            raise ValueError(f"Missing package name in KDE listing: {filename}")
        package_url = entry["url"]
        packages.append({
            "name": name,
            "url": package_url,
            "sha_url": f"{package_url}.sha256",
        })

    return sorted(packages, key=lambda package: package["name"])


def get_sha(packages: list[dict[str, str]]) -> list[dict[str, str]]:
    """Retrieve and validate KDE .sha256 files for the supplied package records."""
    for package in packages:
        response = requests.get(package["sha_url"], timeout=30)
        response.raise_for_status()
        # Format: <sha256>  <filename>
        parts = response.text.strip().split(maxsplit=1)
        if len(parts) != 2:
            raise ValueError(f"Invalid SHA256 response: {package['sha_url']}")
        sha, filename = parts
        if len(sha) != 64 or not all(c in "0123456789abcdefABCDEF" for c in sha):
            raise ValueError(f"Invalid SHA256 checksum: {package['sha_url']}")
        expected_filename = package["url"].rsplit("/", 1)[-1]
        if filename.lstrip("*") != expected_filename:
            raise ValueError(
                f"Checksum filename mismatch: expected {expected_filename}, got {filename}"
            )
        package["sha"] = sha.lower()
    return packages


# Get available upstream versions, ordered from oldest to newest.
def get_version(type: str) -> list[str]:
    if type not in VERSION_URLS:
        raise ValueError(f"Unknown KDE release type: {type}")
    entries = parse_req(VERSION_URLS[type])
    versions = set()
    for entry in entries:
        name = entry["name"].strip().rstrip("/")
        if _VERSION_PATTERN.fullmatch(name):
            versions.add(name)
    return sorted(versions, key=lambda version: tuple(int(part) for part in version.split(".")))
