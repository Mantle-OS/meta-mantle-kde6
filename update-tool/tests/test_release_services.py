"""Offline tests for KDE Release Service release-family configuration."""

import tempfile
import unittest
from pathlib import Path

from kde_oe_update import utils
from kde_oe_update.cache import Cache
from kde_oe_update.release_services import ReleaseServices


class TestReleaseServices(unittest.TestCase):
    def setUp(self):
        self.temp = tempfile.TemporaryDirectory()
        self.addCleanup(self.temp.cleanup)
        self.release_services = ReleaseServices(cache=Cache(Path(self.temp.name) / "cache"))

    def test_family_configuration(self):
        self.assertEqual(self.release_services.base_name, "release_services")
        self.assertEqual(self.release_services.base_url, utils.RELEASE_SERVICES_URL)
        self.assertEqual(self.release_services.input_class, "rs6-checksums.bbclass")

    def test_release_service_source_directory(self):
        self.assertEqual(self.release_services._indexed_directory("26.08.3"), "26.08.3")
        self.assertEqual(
            utils.get_source_url("release_services", "26.08.3"),
            "https://download.kde.org/stable/release-service/26.08.3/src/",
        )

    def test_standard_names(self):
        self.assertEqual(self.release_services.local_names("dolphin6"), "dolphin")
        self.assertEqual(self.release_services.local_names("kate6"), "kate")
        self.assertEqual(self.release_services.local_names("okular"), "okular")

    def test_release_service_override(self):
        self.assertEqual(self.release_services.local_names("libkexiv2-kf6"), "libkexiv2")

    def test_override_does_not_modify_key(self):
        name = "libkexiv2-kf6"
        self.assertEqual(self.release_services.local_names(name), "libkexiv2")
        self.assertEqual(name, "libkexiv2-kf6")
