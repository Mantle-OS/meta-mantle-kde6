# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "KPkPass"
DESCRIPTION = "Apple Wallet Pass reader"
HOMEPAGE = "https://invent.kde.org/pim/kpkpass"
LICENSE = "BSD-3-Clause & LGPL-2.0-or-later"
LIC_FILES_CHKSUM = " \
    file://LICENSES/BSD-3-Clause.txt;md5=954f4d71a37096249f837652a7f586c0 \
    file://LICENSES/LGPL-2.0-or-later.txt;md5=6d2d9952d88b50a51a5c73dc431d06c7 \
"

inherit cmake_kdeapp

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/kpkpass-25.08.0.tar.xz"
SRC_URI[sha256sum] = "2ee2a25ff90f23026dd687e2b62ac1a908c1c55fdf685a42583d67472a1badbb"

DEPENDS += " \
    qtbase6 \
    karchive6 \
"


