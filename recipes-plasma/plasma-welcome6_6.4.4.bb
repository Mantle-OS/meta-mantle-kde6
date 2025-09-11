# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Welcome Center"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-welcome"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"
PR = "r0"

inherit cmake_plasma
inherit mime-xdg

SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/plasma-welcome-6.4.4.tar.xz"
SRC_URI[sha256sum] = "309e1b0d8d16491276b00657c24197c6200c2a3242c3f530a22307689b5d23b1"

DEPENDS = " \
    kcmutils6 \
    kconfig6 \
    kconfigwidgets6 \
    kdbusaddons6 \
    ki18n6 \
    kio6 \
    kservice6 \
    kirigami6 kirigami6-addons6 \
    kjobwidgets6 \
    knewstuff6 \
    ksvg6 \
    kwindowsystem6 \
    libplasma6 \
    qtbase6 \
    qtdeclarative6 \
"

