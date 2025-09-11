# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KOpeningHours"
SUMMARY = "Library for parsing and evaluating OSM opening hours expressions"
HOMEPAGE = "https://invent.kde.org/libraries/kopeninghours"
LICENSE = "LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.0-or-later.txt;md5=6d2d9952d88b50a51a5c73dc431d06c7"

inherit cmake_kdeapp
inherit python3native

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/kopeninghours-25.08.0.tar.xz"
SRC_URI[sha256sum] = "94bae44259d7566b2a7c26411f3200fc40298bb1fc65d48060aa6a210afc4d50"

DEPENDS += " \
    kholidays6 \
    ki18n6 \
    \
    qtbase6 \
    \
    flex \
    bison-native \
    boost \
"


FILES:${PN} += " \
    ${libdir}/${PYTHON_DIR}/site-packages/PyKOpeningHours/* \
"
