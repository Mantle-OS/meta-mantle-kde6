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

inherit cmake_kdeapp
inherit kf6-kcoreaddons kf6-kdoctools kf6-kconfig


SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/dragon-25.08.0.tar.xz"
SRC_URI[sha256sum] = "b72c06f1ce465dd3d7be47d69ec3a3588ddd464cc0f5d351a8d7ed47c85846a9"

DEPENDS += " \
    qtbase6 \
    kconfigwidgets6 \
    ki18n6 \
    kparts6 \
    kxmlgui6 \
    phonon-kf6 \
    solid6 \
"

FILES:${PN} += " \
    ${datadir}/icons/oxygen/* \
    ${datadir}/kio/servicemenus/* \
    ${datadir}/solid/actions/* \
"
