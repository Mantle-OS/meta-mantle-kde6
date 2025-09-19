# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "KEduVocDocument"
DESCRIPTION = "Library for reading from/writing to the KVTML format."
HOMEPAGE = "https://edu.kde.org/khangman/"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881"

inherit cmake_kdeapp
inherit kf6-kconfig
inherit kf6-kauth


SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/libkeduvocdocument-25.08.0.tar.xz"
SRC_URI[sha256sum] = "b6739f73e41c5df9ee312011985f75bd2e74200e78a7cba771958bb1f029517a"

DEPENDS += " \
    karchive6 \
    ki18n6 \
    kio6 \
"
