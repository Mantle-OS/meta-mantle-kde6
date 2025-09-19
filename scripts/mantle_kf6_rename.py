#!/usr/bin/env python3
import argparse
import re
import sys
from pathlib import Path
from typing import List, Dict, Optional, Set

# find . -name "*.bb"
LIST_RAW = r"""
./recipes-kdesupport/oxygen-icons_6.1.0.bb
./recipes-kdesupport/polkit-qt-1_0.200.0.bb
./recipes-kdesupport/phonon-backend-vlc_0.12.0.bb
./recipes-kdesupport/libkexiv2_25.08.0.bb
./recipes-kdesupport/phonon_4.12.0.bb
./recipes-kdesupport/plasma-wayland-protocols_1.18.0.bb
./recipes-support/qca_2.3.10.bb
./recipes-support/zxing-cpp_2.3.10.bb
./recipes-support/libdmtx_0.7.8.bb
./recipes-kf6/tier4/frameworkintegration_6.17.0.bb
./recipes-kf6/tier2/kfilemetadata_6.17.0.bb
./recipes-kf6/tier2/kdoctools_6.17.0.bb
./recipes-kf6/tier2/kpackage_6.17.0.bb
./recipes-kf6/tier2/kstatusnotifieritem_6.17.0.bb
./recipes-kf6/tier2/knotifications_6.17.0.bb
./recipes-kf6/tier2/kcolorscheme_6.17.0.bb
./recipes-kf6/tier2/kcompletion_6.17.0.bb
./recipes-kf6/tier2/syndication_6.17.0.bb
./recipes-kf6/tier2/kauth_6.17.0.bb
./recipes-kf6/tier2/kcrash_6.17.0.bb
./recipes-kf6/tier2/kcontacts_6.17.0.bb
./recipes-kf6/tier2/kunitconversion_6.17.0.bb
./recipes-kf6/tier2/kimageformats_6.17.0.bb
./recipes-kf6/tier2/kjobwidgets_6.17.0.bb
./recipes-kf6/tier2/kpty_6.17.0.bb
./recipes-kf6/tier3/kxmlgui_6.17.0.bb
./recipes-kf6/tier3/kiconthemes_6.17.0.bb
./recipes-kf6/tier3/kded_6.17.0.bb
./recipes-kf6/tier3/ktextwidgets_6.17.0.bb
./recipes-kf6/tier3/baloo_6.17.0.bb
./recipes-kf6/tier3/kconfigwidgets_6.17.0.bb
./recipes-kf6/tier3/kparts_6.17.0.bb
./recipes-kf6/tier3/kdesu_6.17.0.bb
./recipes-kf6/tier3/kio_6.17.0.bb
./recipes-kf6/tier3/kcmutils_6.17.0.bb
./recipes-kf6/tier3/ktexteditor_6.17.0.bb
./recipes-kf6/tier3/kwallet_6.17.0.bb
./recipes-kf6/tier3/ksvg_6.17.0.bb
./recipes-kf6/tier3/kservice_6.17.0.bb
./recipes-kf6/tier3/purpose_6.17.0.bb
./recipes-kf6/tier3/kpeople_6.17.0.bb
./recipes-kf6/tier3/kdav_6.17.0.bb
./recipes-kf6/tier3/knewstuff_6.17.0.bb
./recipes-kf6/tier3/knotifyconfig_6.17.0.bb
./recipes-kf6/tier3/krunner_6.17.0.bb
./recipes-kf6/tier3/qqc2-desktop-style_6.17.0.bb
./recipes-kf6/tier3/kdeclarative_6.17.0.bb
./recipes-kf6/tier3/kbookmarks_6.17.0.bb
./recipes-kf6/packagegroups/packagegroup-kf6-full.bb
./recipes-kf6/tier1/kitemviews_6.17.0.bb
./recipes-kf6/tier1/bluez-qt_6.17.0.bb
./recipes-kf6/tier1/ki18n_6.17.0.bb
./recipes-kf6/tier1/karchive_6.17.0.bb
./recipes-kf6/tier1/kwidgetsaddons_6.17.0.bb
./recipes-kf6/tier1/prison_6.17.0.bb
./recipes-kf6/tier1/kdnssd_6.17.0.bb
./recipes-kf6/tier1/kitemmodels_6.17.0.bb
./recipes-kf6/tier1/kquickcharts_6.17.0.bb
./recipes-kf6/tier1/kconfig_6.17.0.bb
./recipes-kf6/tier1/kholidays_6.17.0.bb
./recipes-kf6/tier1/kirigami_6.17.0.bb
./recipes-kf6/tier1/kidletime_6.17.0.bb
./recipes-kf6/tier1/kglobalaccel_6.17.0.bb
./recipes-kf6/tier1/threadweaver_6.17.0.bb
./recipes-kf6/tier1/kwidgetsaddons_6.4.1.bb
./recipes-kf6/tier1/kcalendarcore_6.17.0.bb
./recipes-kf6/tier1/sonnet_6.17.0.bb
./recipes-kf6/tier1/modemmanager-qt_6.17.0.bb
./recipes-kf6/tier1/kuserfeedback_6.17.0.bb
./recipes-kf6/tier1/breeze-icons_6.17.0.bb
./recipes-kf6/tier1/solid_6.17.0.bb
./recipes-kf6/tier1/kplotting_6.17.0.bb
./recipes-kf6/tier1/attica_6.17.0.bb
./recipes-kf6/tier1/kcoreaddons_6.17.0.bb
./recipes-kf6/tier1/networkmanager-qt_6.17.0.bb
./recipes-kf6/tier1/ktexttemplate_6.17.0.bb
./recipes-kf6/tier1/kcodecs_6.17.0.bb
./recipes-kf6/tier1/syntax-highlighting_6.17.0.bb
./recipes-kf6/tier1/kwindowsystem_6.17.0.bb
./recipes-kf6/tier1/kdbusaddons_6.17.0.bb
./recipes-kf6/tier1/kguiaddons_6.17.0.bb
./recipes-kf6/tier1/extra-cmake-modules_6.17.0.bb
""".strip()

