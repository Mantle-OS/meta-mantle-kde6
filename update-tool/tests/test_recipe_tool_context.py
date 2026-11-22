"""Offline tests for KDE recipe tool startup and selection."""

import argparse
import json
import tempfile
import unittest
from pathlib import Path
from unittest.mock import patch

from kde_oe_recipe_tool import (
    initialize_context,
    load_release,
    select_module,
    select_package,
    select_packages,
    select_version,
)
from kde_oe_update import utils
from kde_oe_update.cache import Cache
from kde_oe_update.schema import Package, Release, SCHEMA_VERSION


class TestRecipeToolContext(unittest.TestCase):
    def setUp(self):
        temp = tempfile.TemporaryDirectory()
        self.addCleanup(temp.cleanup)

        root = Path(temp.name)

        for key, path in {
            "CACHE_DIR": root / "cache",
            "INPUT_DIR": root / "in",
            "OUTPUT_DIR": root / "out",
            "VERSION_FILE": root / "versions.json",
        }.items():
            previous = getattr(utils, key)
            self.addCleanup(setattr, utils, key, previous)
            setattr(utils, key, path)

        utils.CACHE_DIR.mkdir()
        utils.INPUT_DIR.mkdir()
        utils.OUTPUT_DIR.mkdir()

        utils.VERSION_FILE.write_text(json.dumps({
            "frameworks": "6.32.0",
            "plasma": "6.8.3",
            "release_services": "26.08.3",
            "plasma_wayland_protocols": "1.23.0",
        }))

        self.cache = Cache(utils.CACHE_DIR)

        self.package = self.make_package("kapidox", "6.32.0")
        self.release = self.make_release(
            "frameworks", "6.32.0", [self.package]
        )
        self.cache.save_release(self.release)

        (utils.INPUT_DIR / "kf6-checksums.bbclass").write_text(
            'SRC_URI[historical6.sha256sum] = "' + "b" * 64 + '"\n'
        )

    @staticmethod
    def make_package(name: str, version: str) -> Package:
        url = f"https://example.org/{name}-{version}.tar.xz"
        return Package(name=name, url=url, sha_url=url + ".sha256", sha="a" * 64)

    @staticmethod
    def make_release(family: str, version: str, packages: list[Package]) -> Release:
        return Release(
            schema_version=SCHEMA_VERSION,
            family=family,
            version=version,
            source_url=utils.get_source_url(family, version),
            fetched_at="2026-11-17T12:00:00Z",
            packages=packages,
        )

    def args(self, **kwargs):
        defaults = {
            "type": "frameworks",
            "kde_version": "6.32.0",
            "name": "kapidox",
            "generate": False,
            "download": True,
            "download_all": False,
            "cleanup": False,
            "cleanup_all": False,
        }
        defaults.update(kwargs)
        return argparse.Namespace(**defaults)

    def test_select_module_by_argument(self):
        self.assertEqual(select_module("frameworks"), "frameworks")
        self.assertEqual(select_module("plasma"), "plasma")
        self.assertEqual(select_module("release-services"), "release-services")

        with self.assertRaises(ValueError):
            select_module("invalid")

    def test_select_module_interactively(self):
        with patch("builtins.input", return_value="1"):
            self.assertEqual(select_module(), "frameworks")

    def test_select_module_all_requires_permission(self):
        with self.assertRaises(ValueError):
            select_module("all")
        self.assertEqual(select_module("all", allow_all=True), "all")

    def test_select_module_all_interactively(self):
        with patch("builtins.input", return_value="4"):
            self.assertEqual(select_module(allow_all=True), "all")

    def test_select_version_uses_configured_default(self):
        with patch("builtins.input", return_value=""):
            self.assertEqual(select_version("frameworks"), "6.32.0")

    def test_select_version_accepts_explicit_version(self):
        with patch("builtins.input", return_value=""):
            self.assertEqual(select_version("frameworks", "6.32.0"), "6.32.0")

    def test_load_release_from_cache(self):
        release = load_release(self.cache, "frameworks", "6.32.0")
        self.assertEqual(release, self.release)

    def test_load_release_rejects_missing_version(self):
        with self.assertRaises(Exception):
            load_release(self.cache, "frameworks", "6.31.0")

    def test_select_package_by_name(self):
        self.assertEqual(select_package([self.package], "kapidox"), self.package)

    def test_select_package_interactively(self):
        with patch("builtins.input", return_value="1"):
            self.assertEqual(select_package([self.package]), self.package)

    def test_select_package_rejects_unknown_name(self):
        with self.assertRaises(ValueError):
            select_package([self.package], "unknown")

    def test_select_packages_all_returns_sorted_queue(self):
        z = self.make_package("z-package", "6.32.0")
        a = self.make_package("a-package", "6.32.0")
        with patch("builtins.input", side_effect=AssertionError("unexpected prompt")):
            selected = select_packages([z, self.package, a], all_packages=True)
        self.assertEqual([package.name for package in selected], ["a-package", "kapidox", "z-package"])

    def test_select_packages_by_names_preserves_request_order(self):
        other = self.make_package("kmime", "6.32.0")
        selected = select_packages([self.package, other], names=["kmime", "kapidox"])
        self.assertEqual(selected, [other, self.package])

    def test_select_packages_rejects_duplicate_names(self):
        with self.assertRaisesRegex(ValueError, "Duplicate package selection"):
            select_packages([self.package], names=["kapidox", "kapidox"])

    def test_select_packages_rejects_names_with_all(self):
        with self.assertRaises(ValueError):
            select_packages([self.package], names=["kapidox"], all_packages=True)

    def test_initialize_context_from_cached_release(self):
        with patch("builtins.input", return_value=""):
            context = initialize_context(self.args(), self.cache)

        self.assertEqual(context.family, "frameworks")
        self.assertEqual(context.version, "6.32.0")
        self.assertEqual(context.release, self.release)
        self.assertEqual(context.package, self.package)

    def test_initialize_context_cleanup_skips_package_selection(self):
        with patch("builtins.input", return_value=""):
            context = initialize_context(
                self.args(cleanup=True, download=False), self.cache
            )
        self.assertIsNone(context.package)

    def test_initialize_context_cleanup_all_skips_selection(self):
        with patch("builtins.input", side_effect=AssertionError("unexpected prompt")):
            context = initialize_context(
                self.args(cleanup_all=True, download=False), self.cache
            )
        self.assertIsNone(context)

    def test_initialize_context_download_all_one_family(self):
        other = self.make_package("kmime", "6.32.0")
        release = self.make_release("frameworks", "6.32.0", [other, self.package])
        self.cache.save_release(release)

        with patch("builtins.input", return_value=""):
            contexts = initialize_context(
                self.args(download=False, download_all=True, name=None), self.cache
            )

        self.assertIsInstance(contexts, list)
        self.assertEqual([context.package.name for context in contexts], ["kapidox", "kmime"])
        self.assertTrue(all(context.family == "frameworks" for context in contexts))
        self.assertTrue(all(context.release == release for context in contexts))

    def test_initialize_context_download_all_every_family(self):
        plasma = self.make_package("aurorae", "6.8.3")
        services = self.make_package("akonadi", "26.08.3")
        self.cache.save_release(self.make_release("plasma", "6.8.3", [plasma]))
        self.cache.save_release(self.make_release("release_services", "26.08.3", [services]))

        with patch("builtins.input", side_effect=["", "", ""]) as prompts:
            contexts = initialize_context(
                self.args(
                    type="all", kde_version=None, name=None,
                    download=False, download_all=True,
                ),
                self.cache,
            )

        self.assertEqual(prompts.call_count, 3)
        self.assertEqual(
            [(context.family, context.version, context.package.name) for context in contexts],
            [
                ("frameworks", "6.32.0", "kapidox"),
                ("plasma", "6.8.3", "aurorae"),
                ("release-services", "26.08.3", "akonadi"),
            ],
        )

    def test_initialize_context_download_all_rejects_package_name(self):
        with self.assertRaisesRegex(ValueError, "--name"):
            initialize_context(
                self.args(download=False, download_all=True), self.cache
            )

    def test_initialize_context_all_rejects_single_version(self):
        with self.assertRaisesRegex(ValueError, "--kde-version"):
            initialize_context(
                self.args(
                    type="all", name=None, download=False, download_all=True,
                ),
                self.cache,
            )


if __name__ == "__main__":
    unittest.main()
