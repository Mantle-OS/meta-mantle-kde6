
import unittest

import msgspec

from kde_oe_update.schema import (
    SCHEMA_VERSION,
    Package,
    Release,
    VersionIndex,
    FamilyStatus,
    CacheStatus,
)


class TestSchema(unittest.TestCase):
    def test_package_roundtrip(self):
        package = Package(
            name="karchive",
            url="https://example.org/karchive-6.32.0.tar.xz",
            sha_url="https://example.org/karchive-6.32.0.tar.xz.sha256",
            sha="a" * 64,
        )

        encoded = msgspec.msgpack.encode(package)
        decoded = msgspec.msgpack.decode(encoded, type=Package)

        self.assertEqual(decoded, package)

    def test_release_roundtrip(self):
        package = Package(
            name="karchive",
            url="https://example.org/karchive-6.32.0.tar.xz",
            sha_url="https://example.org/karchive-6.32.0.tar.xz.sha256",
            sha="b" * 64,
        )

        release = Release(
            schema_version=SCHEMA_VERSION,
            family="frameworks",
            version="6.32.0",
            source_url="https://example.org/frameworks/6.32/",
            fetched_at="2026-11-16T19:00:00Z",
            packages=[package],
        )

        encoded = msgspec.msgpack.encode(release)
        decoded = msgspec.msgpack.decode(encoded, type=Release)

        self.assertEqual(decoded, release)
        self.assertEqual(len(decoded.packages), 1)

    def test_version_index_roundtrip(self):
        index = VersionIndex(
            schema_version=SCHEMA_VERSION,
            fetched_at="2026-11-16T19:00:00Z",
            frameworks=["6.31", "6.32"],
            plasma=["6.8.2", "6.8.3"],
            release_services=["26.08.2", "26.08.3"],
        )

        encoded = msgspec.msgpack.encode(index)
        decoded = msgspec.msgpack.decode(encoded, type=VersionIndex)

        self.assertEqual(decoded, index)

    def test_cache_status_roundtrip(self):
        status = CacheStatus(
            families={
                "frameworks": FamilyStatus(
                    versions_checked_at="2026-11-16T19:00:00Z",
                    discovered_versions=["6.32"],
                    cached_versions={"6.32.0": "2026-11-16T19:10:00Z"},
                )
            }
        )

        encoded = msgspec.json.encode(status)
        decoded = msgspec.json.decode(encoded, type=CacheStatus)

        self.assertEqual(decoded, status)

    def test_independent_defaults(self):
        first = FamilyStatus()
        second = FamilyStatus()

        first.discovered_versions.append("6.32")
        first.cached_versions["6.32.0"] = "2026-11-16T19:10:00Z"

        self.assertEqual(second.discovered_versions, [])
        self.assertEqual(second.cached_versions, {})

    def test_invalid_package_type(self):
        data = {
            "name": "karchive",
            "url": "https://example.org/archive.tar.xz",
            "sha_url": "https://example.org/archive.tar.xz.sha256",
            "sha": 12345,
        }

        encoded = msgspec.msgpack.encode(data)

        with self.assertRaises(msgspec.ValidationError):
            msgspec.msgpack.decode(encoded, type=Package)
