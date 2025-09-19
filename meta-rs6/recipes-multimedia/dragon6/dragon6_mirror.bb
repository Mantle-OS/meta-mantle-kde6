# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Dragon"
DESCRIPTION = "Multimedia player with the focus on simplicity, not features"
HOMEPAGE = "https://invent.kde.org/multimedia/dragon/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"

inherit rs6

PACKAGECONFIG:append = " \
    kcoreaddons \
    kdoctools \
    kconfig \
    ki18n \
"

DEPENDS:append = " \
    kconfigwidgets6 \
    kparts6 \
    kxmlgui6 \
    phonon-kf6 \
    solid6 \
"
