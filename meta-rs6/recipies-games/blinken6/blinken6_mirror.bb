# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
SUMMARY = "Blinken"
DESCRIPTION = "Memory Enhancement Game"
HOMEPAGE = "https://edu.kde.org/blinken/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881"
PR = "r0"

inherit cmake_kdeapp
inherit kf6-kcoreaddons
inherit kf6-kconfig
inherit kf6-kauth

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/blinken-25.08.0.tar.xz"
SRC_URI[sha256sum] = "659bc69e811c7247e801c0696066bfb8cc9f70e223f401a58b08bb6bd83d35a4"

DEPENDS += " \
    kcrash6 \
    ki18n6 \
    kio6 \
    phonon \
    kcoreaddons6 kcoreaddons6-native \
    kconfig6 kconfig6-native \
    kauth6 kauth6-native polkit-qt-1 \
"

