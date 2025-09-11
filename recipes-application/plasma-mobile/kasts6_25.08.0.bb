# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Kasts"
DESCRIPTION = "Kasts is a convergent podcast application."
HOMEPAGE = "https://invent.kde.org/plasma-mobile/kasts"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-3.0-or-later.txt;md5=8da5784ab1c72e63ac74971f88658166"

inherit cmake_kdeapp
inherit kf6-kconfig kf6-kcoreaddons

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/kasts-25.08.0.tar.xz"
SRC_URI[sha256sum] = "c7cb5d3ce480d97b96402d8f9dcfc40c0465a4e1cfe3513fb7ccef78e5eb275c"

DEPENDS = " \
    qtbase6 \
    qtdeclarative6 \
    qtsvg6 \
    qtmultimedia6 \
    \
    syndication \
    ki18n6 \
    taglib \
    threadweaver6 \
    networkmanager-qt6 \
    qtkeychain6 \
    kcolorscheme6 \
    kirigami6 kirigami6-addons6 \
"
