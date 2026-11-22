"""Offline tests for the shared KDE family workflow."""

import json
import tempfile
import unittest
from pathlib import Path
from unittest.mock import patch

from kde_oe_update import utils
from kde_oe_update.cache import Cache, CacheError
from kde_oe_update.kde import KDE
from kde_oe_update.schema import Package, Release, SCHEMA_VERSION, VersionIndex


class Frameworks(KDE):
    base_name = "frameworks"
    base_url = utils.FRAMEWORKS_URL
    override_map = {"ecm-kf6": "extra-cmake-modules"}


class Plasma(KDE):
    base_name = "plasma"
    base_url = utils.PLASMA_URL
    override_map = {"layer-shell-qt6-kde6": "layer-shell-qt"}


class TestKDE(unittest.TestCase):
    def setUp(self):
        temp = tempfile.TemporaryDirectory()
        self.addCleanup(temp.cleanup)
        root = Path(temp.name)
        self.cache = Cache(root / "cache")
        for key, path in {
            "CACHE_DIR": root / "cache",
            "INPUT_DIR": root / "in",
            "OUTPUT_DIR": root / "out",
            "VERSION_FILE": root / "versions.json",
        }.items():
            previous = getattr(utils, key)
            self.addCleanup(setattr, utils, key, previous)
            setattr(utils, key, path)
        utils.INPUT_DIR.mkdir()
        utils.VERSION_FILE.write_text(json.dumps({
            "frameworks": "6.32.0",
            "plasma": "6.8.3",
            "release_services": "26.08.3",
            "plasma_wayland_protocols": "1.23.0",
        }))
        self.frameworks = Frameworks(self.cache)
        self.plasma = Plasma(self.cache)
        self.index = VersionIndex(
            schema_version=SCHEMA_VERSION,
            fetched_at="2026-11-16T19:00:00Z",
            frameworks=["6.31", "6.32"],
            plasma=["6.8.2", "6.8.3"],
            release_services=["26.08.2", "26.08.3"],
        )
        self.cache.save_version_index(self.index)

    def release(self, family="frameworks", version="6.32.0"):
        name = "karchive" if family == "frameworks" else "layer-shell-qt"
        url = f"https://example.org/{name}-{version}.tar.xz"
        return Release(SCHEMA_VERSION, family, version,
                       utils.get_source_url(family, version), "2026-11-16T19:00:00Z",
                       [Package(name, url, url + ".sha256", "a" * 64)])

    def test_check_version_uses_index_no_network(self):
        with patch.object(utils, "get_version", side_effect=AssertionError("network")):
            result = self.frameworks.check_version()
        self.assertEqual(result["current"], "6.32.0")
        self.assertEqual(result["latest_directory"], "6.32")
        self.assertFalse(result["newer_directory_available"])

    def test_update_version_preserves_unrelated_keys(self):
        self.frameworks.update_version("6.31.2")
        versions = utils.local_versions()
        self.assertEqual(versions["frameworks"], "6.31.2")
        self.assertEqual(versions["plasma_wayland_protocols"], "1.23.0")
        with self.assertRaises(ValueError):
            self.frameworks.update_version("6.90.0")

    def test_update_cache_without_index_fails(self):
        (self.cache.root / "versions.msgpack.zst").unlink()
        with self.assertRaisesRegex(CacheError, "--update-versions"):
            self.frameworks.update_cache()

    def test_update_cache_fetches_then_reuses(self):
        name = "karchive"
        url = "https://example.org/karchive-6.32.0.tar.xz"
        data = [{"name": name, "url": url, "sha_url": url + ".sha256"}]
        def sha(packages):
            packages[0]["sha"] = "a" * 64
            return packages
        with patch.object(utils, "get_packages", return_value=data) as packages, \
             patch.object(utils, "get_sha", side_effect=sha) as checksum:
            release = self.frameworks.update_cache()
            self.assertEqual(release.packages[0].name, "karchive")
            packages.assert_called_once()
            checksum.assert_called_once()
        with patch.object(utils, "get_packages", side_effect=AssertionError("unexpected fetch")):
            self.assertEqual(self.frameworks.update_cache(), release)

    def test_generate_requires_baseline(self):
        self.cache.save_release(self.release())
        with self.assertRaises(FileNotFoundError):
            self.frameworks.build_final_list()

    def test_generate_preserves_key_and_removed_sha(self):
        self.cache.save_release(self.release())
        original = (
            'SRC_URI[karchive6.sha256sum] = "' + "b" * 64 + '"\n'
            'SRC_URI[historical6.sha256sum] = "' + "c" * 64 + '"\n'
        )
        (utils.INPUT_DIR / "kf6-checksums.bbclass").write_text(original)
        with patch("builtins.print") as printed:
            output = self.frameworks.build_final_list()
        lines = output.read_text().splitlines()
        self.assertEqual(lines[0], 'SRC_URI[karchive6.sha256sum] = "' + "a" * 64 + '"')
        self.assertEqual(lines[1], 'SRC_URI[historical6.sha256sum] = "' + "c" * 64 + '"')
        self.assertEqual((utils.INPUT_DIR / "kf6-checksums.bbclass").read_text(), original)
        self.assertEqual(printed.call_count, 1)

    def test_aliases_can_share_upstream_archive(self):
        self.cache.save_release(self.release("plasma", "6.8.3"))
        (utils.INPUT_DIR / "plasma6-checksums.bbclass").write_text(
            'SRC_URI[layer-shell-qt6-kde6.sha256sum] = "' + "b" * 64 + '"\n'
            'SRC_URI[layer-shell-qt6.sha256sum] = "' + "c" * 64 + '"\n'
        )
        output = self.plasma.build_final_list()
        lines = output.read_text().splitlines()
        self.assertEqual(len(lines), 2)
        self.assertTrue(all('"' + "a" * 64 + '"' in line for line in lines))

    def test_invalid_baseline_never_produces_output(self):
        self.cache.save_release(self.release())
        (utils.INPUT_DIR / "kf6-checksums.bbclass").write_text("# comment\n")
        with self.assertRaises(ValueError):
            self.frameworks.build_final_list()
        self.assertFalse((utils.OUTPUT_DIR / "frameworks").exists())

    def test_generate_with_no_missing_packages(self):
        self.cache.save_release(self.release("plasma", "6.8.3"))

        (utils.INPUT_DIR / "plasma6-checksums.bbclass").write_text(
            'SRC_URI[layer-shell-qt6-kde6.sha256sum] = "' + "b" * 64 + '"\n'
        )

        output = self.plasma.build_final_list()
        missing = output.parent / "missing-plasma.bbclass"

        self.assertTrue(output.is_file())
        self.assertFalse(missing.exists())

        self.assertEqual(
            output.read_text(),
            'SRC_URI[layer-shell-qt6-kde6.sha256sum] = "' + "a" * 64 + '"\n'
        )

    def test_generate_removes_stale_missing_manifest(self):
        self.cache.save_release(self.release("plasma", "6.8.3"))

        (utils.INPUT_DIR / "plasma6-checksums.bbclass").write_text(
            'SRC_URI[layer-shell-qt6-kde6.sha256sum] = "' + "b" * 64 + '"\n'
        )

        missing = utils.OUTPUT_DIR / "plasma" / "6.8.3" / "missing-plasma.bbclass"
        missing.parent.mkdir(parents=True, exist_ok=True)
        missing.write_text(
            'SRC_URI[obsolete6.sha256sum] = "' + "c" * 64 + '"\n'
        )

        self.plasma.build_final_list()

        self.assertFalse(missing.exists())

if __name__ == "__main__":
    unittest.main()
