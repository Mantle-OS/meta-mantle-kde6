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

inherit rs6

DEPENDS:append = " \
    kholidays6 \
    \
    flex \
    bison-native \
    boost \
"

FILES:${PN} += " \
    ${libdir}/${PYTHON_DIR}/site-packages/PyKOpeningHours/* \
"
