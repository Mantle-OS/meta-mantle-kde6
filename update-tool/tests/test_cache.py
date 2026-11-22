"""Offline tests for binary cache persistence and integrity."""

import json
import tempfile
import unittest
from pathlib import Path

import msgspec

from kde_oe_update.cache import Cache, CacheError
from kde_oe_update.schema import CacheStatus, FamilyStatus, Package, Release, SCHEMA_VERSION, VersionIndex


class TestCache(unittest.TestCase):
    def setUp(self):
        self.temporary = tempfile.TemporaryDirectory()
        self.addCleanup(self.temporary.cleanup)
        self.cache = Cache(Path(self.temporary.name) / "cache")

    def release(self):
        return Release(
            schema_version=SCHEMA_VERSION,
            family="frameworks",
            version="6.32.0",
            source_url="https://example.org/stable/frameworks/6.32/",
            fetched_at="2026-11-16T19:00:00Z",
            packages=[
                Package(
                    name="karchive",
                    url="https://example.org/karchive-6.32.0.tar.xz",
                    sha_url="https://example.org/karchive-6.32.0.tar.xz.sha256",
                    sha="a" * 64,
                ),
                Package(
                    name="kconfig",
                    url="https://example.org/kconfig-6.32.0.tar.xz",
                    sha_url="https://example.org/kconfig-6.32.0.tar.xz.sha256",
                    sha="b" * 64,
                ),
            ],
        )

    def test_release_roundtrip_and_status(self):
        release = self.release()
        self.cache.save_release(release)
        self.assertTrue(self.cache.has_release("frameworks", "6.32.0"))
        self.assertEqual(self.cache.load_release("frameworks", "6.32.0"), release)
        self.assertIn("6.32.0", self.cache.load_status().families["frameworks"].cached_versions)

    def test_missing_or_incomplete_release(self):
        self.assertFalse(self.cache.has_release("frameworks", "6.32.0"))
        directory = self.cache.release_dir("frameworks", "6.32.0")
        directory.mkdir(parents=True)
        (directory / "packages.msgpack.zst").write_bytes(b"not a compressed snapshot")
        self.assertFalse(self.cache.has_release("frameworks", "6.32.0"))
        with self.assertRaises(CacheError):
            self.cache.load_release("frameworks", "6.32.0")

    def test_tampered_binary_detected(self):
        self.cache.save_release(self.release())
        path = self.cache.release_dir("frameworks", "6.32.0") / "packages.msgpack.zst"
        path.write_bytes(path.read_bytes() + b"tampered")
        with self.assertRaises(CacheError):
            self.cache.load_release("frameworks", "6.32.0")

    def test_tampered_manifest_detected(self):
        self.cache.save_release(self.release())
        path = self.cache.release_dir("frameworks", "6.32.0") / "packages.manifest.json"
        manifest = json.loads(path.read_text(encoding="utf-8"))
        manifest["packages"]["karchive"] = "c" * 64
        path.write_text(json.dumps(manifest), encoding="utf-8")
        with self.assertRaises(CacheError):
            self.cache.load_release("frameworks", "6.32.0")

    def test_invalid_release_does_not_publish(self):
        release = self.release()
        release.packages[0].sha = "invalid"
        with self.assertRaises(CacheError):
            self.cache.save_release(release)
        self.assertFalse(self.cache.has_release("frameworks", "6.32.0"))
        self.assertNotIn("frameworks", self.cache.load_status().families)

    def test_version_index_roundtrip(self):
        index = VersionIndex(
            schema_version=SCHEMA_VERSION,
            fetched_at="2026-11-16T19:00:00Z",
            frameworks=["6.31", "6.32"],
            plasma=["6.8.2", "6.8.3"],
            release_services=["26.08.2", "26.08.3"],
        )
        self.cache.save_version_index(index)
        self.assertEqual(self.cache.load_version_index(), index)
        self.assertEqual(self.cache.load_status().families["frameworks"].discovered_versions, ["6.31", "6.32"])

    def test_cache_status_roundtrip(self):
        status = CacheStatus(families={
            "plasma": FamilyStatus(versions_checked_at="2026-11-16T19:00:00Z")
        })
        self.cache.save_status(status)
        self.assertEqual(self.cache.load_status(), status)

    def test_schema_version_rejected(self):
        release = self.release()
        release.schema_version = SCHEMA_VERSION + 1
        with self.assertRaises(CacheError):
            self.cache.save_release(release)
        index = VersionIndex(SCHEMA_VERSION + 1, "2026-11-16T19:00:00Z", [], [], [])
        with self.assertRaises(CacheError):
            self.cache.save_version_index(index)

    def test_path_traversal_rejected(self):
        with self.assertRaises(CacheError):
            self.cache.release_dir("frameworks", "../../etc")
        with self.assertRaises(CacheError):
            self.cache.release_dir("unknown", "6.32.0")


if __name__ == "__main__":
    unittest.main()
