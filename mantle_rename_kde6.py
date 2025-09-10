#!/usr/bin/env python3
import argparse
import re
import sys
from pathlib import Path
from typing import List, Dict, Optional, Set

# find . -name "*.bb"
LIST_RAW = r"""
./recipes-playground/plasma-camera_git.bb
./recipes-playground/plasma-settings_25.07.0.bb
./recipes-playground/kirigami-addons_1.9.0.bb
./recipes-playground/kquickimageeditor_0.5.1.bb
./recipes-playground/washipad_git.bb
./recipes-playground/futuresql_git.bb
./recipes-playground/arkade_git.bb
./recipes-plasma-bigscreen/plasma-bigscreen_5.27.80.bb
./recipes-plasma-bigscreen/plasma-remotecontrollers_5.27.80.bb
./recipes-plasma-bigscreen/aura-browser_5.27.80.bb
./recipes-plasma-bigscreen/plank-player_5.27.80.bb
./recipes-meta-oe-backports/walnascar-support/wayland-protocols/wayland-protocols_1.44.bb
./recipes-meta-oe-backports/styhead-support/wayland-protocols/wayland-protocols_1.39.bb
./recipes-meta-oe-backports/scrathgap-support/libinput/libinput_1.28.1.bb
./recipes-meta-oe-backports/nanbield-support/appstream_1.0.0.bb
./recipes-meta-oe-backports/mickledore-support/wayland/wayland_1.22.0.bb
./recipes-extragear/sdk/heaptrack_1.5.0.bb
./recipes-extragear/edu/gcompris_25.0.bb
./recipes-plasma/layer-shell-qt_6.4.4.bb
./recipes-plasma/kglobalacceld_6.4.4.bb
./recipes-plasma/polkit-kde-agent-1_6.4.4.bb
./recipes-plasma/kscreen_6.4.4.bb
./recipes-plasma/kwallet-pam_6.4.4.bb
./recipes-plasma/plasma-nm_6.4.4.bb
./recipes-plasma/milou_6.4.4.bb
./recipes-plasma/plasma-vault_6.4.4.bb
./recipes-plasma/libkscreen_6.4.4.bb
./recipes-plasma/breeze_6.4.4.bb
./recipes-plasma/kactivitymanagerd_6.4.4.bb
./recipes-plasma/plasma5support_6.4.4.bb
./recipes-plasma/discover_6.4.4.bb
./recipes-plasma/powerdevil_6.4.4.bb
./recipes-plasma/plasma-pa_6.4.4.bb
./recipes-plasma/kde-cli-tools_6.4.4.bb
./recipes-plasma/krdp_6.4.4.bb
./recipes-plasma/plasma-systemmonitor_6.4.4.bb
./recipes-plasma/xdg-desktop-portal-kde_6.4.4.bb
./recipes-plasma/plasma-nano_6.4.4.bb
./recipes-plasma/plasma-disks_6.4.4.bb
./recipes-plasma/qqc2-breeze-style_6.4.4.bb
./recipes-plasma/plasma-integration_6.4.4.bb
./recipes-plasma/plasma-workspace_6.4.4.bb
./recipes-plasma/ksystemstats_6.4.4.bb
./recipes-plasma/kdecoration_6.4.4.bb
./recipes-plasma/libksysguard_6.4.4.bb
./recipes-plasma/libplasma_6.4.4.bb
./recipes-plasma/plasma-welcome_6.4.4.bb
./recipes-plasma/plasma-activities-stats_6.4.4.bb
./recipes-plasma/kwayland_6.4.4.bb
./recipes-plasma/plasma-firewall_6.4.4.bb
./recipes-plasma/plasma-mobile_6.4.4.bb
./recipes-plasma/ocean-sound-theme_6.4.4.bb
./recipes-plasma/kwin_6.4.4.bb
./recipes-plasma/ksystemstats_6.2.4.bb
./recipes-plasma/plasma-desktop_6.4.4.bb
./recipes-plasma/plasma-activities_6.4.4.bb
./recipes-plasma/plasma-sdk_6.4.4.bb
./recipes-plasma/bluedevil_6.4.4.bb
./recipes-plasma/systemsettings_6.4.4.bb
./recipes-plasma/kscreenlocker_6.4.4.bb
./recipes-plasma/kpipewire_6.4.4.bb
./recipes-plasma/kgamma_6.4.4.bb
./recipes-integration/images/core-image-plasma-bigscreen.bb
./recipes-integration/images/core-image-plasma-mobile.bb
./recipes-integration/packagegroups/packagegroup-plasma-mobile.bb
./recipes-integration/packagegroups/packagegroup-plasma-desktop-workspace.bb
./recipes-integration/packagegroups/packagegroup-plasma-full.bb
./recipes-integration/packagegroups/packagegroup-plasma-bigscreen-workspace.bb
./recipes-integration/packagegroups/packagegroup-kde-applications.bb
./recipes-integration/packagegroups/packagegroup-plasma-mobile-workspace.bb
./recipes-integration/packagegroups/packagegroup-plasma-mobile-applications.bb
./recipes-support/libpinyin_2.6.2.bb
./recipes-support/kpeoplevcard_0.2.bb
./recipes-support/libqalculate_4.2.0.bb
./recipes-support/libphonenumber_8.13.17.bb
./recipes-support/simplelogin_git.bb
./recipes-support/qmltermwidget_0.2.0.bb
./recipes-support/sddm/sddm_0.21.0.bb
./recipes-support/libchewing_0.5.1.bb
./recipes-support/packagekit_1.3.0.bb
./recipes-support/ddcutil_git.bb
./recipes-support/qml-box2d_git.bb
./recipes-support/kimageannotator_0.7.1.bb
./recipes-support/qcoro_0.12.0.bb
./recipes-support/ttf-noto-cjk_2019.03.30.bb
./recipes-support/qtkeychain_0.15.0.bb
./recipes-support/ttf-noto_2019.01.11.bb
./recipes-support/ttf-noto-emoji_2.047.bb
./recipes-support/kcolorpicker_0.3.1.bb
./recipes-support/maliit-keyboard-qt6_2.3.1.bb
./recipes-support/packagekit-qt_1.1.2.bb
./recipes-support/maliit-framework-qt6_2.3.1.bb
./recipes-support/libdisplay-info_0.2.0.bb
./recipes-support/pulseaudio-qt_1.7.0.bb
./recipes-support/libreoffice-dictionaries_25.2.3.bb
./recipes-application/libraries/kweathercore_25.08.0.bb
./recipes-application/libraries/kpublictransport_25.08.0.bb
./recipes-application/utilities/kdialog_25.08.0.bb
./recipes-application/utilities/dolphin_25.08.0.bb
./recipes-application/utilities/konsole_25.08.0.bb
./recipes-application/system/kjournald_25.08.0.bb
./recipes-application/plasma-mobile/kweather_25.08.0.bb
./recipes-application/plasma-mobile/kasts_25.08.0.bb
./recipes-application/plasma-mobile/ktrip_25.08.0.bb
./recipes-application/plasma-mobile/kongress_25.08.0.bb
./recipes-application/plasma-mobile/kclock_25.08.0.bb
./recipes-application/plasma-mobile/alligator_25.08.0.bb
./recipes-application/plasma-mobile/koko_25.08.0.bb
./recipes-application/plasma-mobile/qmlkonsole_25.08.0.bb
./recipes-application/plasma-mobile/krecorder_25.08.0.bb
./recipes-application/sdk/kirigami-gallery_25.08.0.bb
./recipes-application/education/kwordquiz_25.08.0.bb
./recipes-application/education/kanagram_25.08.0.bb
./recipes-application/education/khangman_25.08.0.bb
./recipes-application/education/kdeedu-data_25.08.0.bb
./recipes-application/education/libkeduvocdocument_25.08.0.bb
./recipes-application/education/blinken_25.08.0.bb
./recipes-application/education/marble_25.08.0.bb
./recipes-application/multimedia/dragon_25.08.0.bb
./recipes-application/multimedia/elisa_25.08.0.bb
./recipes-application/network/krfb_25.08.0.bb
./recipes-application/network/angelfish_25.08.0.bb
./recipes-application/network/kdeconnect-kde_25.08.0.bb
./recipes-application/pim/kosmindoormap_25.08.0.bb
./recipes-application/pim/kopeninghours_25.08.0.bb
./recipes-application/pim/kmime_25.08.0.bb
./recipes-application/pim/kpkpass_25.08.0.bb
./recipes-application/pim/itinerary_25.08.0.bb
./recipes-application/pim/kitinerary_25.08.0.bb
./recipes-application/graphics/okular_25.08.0.bb
./recipes-application/graphics/gwenview_25.08.0.bb
./recipes-application/games/libkdegames_25.08.0.bb
./recipes-application/games/kmines_25.08.0.bb
""".strip()

