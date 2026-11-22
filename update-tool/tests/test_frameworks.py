"""Offline tests for KDE Frameworks release-family configuration."""

import tempfile
import unittest
from pathlib import Path

from kde_oe_update.cache import Cache
from kde_oe_update.frameworks import Frameworks
from kde_oe_update import utils


class TestFrameworks(unittest.TestCase):
    def setUp(self):
        self.temp = tempfile.TemporaryDirectory()
        self.addCleanup(self.temp.cleanup)
        self.frameworks = Frameworks(cache=Cache(Path(self.temp.name) / "cache"))

    def test_family_configuration(self):
        self.assertEqual(self.frameworks.base_name, "frameworks")
        self.assertEqual(self.frameworks.base_url, utils.FRAMEWORKS_URL)
        self.assertEqual(self.frameworks.input_class, "kf6-checksums.bbclass")

    def test_frameworks_directory(self):
        self.assertEqual(self.frameworks._indexed_directory("6.32.0"), "6.32")
        self.assertEqual(
            utils.get_source_url("frameworks", "6.32.0"),
            "https://download.kde.org/stable/frameworks/6.32/",
        )

    def test_standard_names(self):
        self.assertEqual(self.frameworks.local_names("karchive6"), "karchive")
        self.assertEqual(self.frameworks.local_names("extra-cmake-modules"), "extra-cmake-modules")

    def test_frameworks_overrides(self):
        for name, archive in self.frameworks.override_map.items():
            with self.subTest(name=name):
                self.assertEqual(self.frameworks.local_names(name), archive)

    def test_override_does_not_change_local_key(self):
        self.assertEqual(self.frameworks.local_names("ecm-kf6"), "extra-cmake-modules")
        self.assertEqual(self.frameworks.local_names("phonon-backend-vlc-kf6"), "phonon-backend-vlc")