# from meta-qt6/scripts
QT_NAMES = """
qt3d qt5compat qtapplicationmanager qtbase qtcharts qtcoap qtconnectivity qtdatavis3d qtdeclarative
qtdeviceutilities qtgrpc qthttpserver qtimageformats qtinsighttracker qtinterfaceframework qtlanguageserver
qtlocation qtlottie qtmqtt qtmultimedia qtnetworkauth qtopcua qtpdf qtpositioning qtquick3d qtquick3dphysics
qtquickdesigner-components qtquicktimeline qtremoteobjects qtscxml qtsensors qtserialbus qtserialport
qtshadertools qtspeech qtsvg qttools qttranslations qtvirtualkeyboard qtvncserver qtwayland qtwebchannel
qtwebengine qtwebsockets qtwebview
""".split()

OVERRIDE_SUFFIX_MAP: Dict[str, str] = {
    "oxygen-icons": "oxygen-icons-kf6",
    "phonon-backend-vlc": "phonon-backend-vlc-kf6",
    "libkexiv2": "libkexiv2-kf6",
    "phonon": "phonon-kf6",
    "plasma-wayland-protocols": "plasma-wayland-protocols-kf6",
    "qca": "qca-kf6",
    "zxing-cpp": "zxing-cpp-kf6",
    "libdmtx": "libdmtx-kf6",
    "extra-cmake-modules": "ecm-kf6"
}

# data
class Entry(dict):
    __getattr__ = dict.__getitem__
    __setattr__ = dict.__setitem__

def parse_name_from_bb(path: Path) -> str:
    stem = path.name
    return stem.split("_", 1)[0] if "_" in stem else stem.replace(".bb", "")

def build_entries_from_list(raw: str) -> List[Entry]:
    items: List[Entry] = []
    for line in raw.splitlines():
        s = line.strip()
        if not s:
            continue
        p = Path(s)
        name = parse_name_from_bb(p)
        items.append(Entry(name=name, filename=str(p), bbappend=None, alter=True))
    items.sort(key=lambda e: len(e["name"]), reverse=True)  # longest first
    return items

# token
def replace_tokens_safe(text: str, mapping: Dict[str, str]) -> str:
    before = r'(^|[ \t"\'=:,;\|\<\>\+\{\}\[\]\(\)`-])'
    after  = r'(?=$|[ \t"\'=:,;\|\<\>\+\{\}\[\]\(\)`-])'
    for old, new in mapping.items():
        text = re.sub(before + re.escape(old) + after, r'\1' + new, text)
    return text

#lex
DEP_KEYS = re.compile(r'^\s*(DEPENDS|RDEPENDS|PROVIDES|RPROVIDES|PACKAGECONFIG|PACKAGES)\b')

def rewrite_content(buf: str, mapping: Dict[str, str]) -> str:
    lines = buf.splitlines(keepends=True)
    out: List[str] = []

    in_dep_quote = False
    for line in lines:
        original_line = line

        # global targeted forms first (safe anywhere)
        for old, new in mapping.items():
            line = re.sub(rf'(\$\{{PN\}}-){re.escape(old)}\b', r'\1' + new, line)
            line = re.sub(rf'(\bpn-){re.escape(old)}\b', r'\1' + new, line)
            line = re.sub(rf'(\bSRCREV(?::\w+)?[:_](?:pn-)?){re.escape(old)}\b', r'\1' + new, line)

        cursor = 0
        rebuilt = ""

        while cursor < len(line):
            if not in_dep_quote:
                m = DEP_KEYS.match(line[cursor:]) if cursor == 0 else None
                if m:
                    qpos = line.find('"', cursor)
                    if qpos == -1:
                        rebuilt += line[cursor:]
                        cursor = len(line)
                        break
                    rebuilt += line[cursor:qpos+1]
                    cursor = qpos + 1
                    in_dep_quote = True
                    continue
                else:
                    rebuilt += line[cursor:]
                    cursor = len(line)
                    break
            else:
                i = cursor
                closing = -1
                while i < len(line):
                    ch = line[i]
                    if ch == '"':
                        if i == 0 or line[i-1] != '\\':
                            closing = i
                            break
                    i += 1

                if closing == -1:
                    segment = line[cursor:]
                    segment = replace_tokens_safe(segment, mapping)
                    rebuilt += segment
                    cursor = len(line)
                    break
                else:
                    inside = line[cursor:closing]
                    inside = replace_tokens_safe(inside, mapping)
                    rebuilt += inside + '"'  # add the closing quote
                    cursor = closing + 1
                    in_dep_quote = False
                    rebuilt += line[cursor:]
                    cursor = len(line)
                    break

        out.append(rebuilt)

    return "".join(out)