# from meta-qt6/scripts/convert-to-new-modules.sh
QT_NAMES = """
qt3d qt5compat qtapplicationmanager qtbase qtcharts qtcoap qtconnectivity qtdatavis3d qtdeclarative
qtdeviceutilities qtgrpc qthttpserver qtimageformats qtinsighttracker qtinterfaceframework qtlanguageserver
qtlocation qtlottie qtmqtt qtmultimedia qtnetworkauth qtopcua qtpdf qtpositioning qtquick3d qtquick3dphysics
qtquickdesigner-components qtquicktimeline qtremoteobjects qtscxml qtsensors qtserialbus qtserialport
qtshadertools qtspeech qtsvg qttools qttranslations qtvirtualkeyboard qtvncserver qtwayland qtwebchannel
qtwebengine qtwebsockets qtwebview
""".split()

# meta-kf6/mantle_kf6_rename.py
KF6_NAMES = """
attica bluez-qt extra-cmake-modules karchive kbookmarks kcalendarcore kcodecs kcmutils kconfig kconfigwidgets
kcoreaddons kdbusaddons kdeclarative kded kdeedu-data kdesu kfilemetadata kglobalaccel kguiaddons ki18n
kiconthemes kidletime kimageformats kio kirigami kirigami-addons kitemmodels kitemviews kjobwidgets kjsonrpc
knewstuff knotifyconfig kpackage kparts kpeople kpkpass kpty kquickcharts krunner kservice kstatusnotifieritem
ksvg ktexteditor ktexttemplate ktextwidgets kunitconversion kwallet kwayland kwidgetsaddons kwindowsystem
kxmlgui modemmanager-qt networkmanager-qt plasma5support qqc2-desktop-style solid sonnet syntax-highlighting
threadweaver
""".split()

