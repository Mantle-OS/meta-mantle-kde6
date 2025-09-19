# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KOSMIndoorMap"
SUMMARY = "OSM multi-floor indoor map renderer"
HOMEPAGE = "https://invent.kde.org/libraries/kosmindoormap"
LICENSE = "LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.0-or-later.txt;md5=6d2d9952d88b50a51a5c73dc431d06c7"

inherit cmake_kdeapp

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/kosmindoormap-25.08.0.tar.xz"
SRC_URI[sha256sum] = "46151160bfce5f855e4df6febbdf9e4887f4addd745749dd0888aa9a12d121cb"

DEPENDS += " \
    qtdeclarative6 \
    ki18n6 \
    kopeninghours6 \
    kpublictransport6 \
    bison-native \
"

