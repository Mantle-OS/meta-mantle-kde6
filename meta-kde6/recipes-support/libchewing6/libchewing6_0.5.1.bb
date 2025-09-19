# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"
SUMMARY = "The intelligent phonetic input method library"

inherit kde6-cmake

SRC_URI = " \
    git://github.com/chewing/libchewing.git;nobranch=1;protocol=https \
    file://0001-Cross-compilation-options.patch \
    file://0002-Disable-genkeystroke-testapp.patch \
"
SRCREV = "547dcbd4962b6da319bbf98506e9dc86ef248a05"
S = "${WORKDIR}/git"

DEPENDS:append = " \
    ncurses \
    sqlite3 \
"

DEPENDS:append:class-nativesdk = " \
    ${PN}-native \
"
DEPENDS:append:class-target = " \
    ${PN}-native \
"

EXTRA_OECMAKE += "-DUSE_TOOLS_FROM_PATH=ON"
EXTRA_OECMAKE:class-native += "-DINSTALL_TOOLS=ON"

BBCLASSEXTEND = "native"