# carryover from meta-kf6/mantle_kf6_rename.py
OVERRIDE_SUFFIX_MAP: Dict[str, str] = {
    # carry-over from meta-kf6 rename
    "oxygen-icons": "oxygen-icons-kf6",
    "phonon-backend-vlc": "phonon-backend-vlc-kf6",
    "libkexiv2": "libkexiv2-kf6",
    "phonon": "phonon-kf6",
    "plasma-wayland-protocols": "plasma-wayland-protocols-kf6",
    "qca": "qca-kf6",
    "zxing-cpp": "zxing-cpp-kf6",
    "libdmtx": "libdmtx-kf6",
    "extra-cmake-modules": "ecm-kf6",
    # oddballs
    "layer-shell-qt": "layer-shell-qt6-kde6",
    "packagekit-qt": "packagekit-qt6-kde6",
    "polkit-kde-agent-1": "polkit-kde6-agent-1-kde6",
    "pulseaudio-qt": "pulseaudio-qt6-kde6",
    "xdg-desktop-portal-kde": "xdg-desktop-portal-kde6",
    "kde-cli-tools": "kde6-cli-tools-kde6",
    "packagegroup-kde-applications": "packagegroup-applications-kde6",
    "kdeconnect-kde": "kdeconnect-kde6"
}

# do NOT rename dirs
EXCLUDE_DIR_PREFIXES = [
    "recipes-meta-oe-backports/"
]

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
        rel = str(p).lstrip("./")
        alter = not any(rel.startswith(prefix) for prefix in EXCLUDE_DIR_PREFIXES)
        items.append(Entry(name=name, filename=str(p), bbappend=None, alter=alter))
    # longest first avoid kwin/kwindowsystem
    items.sort(key=lambda e: len(e["name"]), reverse=True)
    return items

