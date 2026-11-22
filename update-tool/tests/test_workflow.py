"""Offline integration tests for KDE discovery, selection, and generation."""

import json
import runpy
import tempfile
import unittest
from pathlib import Path
from unittest.mock import patch

from kde_oe_update import run, _resolve_frameworks_version
from kde_oe_update import utils
from kde_oe_update.cache import Cache, CacheError
from kde_oe_update.schema import Package, Release, SCHEMA_VERSION, VersionIndex


class TestWorkflow(unittest.TestCase):
    def setUp(self):
        temporary = tempfile.TemporaryDirectory()
        self.addCleanup(temporary.cleanup)
        self.root = Path(temporary.name)
        for name, path in {
            "SCRIPT_DIR": self.root,
            "CACHE_DIR": self.root / "cache",
            "INPUT_DIR": self.root / "in",
            "OUTPUT_DIR": self.root / "out",
            "VERSION_FILE": self.root / "versions.json",
        }.items():
            previous = getattr(utils, name)
            setattr(utils, name, path)
            self.addCleanup(setattr, utils, name, previous)
        utils.INPUT_DIR.mkdir()
        utils.VERSION_FILE.write_text(json.dumps({
            "frameworks": "6.31.0", "plasma": "6.8.2",
            "release_services": "26.08.2", "plasma_wayland_protocols": "1.23.0",
        }))
        self.cache = Cache(utils.CACHE_DIR)

    def index(self):
        return VersionIndex(SCHEMA_VERSION, "2026-11-16T19:00:00Z",
                            ["6.31", "6.32"], ["6.8.2", "6.8.3"], ["26.08.2", "26.08.3"])

    def release(self, family, version, name):
        url = f"https://example.org/{name}-{version}.tar.xz"
        return Release(SCHEMA_VERSION, family, version,
                       utils.get_source_url(family, version), "2026-11-16T19:00:00Z",
                       [Package(name, url, url + ".sha256", "a" * 64)])

    def test_discovery_all_preserves_family_index_and_creates_placeholders(self):
        answers = {"frameworks": ["6.31", "6.32"], "plasma": ["6.8.3"],
                   "release_services": ["26.08.3"]}
        with patch.object(utils, "get_version", side_effect=lambda family: answers[family]) as fetch:
            run("all", update_versions=True, cache=self.cache)
        self.assertEqual(fetch.call_count, 3)
        index = self.cache.load_version_index()
        self.assertEqual(index.frameworks, ["6.31", "6.32"])
        self.assertEqual(index.plasma, ["6.8.3"])
        self.assertTrue((self.cache.root / "frameworks" / "6.32").is_dir())
        self.assertFalse(self.cache.has_release("frameworks", "6.32"))
        self.assertEqual(len(self.cache.load_status().families), 3)

    def test_discovery_throttled_and_forceable(self):
        with patch.object(utils, "get_version", return_value=["6.8.3"]) as fetch:
            run("plasma", update_versions=True, cache=self.cache)
            run("plasma", update_versions=True, cache=self.cache)
            self.assertEqual(fetch.call_count, 1)
            run("plasma", update_versions=True, force=True, cache=self.cache)
            self.assertEqual(fetch.call_count, 2)

    def test_partial_discovery_keeps_other_families(self):
        self.cache.save_version_index(self.index())
        with patch.object(utils, "get_version", return_value=["6.8.4"]):
            run("plasma", update_versions=True, force=True, cache=self.cache)
        index = self.cache.load_version_index()
        self.assertEqual(index.frameworks, ["6.31", "6.32"])
        self.assertEqual(index.release_services, ["26.08.2", "26.08.3"])
        self.assertEqual(index.plasma, ["6.8.4"])

    def test_frameworks_resolution_uses_actual_archive_versions(self):
        names = ["karchive-6.32.0.tar.xz", "karchive-6.32.2.tar.xz",
                 "karchive-6.31.9.tar.xz", "karchive-6.32.2.tar.xz.sig"]
        with patch.object(utils, "parse_req", return_value=[{"name": n} for n in names]) as fetch:
            version = _resolve_frameworks_version("6.32", self.cache)
        self.assertEqual(version, "6.32.2")
        fetch.assert_called_once_with(utils.FRAMEWORKS_URL + "6.32/")

    def test_cli_latest_frameworks_uses_real_full_version(self):
        cli = runpy.run_path(str(Path(__file__).resolve().parents[1] / "kde-oe-update.py"))
        from kde_oe_update.frameworks import Frameworks
        from kde_oe_update.plasma import Plasma
        updaters = [Frameworks(self.cache), Plasma(self.cache)]
        with patch("builtins.input", return_value="latest"), \
             patch.dict(cli["select_versions"].__globals__, {
                 "_frameworks_release_versions": lambda _: ["6.32.0", "6.32.2"]
             }):
            selected = cli["select_versions"](self.index(), updaters, self.cache)
        self.assertEqual(selected, {"frameworks": "6.32.2", "plasma": "6.8.3"})

    def test_cli_advanced_frameworks_can_choose_patch(self):
        cli = runpy.run_path(str(Path(__file__).resolve().parents[1] / "kde-oe-update.py"))
        from kde_oe_update.frameworks import Frameworks
        updaters = [Frameworks(self.cache)]
        answers = iter(["advanced", "6.32", "6.32.0"])
        with patch("builtins.input", side_effect=lambda *_: next(answers)), \
             patch.dict(cli["select_versions"].__globals__, {
                 "_frameworks_release_versions": lambda _: ["6.32.0", "6.32.2"]
             }):
            selected = cli["select_versions"](self.index(), updaters, self.cache)
        self.assertEqual(selected, {"frameworks": "6.32.0"})

    def test_cli_all_returns_all_without_frameworks_requests(self):
        cli = runpy.run_path(str(Path(__file__).resolve().parents[1] / "kde-oe-update.py"))
        from kde_oe_update.frameworks import Frameworks
        with patch("builtins.input", return_value="3"), \
             patch.dict(cli["select_versions"].__globals__, {
                 "_frameworks_release_versions": lambda _: self.fail("unexpected resolution")
             }):
            selected = cli["select_versions"](self.index(), [Frameworks(self.cache)], self.cache)
        self.assertEqual(selected, "all")

    def test_cache_all_reuses_snapshots_and_preserves_selected_versions(self):
        self.cache.save_version_index(self.index())
        before = utils.VERSION_FILE.read_bytes()
        releases = {
            ("frameworks", "6.31.0"): "karchive",
            ("frameworks", "6.32.0"): "karchive",
            ("frameworks", "6.32.2"): "karchive",
            ("plasma", "6.8.2"): "kwin",
            ("plasma", "6.8.3"): "kwin",
            ("release_services", "26.08.2"): "dolphin",
            ("release_services", "26.08.3"): "dolphin",
        }
        for (family, version), name in releases.items():
            self.cache.save_release(self.release(family, version, name))
        with patch("kde_oe_update._frameworks_release_versions", side_effect=lambda directory: {
            "6.31": ["6.31.0"], "6.32": ["6.32.0", "6.32.2"]
        }[directory]) as resolve, \
             patch.object(utils, "get_packages", side_effect=AssertionError("unexpected network")):
            run("all", update_cache=True, cache=self.cache, select_versions=lambda *_: "all")
        self.assertEqual(resolve.call_count, 2)
        self.assertEqual(utils.VERSION_FILE.read_bytes(), before)

    def test_cache_without_index_fails_actionably(self):
        with self.assertRaisesRegex(CacheError, "--update-versions"):
            run("plasma", update_cache=True, cache=self.cache,
                select_versions=lambda *_: {"plasma": "6.8.3"})

    def test_invalid_selection_does_not_modify_versions(self):
        self.cache.save_version_index(self.index())
        before = utils.VERSION_FILE.read_bytes()
        with self.assertRaises(ValueError):
            run("all", update_cache=True, cache=self.cache,
                select_versions=lambda *_: {"frameworks": "6.32.0", "plasma": "6.99.0",
                                            "release_services": "26.08.3"})
        self.assertEqual(utils.VERSION_FILE.read_bytes(), before)

    def test_cache_selection_commits_all_versions_together(self):
        self.cache.save_version_index(self.index())
        selected = {"frameworks": "6.32.0", "plasma": "6.8.3", "release_services": "26.08.3"}
        releases = {family: self.release(family, version, "karchive" if family == "frameworks"
                                        else "kwin" if family == "plasma" else "dolphin")
                    for family, version in selected.items()}
        for release in releases.values():
            self.cache.save_release(release)
        with patch.object(utils, "get_packages", side_effect=AssertionError("unexpected network")):
            run("all", update_cache=True, cache=self.cache, select_versions=lambda *_: selected)
        self.assertEqual({k: utils.local_versions()[k] for k in selected}, selected)
        self.assertEqual(utils.local_versions()["plasma_wayland_protocols"], "1.23.0")

    def test_generate_is_offline_and_checks_all_baselines_before_writing(self):
        self.cache.save_release(self.release("plasma", "6.8.2", "kwin"))
        path = utils.INPUT_DIR / "plasma6-checksums.bbclass"
        path.write_text('SRC_URI[kwin6.sha256sum] = "' + "b" * 64 + '"\n')
        with patch.object(utils, "parse_req", side_effect=AssertionError("unexpected network")), \
             patch.object(utils, "get_packages", side_effect=AssertionError("unexpected network")), \
             patch.object(utils, "get_sha", side_effect=AssertionError("unexpected network")):
            run("plasma", generate=True, cache=self.cache)
        output = utils.OUTPUT_DIR / "plasma" / "6.8.2" / "plasma6-checksums.bbclass"
        self.assertEqual(output.read_text(), 'SRC_URI[kwin6.sha256sum] = "' + "a" * 64 + '"\n')
        self.assertTrue(path.read_text().endswith("b" * 64 + '"\n'))

    def test_generation_preflight_all_families(self):
        self.cache.save_release(self.release("plasma", "6.8.2", "kwin"))
        (utils.INPUT_DIR / "plasma6-checksums.bbclass").write_text(
            'SRC_URI[kwin6.sha256sum] = "' + "b" * 64 + '"\n')
        with self.assertRaises(FileNotFoundError):
            run("all", generate=True, cache=self.cache)
        self.assertFalse(utils.OUTPUT_DIR.exists())


if __name__ == "__main__":
    unittest.main()
