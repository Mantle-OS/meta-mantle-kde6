# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KOpeningHours"
SUMMARY = "Library for parsing and evaluating OSM opening hours expressions"
HOMEPAGE = "https://invent.kde.org/libraries/kopeninghours"
LICENSE = "LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.0-or-later.txt;md5=6d2d9952d88b50a51a5c73dc431d06c7"

inherit rs6

SRC_URI:append = " \
    file://0001-update-py-install-path.patch \
"

DEPENDS:append = " \
    qtdeclarative6 \
    \
    kholidays6 \
    \
    python3-native \
    flex-native \
    bison-native \
    boost \
"

PACKAGECONFIG[validator-only] = "-DVALIDATOR_ONLY=ON, -DVALIDATOR_ONLY=OFF, "

PACKAGECONFIG:append = " \
    ki18n \
    kconfig \
"

FILES:${PN} += " \
    ${PYTHON_SITEPACKAGES_DIR}/PyKOpeningHours \
"
