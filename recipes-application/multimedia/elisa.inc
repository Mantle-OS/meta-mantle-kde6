# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

SUMMARY = "Elisa"
DESCRIPTION = "Elisa music player"
HOMEPAGE = "https://apps.kde.org/de/elisa"
LICENSE = "LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-3.0-only.txt;md5=c51d3eef3be114124d11349ca0d7e117"
PR = "r0"

DEPENDS += " \
    baloo \
    qtbase6 \
    qtdeclarative6 \
    qtmultimedia6 \
    kirigami6 \
    kirigami6-addons6 \
    ki18n6 \
    kdeclarative6 \
    kfilemetadata6 \
    kxmlgui6 \
    kconfigwidgets6 \
    kcrash \
    kdbusaddons6 \
    qqc2-desktop-style6 \
"

inherit cmake_kdeapp
inherit kf6-kcoreaddons
inherit kf6-kconfig
inherit kf6-kauth
inherit mime-xdg
