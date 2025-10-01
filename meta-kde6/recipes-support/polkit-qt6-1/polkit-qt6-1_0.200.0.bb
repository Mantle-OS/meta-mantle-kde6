# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017, 2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Polkit-qt6-1 PolicyKit API with Qt-styled API"
HOMEPAGE = "https://api.kde.org/polkit-qt-1/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.0-or-later.txt;md5=da48810c4ddf8e49efa031294a26b98c"

inherit kde6-cmake 
inherit gobject-introspection

SRC_URI = "git://invent.kde.org/libraries/polkit-qt-1.git;nobranch=1;protocol=https"
SRCREV = "aa1c6c414ec021fd32e30492c00bb13347e228db"

S = "${WORKDIR}/git"

DEPENDS:append = " \
    polkit \
    glib-2.0 \
"

PACKAGECONFIG[examples] = "-DBUILD_EXAMPLES=ON,  -DBUILD_EXAMPLES=OFF, "
PACKAGECONFIG[tests] = "-DBUILD_TEST=ON, -DBUILD_TEST=OFF, "
