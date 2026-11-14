# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "The intelligent phonetic input method library"
HOMEPAGE = ""
SECTION = "libs"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

inherit kde6-cmake

SRC_URI = " \
    gitsm://github.com/chewing/libchewing.git;branch=release/0.10.x;protocol=https \
    file://0001-bb-rust-for-cli-and-api-no-corrosion.patch \
"

SRCREV = "93a9a24bae6173f23c620df314b55c596c7622dd"

DEPENDS:append = " \
    ncurses \
    chewing-capi chewing-capi-native \
"

PACKAGECONFIG[shared] = "-DBUILD_SHARED_LIBS=ON, -DBUILD_SHARED_LIBS=OFF, "
PACKAGECONFIG[sqlite3] = "-DWITH_SQLITE3=true, -DWITH_SQLITE3=false, sqlite3"
#PACKAGECONFIG[valgrind] = "-DUSE_VALGRIND=true, -DUSE_VALGRIND=false, valgrind"

PACKAGECONFIG:append = " \
    shared \
    sqlite3 \
"

EXTRA_OECMAKE:append = " -DCHEWING_CLI=${STAGING_BINDIR_NATIVE}/chewing-cli"

FILES:${PN} += " \
    ${datadir}/libchewing \
"
