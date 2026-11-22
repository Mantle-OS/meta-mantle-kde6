"""Offline tests for strict checksum fragments and upstream comparisons."""

import tempfile
import unittest
from pathlib import Path

from kde_oe_update.manifest import (
    ChecksumEntry, ManifestError, compare_checksums, parse_checksums,
    render_checksums, write_checksums,
)
from kde_oe_update.schema import Package, Release, SCHEMA_VERSION


SHA_A = "a" * 64
SHA_B = "b" * 64
SHA_C = "c" * 64


def release(*packages):
    return Release(
        schema_version=SCHEMA_VERSION, family="plasma", version="6.8.3",
        source_url="https://example.org/plasma/6.8.3/",
        fetched_at="2026-11-16T19:00:00Z",
        packages=[Package(name=name, url=f"https://example.org/{name}.tar.xz",
                          sha_url=f"https://example.org/{name}.tar.xz.sha256",
                          sha=sha) for name, sha in packages],
    )


def resolve(name):
    overrides = {
        "kde6-cli-tools-kde6": "kde-cli-tools",
        "layer-shell-qt6-kde6": "layer-shell-qt",
        "layer-shell-qt6": "layer-shell-qt",
    }
    return overrides.get(name, name[:-1] if name.endswith("6") else name)


class TestManifest(unittest.TestCase):
    def setUp(self):
        self.temp = tempfile.TemporaryDirectory()
        self.addCleanup(self.temp.cleanup)
        self.root = Path(self.temp.name)
        self.input = self.root / "in" / "plasma6-checksums.bbclass"
        self.input.parent.mkdir()

    def write_input(self, text):
        self.input.write_text(text, encoding="utf-8")
        return self.input

    def test_parse_order_and_normalize_sha(self):
        self.write_input(f'SRC_URI[breeze6.sha256sum] = "{SHA_A.upper()}"\n'
                         f'SRC_URI[bluedevil6.sha256sum] = "{SHA_B}"\n')
        self.assertEqual(parse_checksums(self.input), [
            ChecksumEntry("breeze6", SHA_A), ChecksumEntry("bluedevil6", SHA_B)])

    def test_input_must_exist(self):
        with self.assertRaises(ManifestError):
            parse_checksums(self.input)

    def test_empty_file_rejected(self):
        self.write_input("")
        with self.assertRaises(ManifestError):
            parse_checksums(self.input)

    def test_no_blank_lines_or_comments_or_indentation(self):
        for bad in ["\n", "# header\n", " SRC_URI[x.sha256sum] = \"" + SHA_A + "\"\n",
                    f'SRC_URI[x.sha256sum] = "{SHA_A}"\n\n', "SRCREV = \"test\"\n"]:
            with self.subTest(bad=bad):
                self.write_input(bad)
                with self.assertRaises(ManifestError):
                    parse_checksums(self.input)

    def test_malformed_and_duplicate_rejected(self):
        for bad in [f'SRC_URI[x.sha256sum = "{SHA_A}"\n',
                    'SRC_URI[x.sha256sum] = "bad"\n',
                    f'SRC_URI[x.sha256sum] = "{SHA_A}"\nSRC_URI[x.sha256sum] = "{SHA_B}"\n']:
            with self.subTest(bad=bad):
                self.write_input(bad)
                with self.assertRaises(ManifestError):
                    parse_checksums(self.input)

    def test_matched_and_missing(self):
        entries = [ChecksumEntry("breeze6", SHA_A)]
        result = compare_checksums(entries, release(("breeze", SHA_B), ("kwin", SHA_C)), resolve)
        self.assertEqual(result.entries, [ChecksumEntry("breeze6", SHA_B)])
        self.assertEqual([p.name for p in result.missing], ["kwin"])
        self.assertEqual(result.removed, [])

    def test_removed_retains_historical_checksum(self):
        entry = ChecksumEntry("retired6", SHA_A)
        result = compare_checksums([entry], release(("breeze", SHA_B)), resolve)
        self.assertEqual(result.entries, [entry])
        self.assertEqual(result.removed, [entry])
        self.assertEqual([p.name for p in result.missing], ["breeze"])

    def test_two_local_aliases_one_upstream_archive(self):
        entries = [ChecksumEntry("layer-shell-qt6-kde6", SHA_A),
                   ChecksumEntry("layer-shell-qt6", SHA_C)]
        result = compare_checksums(entries, release(("layer-shell-qt", SHA_B)), resolve)
        self.assertEqual(result.entries, [ChecksumEntry("layer-shell-qt6-kde6", SHA_B),
                                          ChecksumEntry("layer-shell-qt6", SHA_B)])
        self.assertEqual(result.missing, [])

    def test_family_specific_mapping(self):
        result = compare_checksums([ChecksumEntry("kde6-cli-tools-kde6", SHA_A)],
                                   release(("kde-cli-tools", SHA_C)), resolve)
        self.assertEqual(result.entries[0].sha, SHA_C)

    def test_bad_mapping_rejected(self):
        with self.assertRaises(ManifestError):
            compare_checksums([ChecksumEntry("breeze6", SHA_A)],
                              release(("breeze", SHA_B)), lambda _: "../breeze")

    def test_duplicate_upstream_package_rejected(self):
        with self.assertRaises(ManifestError):
            compare_checksums([], release(("breeze", SHA_A), ("breeze", SHA_B)), resolve)

    def test_render_only_checksums(self):
        entries = [ChecksumEntry("kwin6", SHA_A), ChecksumEntry("breeze6", SHA_B)]
        rendered = render_checksums(entries)
        self.assertEqual(rendered, f'SRC_URI[kwin6.sha256sum] = "{SHA_A}"\n'
                                   f'SRC_URI[breeze6.sha256sum] = "{SHA_B}"\n')

    def test_atomic_write_roundtrip(self):
        path = self.root / "out" / "plasma" / "6.8.3" / "plasma6-checksums.bbclass"
        write_checksums(path, [ChecksumEntry("kwin6", SHA_C)])
        self.assertEqual(parse_checksums(path), [ChecksumEntry("kwin6", SHA_C)])
        self.assertFalse(list(path.parent.glob("*.tmp")))

    def test_checksum_whitespace_normalization(self):
        self.write_input(
            f'SRC_URI[attica6.sha256sum]             = "{SHA_A}"\n'
            f'SRC_URI[bluez-qt6.sha256sum]\t\t= "{SHA_B}"\n'
            f'SRC_URI[karchive6.sha256sum]="{SHA_C}"\n'
        )

        entries = parse_checksums(self.input)

        self.assertEqual(entries, [
            ChecksumEntry("attica6", SHA_A),
            ChecksumEntry("bluez-qt6", SHA_B),
            ChecksumEntry("karchive6", SHA_C),
        ])

        self.assertEqual(
            render_checksums(entries),
            f'SRC_URI[attica6.sha256sum] = "{SHA_A}"\n'
            f'SRC_URI[bluez-qt6.sha256sum] = "{SHA_B}"\n'
            f'SRC_URI[karchive6.sha256sum] = "{SHA_C}"\n'
        )

if __name__ == "__main__":
    unittest.main()
