# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Library to facilitate retrieval of weather information including forecasts and alerts"
HOMEPAGE = "https://invent.kde.org/libraries/kweathercore"
LICENSE = "LGPL-2.0-or-later"
LIC_FILES_CHKSUM = " \
    file://LICENSES/LGPL-2.0-or-later.txt;md5=6d2d9952d88b50a51a5c73dc431d06c7 \
"

inherit cmake_kdeapp

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/kweathercore-25.08.0.tar.xz"
SRC_URI[sha256sum] = "1b97e95aec8f1930244ae5c400712056109b3ed22fa5b6994834cd714bbe324a"

DEPENDS += " \
    qtbase6 \
    qtlocation6 \
    ki18n6 \
    kholidays6 \
"