def suffixed_name(old: str) -> str:
    return OVERRIDE_SUFFIX_MAP.get(old, f"{old}6")

def new_recipe_filename(src: Path, old: str, new: str) -> Path:
    name = src.name
    if "_" in name:
        newfname = name.replace(old + "_", new + "_", 1)
    else:
        if name.endswith(".bb"):
            newfname = name.replace(old + ".bb", new + ".bb", 1)
        else:  # .bbappend
            newfname = name.replace(old + ".bbappend", new + ".bbappend", 1)
    return src.with_name(newfname)

def rename_bb_files(entries: List[Entry], mapping: Dict[str, str], root: Path, delete_file: bool) -> None:
    processed: Set[Path] = set()

    for e in entries:
        if not e.alter:
            continue
        src = Path(e.filename)
        if not (src.is_file() and src.suffix == ".bb"):
            continue
        old = parse_name_from_bb(src)
        new = mapping.get(old)
        if not new:
            continue
        dst = new_recipe_filename(src, old, new)
        buf = src.read_text(encoding="utf-8")
        newbuf = rewrite_content(buf, mapping)
        dst.write_text(newbuf, encoding="utf-8")
        print(f"[bb]  {src}  ->  {dst}")
        processed.add(src.resolve())
        if delete_file and src != dst and src.exists():
            src.unlink()
            print(f"[del] {src}")

    for p in root.rglob("*.bb"):
        if p.resolve() in processed:
            continue
        old = parse_name_from_bb(p)
        if old not in mapping:
            continue
        new = mapping[old]
        dst = new_recipe_filename(p, old, new)
        buf = p.read_text(encoding="utf-8")
        newbuf = rewrite_content(buf, mapping)
        dst.write_text(newbuf, encoding="utf-8")
        print(f"[bb]  {p}  ->  {dst}")
        if delete_file and p != dst and p.exists():
            p.unlink()
            print(f"[del] {p}")

def rename_inc_files(root: Path, mapping: Dict[str, str]) -> None:
    for p in root.rglob("*.inc"):
        buf = p.read_text(encoding="utf-8")
        newbuf = rewrite_content(buf, mapping)
        if newbuf != buf:
            p.write_text(newbuf, encoding="utf-8")
            print(f"[inc] {p}  (edited)")

def rename_bbappend_files(root: Path, mapping: Dict[str, str], delete_file: bool) -> None:
    for p in root.rglob("*.bbappend"):
        old = p.name.split("_", 1)[0] if "_" in p.name else p.name.replace(".bbappend", "")
        new = mapping.get(old)
        buf = p.read_text(encoding="utf-8")
        newbuf = rewrite_content(buf, mapping)
        if new:
            dst = new_recipe_filename(p, old, new)
            dst.write_text(newbuf, encoding="utf-8")
            print(f"[bba] {p}  ->  {dst}")
            if delete_file and p != dst and p.exists():
                p.unlink()
                print(f"[del] {p}")
        elif newbuf != buf:
            p.write_text(newbuf, encoding="utf-8")
            print(f"[bba] {p}  (edited)")

def main():
    ap = argparse.ArgumentParser(description="Suffix KF6/Qt6 recipe names with '6' and rewrite deps (multi-line aware).")
    ap.add_argument("--delete-file", action="store_true",
                    help="Delete original .bb/.bbappend after writing suffixed copies (methods 1 & 3).")
    args = ap.parse_args()

    root = Path(".").resolve()
    entries = build_entries_from_list(LIST_RAW)

    # Build mapping from entries + explicit Qt names (with overrides)
    mapping: Dict[str, str] = {}
    for e in entries:
        old = e.name
        if old.endswith("6") or old.endswith("-qt6"):
            continue
        mapping[old] = suffixed_name(old)
    for q in QT_NAMES:
        q = q.strip()
        if not q or q.endswith("6") or q.endswith("-qt6"):
            continue
        mapping[q] = suffixed_name(q)

    # Run methods
    rename_bb_files(entries, mapping, root, delete_file=args.delete_file)
    rename_inc_files(root, mapping)
    rename_bbappend_files(root, mapping, delete_file=args.delete_file)

    # Dict preview (for audit)
    print("\nDictionary preview:")
    for e in entries:
        print(f'{{name="{e.name}", filename="{e.filename}", bbappend={e.bbappend}, alter={e.alter}}}')
    for q in sorted(set(QT_NAMES)):
        if q:
            print(f'{{name="{q}", filename="(auto-discovered)", bbappend=None, alter=True}}')

if __name__ == "__main__":
    sys.exit(main())
