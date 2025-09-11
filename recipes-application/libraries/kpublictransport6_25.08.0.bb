# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "KPublicTransport"
DESCRIPTION = "Library to assist with accessing public transport timetables and other data"
HOMEPAGE = "https://invent.kde.org/libraries/kpublictransport"
LICENSE = "MIT & BSD-3-Clause & LGPL-2.0-or-later"
LIC_FILES_CHKSUM = " \
    file://LICENSES/LGPL-2.0-or-later.txt;md5=6d2d9952d88b50a51a5c73dc431d06c7 \
"

inherit cmake_kdeapp

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/kpublictransport-25.08.0.tar.xz"
SRC_URI[sha256sum] = "fd0190081f4be172711ec1e0e5702805bcf9c00c73a08a42ba3790167cabab0a"

DEPENDS += " \
    qtdeclarative6 \
"
