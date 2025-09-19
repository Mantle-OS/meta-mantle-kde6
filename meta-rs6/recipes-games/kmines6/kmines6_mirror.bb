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

inherit rs6

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    ki18n \
"

DEPENDS:append = " \
    kconfigwidgets6 \
    kcrash6 \
    ktextwidgets6 \
    kwidgetsaddons6 \
    kxmlgui6 \
    libkdegames6 \
"
