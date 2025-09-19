# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Ocean Sound Theme"
HOMEPAGE = "https://invent.kde.org/plasma/ocean-sound-theme"
LICENSE = "BSD-2-Clause & CC-BY-SA-4.0"
LIC_FILES_CHKSUM = " \
    file://LICENSES/BSD-2-Clause.txt;md5=000c37ead9822c219d89c4971eeb5163 \
    file://LICENSES/CC-BY-SA-4.0.txt;md5=9eb9fa51640fba9c2dbeb93fe711ae80 \
"

inherit cmake_plasma

SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/ocean-sound-theme-6.4.4.tar.xz"
SRC_URI[sha256sum] = "ddd87c9021947a5eff06e2f92566b9d81fe06ea444430745e896d647994e37eb"

