"""Verified KDE source archive downloads and extraction.

This module consumes an existing RecipeContext from kde_oe_recipe_tool.
Release discovery, package selection, and checksum metadata remain owned
by kde_oe_update.

Archives and extracted sources share the selected release cache directory.
No CMake, ECM, YAML, or recipe generation takes place here.
"""

import hashlib
import os
import tarfile
import tempfile
from dataclasses import dataclass
from pathlib import Path
from urllib.parse import unquote, urlsplit

import requests
import zstandard

from concurrent.futures import ThreadPoolExecutor, as_completed
from typing import Iterable

from kde_oe_update import utils
from kde_oe_update.cache import FAMILY_DIRS

from .recipe_ctx import RecipeContext


CHUNK_SIZE = 1024 * 1024

ARCHIVE_SUFFIXES = (
    ".tar.xz",
    ".tar.gz",
    ".tar.bz2",
    ".tar.zst",
    ".tar",
    ".tgz",
    ".tbz2",
)


class FetchError(RuntimeError):
    """Source archive download, verification, or extraction failure."""


@dataclass(frozen=True)
class FetchResult:
    """Paths to a verified source archive and its extracted source tree."""

    archive: Path
    source: Path
    downloaded: bool = False
    extracted: bool = False


def archive_name(context: RecipeContext) -> str:
    """Return the original archive filename from cached upstream metadata."""
    if context.package is None:
        raise FetchError("No KDE package selected")

    url = urlsplit(context.package.url)

    if url.scheme != "https" or not url.netloc:
        raise FetchError(f"Invalid KDE source archive URL: {context.package.url}")

    name = unquote(url.path.rsplit("/", 1)[-1])

    if not name or name in (".", "..") or Path(name).name != name:
        raise FetchError(f"Invalid source archive filename: {name!r}")

    if "\\" in name or any(ord(char) < 32 for char in name):
        raise FetchError(f"Unsafe source archive filename: {name!r}")

    if not name.endswith(ARCHIVE_SUFFIXES):
        raise FetchError(f"Unsupported source archive format: {name}")

    return name


def cache_directory(context: RecipeContext) -> Path:
    """Resolve the existing release directory from shared KDE cache paths."""
    if utils.CACHE_DIR is None:
        raise FetchError("KDE release cache has not been initialized")

    if context.release.family not in FAMILY_DIRS:
        raise FetchError(f"Unsupported KDE family: {context.release.family}")

    if context.version != context.release.version:
        raise FetchError("Selected version does not match the cached release")

    expected_family = (
        "release_services"
        if context.family == "release-services"
        else context.family
    )

    if expected_family != context.release.family:
        raise FetchError("Selected family does not match the cached release")

    return utils.CACHE_DIR / FAMILY_DIRS[context.release.family] / context.version


def source_name(filename: str) -> str:
    """Derive a source-directory name from a supported archive filename."""
    for suffix in ARCHIVE_SUFFIXES:
        if filename.endswith(suffix):
            return filename[:-len(suffix)]

    raise FetchError(f"Unsupported source archive format: {filename}")


def sha256_file(path: Path) -> str:
    """Calculate SHA-256 without loading the entire archive into memory."""
    digest = hashlib.sha256()

    with path.open("rb") as file:
        for chunk in iter(lambda: file.read(CHUNK_SIZE), b""):
            digest.update(chunk)

    return digest.hexdigest()


def verify_archive(path: Path, expected_sha: str) -> None:
    """Verify an archive against the SHA-256 stored in the KDE release cache."""
    if len(expected_sha) != 64:
        raise FetchError("Invalid expected SHA-256 checksum")

    try:
        int(expected_sha, 16)
    except ValueError as exc:
        raise FetchError("Invalid expected SHA-256 checksum") from exc

    actual_sha = sha256_file(path)

    if actual_sha.lower() != expected_sha.lower():
        raise FetchError(
            f"SHA-256 mismatch for {path}\n"
            f"Expected: {expected_sha}\n"
            f"Actual:   {actual_sha}"
        )


def download_archive(context: RecipeContext) -> tuple[Path, bool]:
    """Reuse a verified archive or download and publish a verified copy."""
    if context.package is None:
        raise FetchError("No KDE package selected")

    directory = cache_directory(context)
    filename = archive_name(context)
    archive = directory / filename

    directory.mkdir(parents=True, exist_ok=True)

    if archive.is_symlink():
        raise FetchError(f"Cached archive is a symbolic link: {archive}")

    if archive.exists():
        if not archive.is_file():
            raise FetchError(f"Cached archive is not a regular file: {archive}")

        verify_archive(archive, context.package.sha)
        print(f"Using verified cached archive: {archive}")
        return archive, False

    temporary = None

    try:
        with tempfile.NamedTemporaryFile(
            mode="wb",
            dir=directory,
            prefix=f".{filename}.",
            suffix=".tmp",
            delete=False,
        ) as file:
            temporary = Path(file.name)

            with requests.get(
                context.package.url,
                stream=True,
                timeout=(15, 120),
            ) as response:
                response.raise_for_status()

                for chunk in response.iter_content(chunk_size=CHUNK_SIZE):
                    if chunk:
                        file.write(chunk)

            file.flush()
            os.fsync(file.fileno())

        verify_archive(temporary, context.package.sha)

        # Never publish a downloaded archive before checksum verification.
        if archive.exists() or archive.is_symlink():
            raise FetchError(f"Archive appeared during download: {archive}")

        temporary.replace(archive)
        temporary = None

        print(f"Downloaded and verified: {archive}")
        return archive, True

    except requests.RequestException as exc:
        raise FetchError(f"Download failed: {context.package.url}: {exc}") from exc

    except OSError as exc:
        raise FetchError(f"Archive I/O failure: {exc}") from exc

    finally:
        if temporary is not None:
            temporary.unlink(missing_ok=True)


