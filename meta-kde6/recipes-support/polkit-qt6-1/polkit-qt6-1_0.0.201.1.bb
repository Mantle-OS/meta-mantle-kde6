SUMMARY = "Polkit-qt6-1 PolicyKit API with Qt-styled API"
HOMEPAGE = "https://api.kde.org/polkit-qt-1/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.0-or-later.txt;md5=da48810c4ddf8e49efa031294a26b98c"

inherit kde6-cmake gobject-introspection

SRC_URI = "git://invent.kde.org/libraries/polkit-qt-1.git;nobranch=1;protocol=https"
SRCREV = "328a4c8615c7207e1297b776026657a77c6a71b6"

DEPENDS:append = " \
    polkit \
    glib-2.0 \
"

PACKAGECONFIG[examples] = "-DBUILD_EXAMPLES=ON,  -DBUILD_EXAMPLES=OFF, "
PACKAGECONFIG[tests] = "-DBUILD_TEST=ON, -DBUILD_TEST=OFF, "
