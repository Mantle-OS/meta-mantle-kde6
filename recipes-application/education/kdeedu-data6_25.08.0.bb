# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0

require ${PN}.inc
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "KDE Education Data"
DESCRIPTION = "Shared icons, artwork and data files for educational applications"
HOMEPAGE = "https://invent.kde.org/education/kdeedu-data"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit cmake_kdeapp

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/kdeedu-data-25.08.0.tar.xz"
SRC_URI[sha256sum] = "5ef96eeeed941a5cebd9756c6cc1dde888a63c677a1c6250b6a2286070b92066"

FILES:${PN} += " \
    ${datadir}/apps/kvtml/* \
"
