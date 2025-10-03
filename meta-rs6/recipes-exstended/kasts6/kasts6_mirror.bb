# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Kasts"
DESCRIPTION = "Kasts is a convergent podcast application."
HOMEPAGE = "https://invent.kde.org/plasma-mobile/kasts"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-3.0-or-later.txt;md5=8da5784ab1c72e63ac74971f88658166"

inherit rs6

PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    ki18n \
"

DEPENDS:append = " \
    qtdeclarative6 \
    qtsvg6 \
    qtmultimedia6 \
    \
    syndication6 \
    taglib \
    threadweaver6 \
    networkmanager-qt6 \
    qtkeychain6 \
    kcolorscheme6 \
    kirigami6 kirigami6-addons \
"
