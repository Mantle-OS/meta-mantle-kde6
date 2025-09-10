# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

SUMMARY = "libkdegames"
DESCRIPTION = "Common code and data for many KDE games"
HOMEPAGE = "https://invent.kde.org/games/libkdegames"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881"
PR = "r0"

DEPENDS += " \
    qtbase6 \
    qtdeclarative6 \
    karchive6 \
    kconfigwidgets6 \
    kguiaddons6 \
    kcolorscheme \
    kiconthemes6 \
    knewstuff6 \
    kdnssd \
    kxmlgui6 \
    openal-soft \
"

inherit cmake_kdeapp
inherit kf6-kconfig

FILES:${PN} += " \
    ${datadir}/carddecks/* \
"