# tok
def replace_tokens_safe(text: str, mapping: Dict[str, str]) -> str:
    before = r'(^|[ \t"\'=:,;\|\<\>\+\{\}\[\]\(\)`-])'
    after  = r'(?=$|[ \t"\'=:,;\|\<\>\+\{\}\[\]\(\)`-])'
    for old, new in mapping.items():
        text = re.sub(before + re.escape(old) + after, r'\1' + new, text)
    return text

# lex
DEP_KEYS = re.compile(r'^\s*(DEPENDS|RDEPENDS|PROVIDES|RPROVIDES|PACKAGECONFIG|PACKAGES)\b')

def rewrite_content(buf: str, mapping: Dict[str, str]) -> str:
    lines = buf.splitlines(keepends=True)
    out: List[str] = []
    in_dep_quote = False

    for line in lines:
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
                    if line[i] == '"' and (i == 0 or line[i-1] != '\\'):
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
                    rebuilt += inside + '"'
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

    # Pass A: LIST_RAW
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

    # Pass B: any other .bb(Qt/KF/app names)
    for p in root.rglob("*.bb"):
        if p.resolve() in processed:
            continue
        rel = str(p).lstrip("./")
        if any(rel.startswith(prefix) for prefix in EXCLUDE_DIR_PREFIXES):
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
        rel = str(p).lstrip("./")
        if any(rel.startswith(prefix) for prefix in EXCLUDE_DIR_PREFIXES):
            continue

        buf = p.read_text(encoding="utf-8")
        newbuf = rewrite_content(buf, mapping)
        if newbuf != buf:
            p.write_text(newbuf, encoding="utf-8")
            print(f"[inc] {p}  (edited)")

def rename_bbappend_files(root: Path, mapping: Dict[str, str], delete_file: bool) -> None:
    for p in root.rglob("*.bbappend"):
        rel = str(p).lstrip("./")
        if any(rel.startswith(prefix) for prefix in EXCLUDE_DIR_PREFIXES):
            continue

        # Old base name is before first "_" if present; otherwise strip suffix
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
    ap = argparse.ArgumentParser(
        description="mantle_rename_kde6.py — Suffix KDE6/KF6/Qt6 recipe names and rewrite deps (multi-line aware)."
    )
    ap.add_argument(
        "--delete-file",
        action="store_true",
        help="Delete original .bb/.bbappend after writing suffixed copies",
    )
    args = ap.parse_args()

    root = Path(".").resolve()
    entries = build_entries_from_list(LIST_RAW)

    # Mmapping from:
    #  - explicit file list (entries)
    #  - QT_NAMES defined in meta-qt6/scripts
    #  - KF6_NAMES defined in meta-kf6
    mapping: Dict[str, str] = {}

    def add_name(old: str):
        if not old:
            return
        if old.endswith("6") or old.endswith("-qt6") or old.endswith("-kf6"):
            return
        mapping[old] = suffixed_name(old)

    # explicit(find . -name *.bb)
    for e in entries:
        add_name(e.name)

    # meta-qt6/scripts/convert-to-new-modules.sh
    for q in QT_NAMES:
        add_name(q.strip())

    # meta-kf6/mantle_rename_kf6.yml
    for k in KF6_NAMES:
        add_name(k.strip())

    rename_bb_files(entries, mapping, root, delete_file=args.delete_file)
    rename_inc_files(root, mapping)
    rename_bbappend_files(root, mapping, delete_file=args.delete_file)

    print("\nDictionary preview:")
    for e in entries:
        print(f'{{name="{e.name}", filename="{e.filename}", bbappend={e.bbappend}, alter={e.alter}}}')

    preview_sets = [
        ("QT_NAMES", QT_NAMES),
        ("KF6_NAMES", KF6_NAMES),
    ]
    for label, arr in preview_sets:
        shown = 0
        for nm in arr:
            if nm and nm in mapping:
                print(f'{{set="{label}", name="{nm}", mapped_to="{mapping[nm]}"}}')
                shown += 1
            if shown >= 50:
                print(f"... ({label} preview truncated)")
                break

if __name__ == "__main__":
    sys.exit(main())
