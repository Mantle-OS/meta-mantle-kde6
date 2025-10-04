# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Blinken"
DESCRIPTION = "Memory Enhancement Game"
HOMEPAGE = "https://edu.kde.org/blinken/"
SECTION = "games"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881"

inherit rs6

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
    ki18n \
"

DEPENDS:append = " \
    kxmlgui6 \
    kguiaddons6 \
    kdoctools6-native kdoctools6 \
    kdbusaddons6 \
    kcrash6 \
    \
    qtmultimedia6 \
    qtdeclarative6 \
    qtsvg6 \
"

PACKAGECONFIG[qml-desktop] = "-DQML_VERSION_FOR_DESKTOP=ON, -DQML_VERSION_FOR_DESKTOP=OFF, kirigami6 kirigami6-addons"

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/config.kcfg \
    ${datadir}/blinken \
"
