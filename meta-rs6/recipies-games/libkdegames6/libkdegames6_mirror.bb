# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

SUMMARY = "libkdegames"
DESCRIPTION = "Common code and data for many KDE games"
HOMEPAGE = "https://invent.kde.org/games/libkdegames"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881"

inherit cmake_kdeapp
inherit kf6-kcoreaddons kf6-kconfig

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/libkdegames-25.08.0.tar.xz"
SRC_URI[sha256sum] = "15579343d5b06608115e8e95473b80bc91de8841b1a79584f5615a91fe57e2f7"

DEPENDS += " \
    qtbase6 \
    qtdeclarative6 \
    karchive6 \
    kconfigwidgets6 \
    kguiaddons6 \
    kcolorscheme \
    kiconthemes6 \
    knewstuff6 \
    kdnssd6 \
    kxmlgui6 \
    openal-soft \
"

FILES:${PN} += " \
    ${datadir}/carddecks/* \
"

