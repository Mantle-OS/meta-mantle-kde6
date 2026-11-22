"""Offline tests for the Plasma release-family configuration."""

import tempfile
import unittest
from pathlib import Path

from kde_oe_update import utils
from kde_oe_update.cache import Cache
from kde_oe_update.plasma import Plasma


class TestPlasma(unittest.TestCase):
    def setUp(self):
        self.temporary = tempfile.TemporaryDirectory()
        self.addCleanup(self.temporary.cleanup)
        self.plasma = Plasma(cache=Cache(Path(self.temporary.name) / "cache"))

    def test_family_identity(self):
        self.assertEqual(self.plasma.base_name, "plasma")
        self.assertEqual(self.plasma.base_url, utils.PLASMA_URL)
        self.assertEqual(self.plasma.input_class, "plasma6-checksums.bbclass")

    def test_source_url(self):
        self.assertEqual(
            utils.get_source_url("plasma", "6.8.3"),
            "https://download.kde.org/stable/plasma/6.8.3/",
        )
        self.assertEqual(self.plasma._indexed_directory("6.8.3"), "6.8.3")

    def test_normal_package_names(self):
        self.assertEqual(self.plasma.local_names("kwin6"), "kwin")
        self.assertEqual(self.plasma.local_names("plasma-desktop6"), "plasma-desktop")
        self.assertEqual(self.plasma.local_names("discover6"), "discover")
        self.assertEqual(self.plasma.local_names("xdg-desktop-portal-kde6"), "xdg-desktop-portal-kde")

    def test_explicit_overrides(self):
        self.assertEqual(self.plasma.local_names("kde6-cli-tools-kde6"), "kde-cli-tools")
        self.assertEqual(self.plasma.local_names("polkit-kde6-agent-1-kde6"), "polkit-kde-agent-1")

    def test_name_resolution_does_not_rewrite_checksum_keys(self):
        original = "kde6-cli-tools-kde6"
        self.assertEqual(self.plasma.local_names(original), "kde-cli-tools")
        self.assertEqual(original, "kde6-cli-tools-kde6")
        self.assertEqual(self.plasma.local_names("layer-shell-qt6-kde6"), "layer-shell-qt6-kde")
        self.assertEqual(self.plasma.local_names("layer-shell-qt6"), "layer-shell-qt")


if __name__ == "__main__":
    unittest.main()