def _extract_members(archive: tarfile.TarFile, directory: Path) -> None:
    """Extract regular files and directories into an isolated staging tree.

    Source archives with links, devices, or unsafe paths are rejected.
    """
    for member in archive:
        path = Path(member.name)

        if (
            path.is_absolute()
            or ".." in path.parts
            or not member.name
        ):
            raise FetchError(f"Unsafe archive member path: {member.name!r}")

        if not (member.isfile() or member.isdir()):
            raise FetchError(
                f"Unsupported archive member type: {member.name!r}"
            )

        archive.extract(member, path=directory, filter="data")


def _extract_tar(archive: Path, directory: Path) -> None:
    """Extract a supported tar archive safely into a staging directory."""
    if archive.name.endswith(".tar.zst"):
        with archive.open("rb") as file:
            with zstandard.ZstdDecompressor().stream_reader(file) as stream:
                with tarfile.open(fileobj=stream, mode="r|") as tar:
                    _extract_members(tar, directory)
    else:
        with tarfile.open(archive, mode="r:*") as tar:
            _extract_members(tar, directory)


def extract_archive(archive: Path, context: RecipeContext) -> tuple[Path, bool]:
    """Extract a verified archive without replacing an existing source tree."""
    directory = cache_directory(context)
    name = source_name(archive.name)
    source = directory / name

    if source.is_symlink():
        raise FetchError(f"Extracted source path is a symbolic link: {source}")

    if source.exists():
        if not source.is_dir():
            raise FetchError(f"Source path is not a directory: {source}")

        print(f"Using existing source directory: {source}")
        return source, False

    # Extraction happens in a disposable directory before publication.
    with tempfile.TemporaryDirectory(
        dir=directory,
        prefix=f".{name}.",
    ) as temporary:
        staging = Path(temporary)

        try:
            _extract_tar(archive, staging)
        except (tarfile.TarError, OSError, zstandard.ZstdError) as exc:
            raise FetchError(f"Archive extraction failed: {exc}") from exc

        contents = list(staging.iterdir())

        if not contents:
            raise FetchError(f"Archive contains no files: {archive}")

        # KDE archives generally contain one top-level versioned directory.
        if len(contents) == 1 and contents[0].is_dir():
            extracted = contents[0]
        else:
            # Handle archives without one top-level source directory.
            extracted = staging / name

            extracted.mkdir()

            for entry in contents:
                entry.rename(extracted / entry.name)

        if source.exists() or source.is_symlink():
            raise FetchError(f"Source directory appeared during extraction: {source}")

        extracted.rename(source)

    print(f"Extracted source directory: {source}")
    return source, True


def fetch(context: RecipeContext) -> FetchResult:
    """Acquire and extract one KDE source package, then return its paths.

    The archive must match the SHA-256 stored in the cached KDE release.
    No metadata discovery or recipe generation is performed.
    """
    if context.package is None:
        raise FetchError("Cannot fetch without a selected KDE package")

    archive, downloaded = download_archive(context)
    source, extracted = extract_archive(archive, context)

    return FetchResult(
        archive=archive,
        source=source,
        downloaded=downloaded,
        extracted=extracted,
    )


# Maximum number of concurrent download/extraction workers.
# Default: nproc - 1, with at least one worker.
MAX_DOWNLOADS = max(1, (os.process_cpu_count() or 2) - 1)


@dataclass(frozen=True)
class FetchFailure:
    """A package that could not be downloaded or extracted."""

    package: str
    error: str


@dataclass
class FetchBatchResult:
    """Results from a batch of independent source acquisition tasks."""

    completed: list[FetchResult]
    failed: list[FetchFailure]

    @property
    def successful(self) -> bool:
        return not self.failed


def fetch_all(
    contexts: Iterable[RecipeContext],
    max_downloads: int | None = None,
) -> FetchBatchResult:
    """Download and extract multiple packages using bounded concurrency.

    Each worker executes the existing fetch() operation independently.
    A failed package does not prevent other packages from completing.
    No source analysis or recipe generation is performed.
    """
    selected = list(contexts)
    limit = MAX_DOWNLOADS if max_downloads is None else max_downloads

    if limit < 1:
        raise ValueError("max_downloads must be at least 1")

    if not selected:
        return FetchBatchResult(completed=[], failed=[])

    # Each task must have a unique archive and extraction destination.
    # Duplicate destinations could cause concurrent filesystem writes.
    archives = set()
    sources = set()

    for context in selected:
        filename = archive_name(context)
        directory = cache_directory(context)

        archive = directory / filename
        source = directory / source_name(filename)

        if archive in archives or source in sources:
            raise FetchError(
                f"Duplicate download or extraction destination: {archive}"
            )

        archives.add(archive)
        sources.add(source)

    workers = min(limit, len(selected))
    completed = []
    failed = []

    print(f"\nFetching {len(selected)} KDE source packages")
    print(f"Concurrent workers: {workers}\n")

    with ThreadPoolExecutor(
        max_workers=workers,
        thread_name_prefix="kde-fetch",
    ) as executor:
        futures = {
            executor.submit(fetch, context): context
            for context in selected
        }

        for future in as_completed(futures):
            context = futures[future]

            try:
                result = future.result()
                completed.append(result)

            except Exception as exc:
                failed.append(FetchFailure(
                    package=context.package.name,
                    error=str(exc),
                ))
                print(
                    f"FAILED: {context.package.name}: {exc}",
                    flush=True,
                )

    print("\nSource acquisition complete")
    print(f"  Successful: {len(completed)}")
    print(f"  Failed:     {len(failed)}")

    return FetchBatchResult(
        completed=completed,
        failed=failed,
    )


