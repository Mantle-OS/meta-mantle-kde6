"""Offline tests for KDE recipe-tool archive fetching and extraction.

All archives, cache paths, and HTTP responses are local test fixtures.
No KDE mirror access, source inspection, or recipe generation occurs.
"""

import hashlib
import io
import tarfile
import tempfile
import threading
import unittest
from pathlib import Path
from unittest.mock import MagicMock, patch

import zstandard

from kde_oe_recipe_tool.fetcher import (
    FetchError,
    FetchResult,
    archive_name,
    cache_directory,
    download_archive,
    extract_archive,
    fetch,
    fetch_all,
    sha256_file,
    source_name,
    verify_archive,
)
from kde_oe_recipe_tool.recipe_ctx import RecipeContext
from kde_oe_update import utils
from kde_oe_update.schema import Package, Release, SCHEMA_VERSION


class TestRecipeToolFetcher(unittest.TestCase):
    def setUp(self):
        temp = tempfile.TemporaryDirectory()
        self.addCleanup(temp.cleanup)
        self.root = Path(temp.name)

        previous = utils.CACHE_DIR
        self.addCleanup(setattr, utils, "CACHE_DIR", previous)
        utils.CACHE_DIR = self.root / "cache"
        utils.CACHE_DIR.mkdir()

        self.archive_bytes = self.make_tar_xz("kapidox-6.32.0")
        self.context = self.make_context("kapidox", "frameworks", "6.32.0", self.archive_bytes)
        self.directory = utils.CACHE_DIR / "frameworks" / "6.32.0"
        self.archive = self.directory / "kapidox-6.32.0.tar.xz"
        self.source = self.directory / "kapidox-6.32.0"

    @staticmethod
    def make_tar_xz(name: str, *, member_name: str | None = None,
                    kind: str = "file") -> bytes:
        stream = io.BytesIO()
        with tarfile.open(fileobj=stream, mode="w:xz") as archive:
            if kind == "file":
                data = b"cmake_minimum_required(VERSION 3.16)\n"
                info = tarfile.TarInfo(member_name or f"{name}/CMakeLists.txt")
                info.size = len(data)
                archive.addfile(info, io.BytesIO(data))
            elif kind == "symlink":
                info = tarfile.TarInfo(member_name or f"{name}/linked-file")
                info.type = tarfile.SYMTYPE
                info.linkname = "CMakeLists.txt"
                archive.addfile(info)
            else:
                raise ValueError(kind)
        return stream.getvalue()

    @staticmethod
    def make_context(name: str, family: str, version: str, data: bytes,
                     *, suffix: str = ".tar.xz") -> RecipeContext:
        url = f"https://example.org/{name}-{version}{suffix}"
        package = Package(
            name=name,
            url=url,
            sha_url=url + ".sha256",
            sha=hashlib.sha256(data).hexdigest(),
        )
        release_family = "release_services" if family == "release-services" else family
        release = Release(
            schema_version=SCHEMA_VERSION,
            family=release_family,
            version=version,
            source_url=utils.get_source_url(release_family, version),
            fetched_at="2026-11-17T12:00:00Z",
            packages=[package],
        )
        return RecipeContext(family=family, version=version,
                             release=release, package=package)

    def store_archive(self, data: bytes | None = None) -> None:
        self.directory.mkdir(parents=True, exist_ok=True)
        self.archive.write_bytes(self.archive_bytes if data is None else data)

    @staticmethod
    def fake_response(data: bytes):
        response = MagicMock()
        response.__enter__.return_value = response
        response.iter_content.return_value = [data[:13], b"", data[13:]]
        return response

    def test_archive_name_and_source_name(self):
        self.assertEqual(archive_name(self.context), "kapidox-6.32.0.tar.xz")
        self.assertEqual(source_name("kapidox-6.32.0.tar.xz"), "kapidox-6.32.0")
        self.assertEqual(source_name("package.tar.zst"), "package")
        self.assertEqual(source_name("package.tgz"), "package")
        with self.assertRaises(FetchError):
            source_name("package.zip")

    def test_cache_directory_for_every_family(self):
        cases = (
            ("frameworks", "6.32.0", "frameworks"),
            ("plasma", "6.8.3", "plasma"),
            ("release-services", "26.08.3", "release-service"),
        )
        for family, version, directory in cases:
            with self.subTest(family=family):
                context = self.make_context("example", family, version, b"payload")
                self.assertEqual(cache_directory(context), utils.CACHE_DIR / directory / version)

    def test_cache_directory_rejects_mismatched_identity(self):
        with self.assertRaises(FetchError):
            cache_directory(RecipeContext(
                family="plasma", version="6.32.0",
                release=self.context.release, package=self.context.package,
            ))
        with self.assertRaises(FetchError):
            cache_directory(RecipeContext(
                family="frameworks", version="6.31.0",
                release=self.context.release, package=self.context.package,
            ))

    def test_sha256_file_and_verify_archive(self):
        self.store_archive()
        self.assertEqual(sha256_file(self.archive), self.context.package.sha)
        verify_archive(self.archive, self.context.package.sha)

    def test_verify_archive_rejects_bad_checksum(self):
        self.store_archive(b"corrupt")
        with self.assertRaisesRegex(FetchError, "SHA-256 mismatch"):
            verify_archive(self.archive, self.context.package.sha)
        with self.assertRaisesRegex(FetchError, "Invalid expected SHA-256"):
            verify_archive(self.archive, "not-a-sha256")

    def test_download_reuses_verified_archive_without_network(self):
        self.store_archive()
        with patch("kde_oe_recipe_tool.fetcher.requests.get",
                   side_effect=AssertionError("unexpected network request")):
            archive, downloaded = download_archive(self.context)
        self.assertEqual(archive, self.archive)
        self.assertFalse(downloaded)
        self.assertEqual(self.archive.read_bytes(), self.archive_bytes)

    def test_download_rejects_corrupt_cached_archive_without_network(self):
        self.store_archive(b"corrupt")
        with patch("kde_oe_recipe_tool.fetcher.requests.get",
                   side_effect=AssertionError("unexpected network request")):
            with self.assertRaisesRegex(FetchError, "SHA-256 mismatch"):
                download_archive(self.context)
        self.assertEqual(self.archive.read_bytes(), b"corrupt")

    def test_download_rejects_cached_archive_symlink(self):
        self.directory.mkdir(parents=True)
        external = self.root / "external.tar.xz"
        external.write_bytes(self.archive_bytes)
        self.archive.symlink_to(external)
        with patch("kde_oe_recipe_tool.fetcher.requests.get",
                   side_effect=AssertionError("unexpected network request")):
            with self.assertRaisesRegex(FetchError, "symbolic link"):
                download_archive(self.context)
        self.assertEqual(external.read_bytes(), self.archive_bytes)

    def test_download_streams_verifies_and_publishes(self):
        response = self.fake_response(self.archive_bytes)
        with patch("kde_oe_recipe_tool.fetcher.requests.get", return_value=response) as get:
            archive, downloaded = download_archive(self.context)
        self.assertTrue(downloaded)
        self.assertEqual(archive, self.archive)
        self.assertEqual(self.archive.read_bytes(), self.archive_bytes)
        self.assertEqual(list(self.directory.glob("*.tmp")), [])
        get.assert_called_once_with(self.context.package.url, stream=True, timeout=(15, 120))
        response.raise_for_status.assert_called_once()

    def test_download_does_not_publish_invalid_payload(self):
        with patch("kde_oe_recipe_tool.fetcher.requests.get",
                   return_value=self.fake_response(b"wrong content")):
            with self.assertRaisesRegex(FetchError, "SHA-256 mismatch"):
                download_archive(self.context)
        self.assertFalse(self.archive.exists())
        self.assertEqual(list(self.directory.iterdir()), [])

    def test_download_wraps_network_failure_and_removes_temporary_file(self):
        import requests
        with patch("kde_oe_recipe_tool.fetcher.requests.get",
                   side_effect=requests.ConnectionError("offline")):
            with self.assertRaisesRegex(FetchError, "Download failed"):
                download_archive(self.context)
        self.assertFalse(self.archive.exists())
        self.assertEqual(list(self.directory.iterdir()), [])

    def test_extract_tar_xz_to_versioned_cache(self):
        self.store_archive()
        source, extracted = extract_archive(self.archive, self.context)
        self.assertTrue(extracted)
        self.assertEqual(source, self.source)
        self.assertIn("cmake_minimum_required", (source / "CMakeLists.txt").read_text())
        self.assertEqual(list(self.directory.glob(".kapidox-6.32.0.*")), [])

    def test_extract_reuses_existing_source_without_overwriting(self):
        self.store_archive()
        self.source.mkdir()
        modified = self.source / "CMakeLists.txt"
        modified.write_text("developer edits\n")
        source, extracted = extract_archive(self.archive, self.context)
        self.assertEqual(source, self.source)
        self.assertFalse(extracted)
        self.assertEqual(modified.read_text(), "developer edits\n")

    def test_extract_rejects_existing_source_symlink(self):
        self.store_archive()
        external = self.root / "external-source"
        external.mkdir()
        self.source.symlink_to(external, target_is_directory=True)
        with self.assertRaisesRegex(FetchError, "symbolic link"):
            extract_archive(self.archive, self.context)
        self.assertEqual(list(external.iterdir()), [])

    def test_extract_rejects_traversal_and_leaves_no_source(self):
        malicious = self.make_tar_xz("kapidox-6.32.0", member_name="../outside.txt")
        self.store_archive(malicious)
        with self.assertRaisesRegex(FetchError, "Unsafe archive member path"):
            extract_archive(self.archive, self.context)
        self.assertFalse(self.source.exists())
        self.assertFalse((self.root / "outside.txt").exists())
        self.assertEqual(list(self.directory.glob(".kapidox-6.32.0.*")), [])

    def test_extract_rejects_links_and_leaves_no_source(self):
        self.store_archive(self.make_tar_xz("kapidox-6.32.0", kind="symlink"))
        with self.assertRaisesRegex(FetchError, "Unsupported archive member type"):
            extract_archive(self.archive, self.context)
        self.assertFalse(self.source.exists())

    def test_extract_supports_tar_zst(self):
        raw_tar = io.BytesIO()
        with tarfile.open(fileobj=raw_tar, mode="w") as tar:
            data = b"project(kapidox)\n"
            info = tarfile.TarInfo("kapidox-6.32.0/CMakeLists.txt")
            info.size = len(data)
            tar.addfile(info, io.BytesIO(data))
        compressed = zstandard.ZstdCompressor().compress(raw_tar.getvalue())
        context = self.make_context("kapidox", "frameworks", "6.32.0", compressed,
                                    suffix=".tar.zst")
        archive = self.directory / "kapidox-6.32.0.tar.zst"
        self.directory.mkdir(parents=True)
        archive.write_bytes(compressed)
        source, extracted = extract_archive(archive, context)
        self.assertTrue(extracted)
        self.assertEqual((source / "CMakeLists.txt").read_bytes(), b"project(kapidox)\n")

    def test_fetch_complete_from_cached_archive_without_network(self):
        self.store_archive()
        with patch("kde_oe_recipe_tool.fetcher.requests.get",
                   side_effect=AssertionError("unexpected network request")):
            result = fetch(self.context)
        self.assertIsInstance(result, FetchResult)
        self.assertEqual((result.archive, result.source), (self.archive, self.source))
        self.assertFalse(result.downloaded)
        self.assertTrue(result.extracted)
        self.assertTrue((result.source / "CMakeLists.txt").is_file())

    def test_fetch_repeat_is_fully_cached(self):
        self.store_archive()
        fetch(self.context)
        with patch("kde_oe_recipe_tool.fetcher.requests.get",
                   side_effect=AssertionError("unexpected network request")):
            result = fetch(self.context)
        self.assertFalse(result.downloaded)
        self.assertFalse(result.extracted)

    def test_fetch_all_empty_queue(self):
        with patch("kde_oe_recipe_tool.fetcher.fetch",
                   side_effect=AssertionError("unexpected worker")):
            result = fetch_all([], max_downloads=2)
        self.assertEqual(result.completed, [])
        self.assertEqual(result.failed, [])
        self.assertTrue(result.successful)

    def test_fetch_all_rejects_invalid_limit(self):
        with self.assertRaisesRegex(ValueError, "at least 1"):
            fetch_all([self.context], max_downloads=0)

    def test_fetch_all_rejects_duplicate_destinations(self):
        with patch("kde_oe_recipe_tool.fetcher.fetch",
                   side_effect=AssertionError("unexpected worker")):
            with self.assertRaisesRegex(FetchError, "Duplicate"):
                fetch_all([self.context, self.context], max_downloads=2)

    def test_fetch_all_collects_successes_and_failures(self):
        other = self.make_context("kmime", "frameworks", "6.32.0", b"other")
        def worker(context):
            if context.package.name == "kmime":
                raise FetchError("simulated failure")
            return FetchResult(archive=self.archive, source=self.source)
        with patch("kde_oe_recipe_tool.fetcher.fetch", side_effect=worker):
            result = fetch_all([self.context, other], max_downloads=2)
        self.assertEqual(len(result.completed), 1)
        self.assertEqual(len(result.failed), 1)
        self.assertEqual(result.failed[0].package, "kmime")
        self.assertIn("simulated failure", result.failed[0].error)
        self.assertFalse(result.successful)

    def test_fetch_all_respects_worker_limit(self):
        contexts = [self.make_context(f"package{i}", "frameworks", "6.32.0", bytes([i]))
                    for i in range(5)]
        lock = threading.Lock()
        active = 0
        maximum = 0
        barrier = threading.Barrier(2, timeout=5)

        def worker(context):
            nonlocal active, maximum
            with lock:
                active += 1
                maximum = max(maximum, active)
            try:
                # Two simultaneous workers can pass; a serial executor cannot.
                if context.package.name in ("package0", "package1"):
                    barrier.wait()
                return FetchResult(archive=self.archive, source=self.source)
            finally:
                with lock:
                    active -= 1

        with patch("kde_oe_recipe_tool.fetcher.fetch", side_effect=worker):
            result = fetch_all(contexts, max_downloads=2)
        self.assertTrue(result.successful)
        self.assertEqual(len(result.completed), 5)
        self.assertEqual(maximum, 2)


if __name__ == "__main__":
    unittest.main()
