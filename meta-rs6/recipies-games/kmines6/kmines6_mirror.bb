# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

SUMMARY = "KMines"
DESCRIPTION = "KMines is the classic Minesweeper game"
HOMEPAGE = "https://invent.kde.org/games/kmines"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881"

inherit cmake_kdeapp
inherit kf6-kcoreaddons
inherit kf6-kconfig

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/kmines-25.08.0.tar.xz"
SRC_URI[sha256sum] = "31968a4773da1004f335347b68f00ebb132dc9bd20f8f95c0d90dd94db7480f9"

DEPENDS += " \
    kconfigwidgets6 \
    kcrash6 \
    ki18n6 \
    ktextwidgets6 \
    kwidgetsaddons6 \
    kxmlgui6 \
    libkdegames6 \
"
