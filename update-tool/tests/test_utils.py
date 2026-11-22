"""Offline unit tests for KDE mirror and configuration utilities."""

import json
import tempfile
import unittest
from pathlib import Path
from unittest.mock import Mock, patch

from kde_oe_update import utils


class TestUtils(unittest.TestCase):
    def setUp(self):
        self.temp = tempfile.TemporaryDirectory()
        self.addCleanup(self.temp.cleanup)
        self.original_input = utils.INPUT_DIR
        self.original_versions = utils.VERSION_FILE
        self.addCleanup(setattr, utils, "INPUT_DIR", self.original_input)
        self.addCleanup(setattr, utils, "VERSION_FILE", self.original_versions)
        root = Path(self.temp.name)
        utils.INPUT_DIR = root / "in"
        utils.INPUT_DIR.mkdir()
        utils.VERSION_FILE = root / "versions.json"

    def test_input_is_independent_of_version_file(self):
        with self.assertRaises(FileNotFoundError):
            utils.has_input("plasma")
        (utils.INPUT_DIR / "plasma6-checksums.bbclass").write_text('SRC_URI[x.sha256sum] = "' + 'a'*64 + '"\n')
        self.assertTrue(utils.has_input("plasma"))
        with self.assertRaises(FileNotFoundError):
            utils.local_versions()

    def test_selected_versions(self):
        versions = {
            "frameworks": " 6.32.0 ", "plasma": "6.8.3",
            "release_services": "26.08.3", "plasma_wayland_protocols": "1.23.0",
        }
        utils.VERSION_FILE.write_text(json.dumps(versions))
        self.assertEqual(utils.local_versions()["frameworks"], "6.32.0")

    def test_missing_version_key(self):
        utils.VERSION_FILE.write_text('{"frameworks": "6.32.0"}')
        with self.assertRaisesRegex(ValueError, "Missing version keys"):
            utils.local_versions()

    def test_source_urls(self):
        self.assertEqual(utils.get_source_url("frameworks", "6.32.0"),
                         "https://download.kde.org/stable/frameworks/6.32/")
        self.assertEqual(utils.get_source_url("plasma", "6.8.3"),
                         "https://download.kde.org/stable/plasma/6.8.3/")
        self.assertEqual(utils.get_source_url("release_services", "26.08.3"),
                         "https://download.kde.org/stable/release-service/26.08.3/src/")

    def test_invalid_version_rejected(self):
        with self.assertRaises(ValueError):
            utils.get_source_url("plasma", "../oops")

    def test_parse_req(self):
        html = '''<div class="container"><div class="card my-4">
        <div class="table card-body mb-0 table-striped"><table><tbody>
        <tr><td>heading</td></tr><tr><td>parent</td></tr>
        <tr><td>icon</td><td><a href="karchive-6.32.0.tar.xz">karchive-6.32.0.tar.xz</a></td></tr>
        </tbody></table></div></div></div>'''
        response = Mock(text=html, url="https://download.kde.org/stable/frameworks/6.32/")
        with patch.object(utils.requests, "get", return_value=response) as get:
            items = utils.parse_req(response.url)
        self.assertEqual(items[0]["name"], "karchive-6.32.0.tar.xz")
        self.assertEqual(items[0]["url"], response.url + "karchive-6.32.0.tar.xz")
        get.assert_called_once_with(response.url, timeout=30)

    def test_packages_explicit_version(self):
        listing = [
            {"name": "karchive-6.32.0.tar.xz", "url": "https://example.org/karchive-6.32.0.tar.xz"},
            {"name": "karchive-6.32.0.tar.xz.sha256", "url": "https://example.org/sha"},
            {"name": "kconfig-6.31.0.tar.xz", "url": "https://example.org/old"},
        ]
        with patch.object(utils, "parse_req", return_value=listing) as parser:
            packages = utils.get_packages("frameworks", "6.32.0")
        self.assertEqual([item["name"] for item in packages], ["karchive"])
        self.assertEqual(packages[0]["sha_url"], "https://example.org/karchive-6.32.0.tar.xz.sha256")
        parser.assert_called_once_with("https://download.kde.org/stable/frameworks/6.32/")

    def test_get_sha(self):
        archive = "karchive-6.32.0.tar.xz"
        package = {"name": "karchive", "url": f"https://example.org/{archive}",
                   "sha_url": f"https://example.org/{archive}.sha256"}
        response = Mock(text=f"{'A'*64}  {archive}\n")
        with patch.object(utils.requests, "get", return_value=response):
            self.assertEqual(utils.get_sha([package])[0]["sha"], "a"*64)

    def test_get_sha_wrong_archive(self):
        package = {"name": "karchive", "url": "https://example.org/karchive-6.32.0.tar.xz",
                   "sha_url": "https://example.org/karchive.sha256"}
        with patch.object(utils.requests, "get", return_value=Mock(text="a"*64 + "  wrong.tar.xz")):
            with self.assertRaisesRegex(ValueError, "Checksum filename mismatch"):
                utils.get_sha([package])

    def test_version_order_numeric(self):
        listing = [{"name": x, "url": "https://example.org/" + x} for x in
                   ["6.9/", "6.10/", "6.8/", "Parent Directory", "6.10/"]]
        with patch.object(utils, "parse_req", return_value=listing):
            self.assertEqual(utils.get_version("frameworks"), ["6.8", "6.9", "6.10"])
