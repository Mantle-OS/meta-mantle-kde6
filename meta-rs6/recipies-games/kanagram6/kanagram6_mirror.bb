# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0

# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Kanagram"
DESCRIPTION = "Kanagram is a game based on anagrams of words: the puzzle is solved when \
    the letters of the scrambled word are put back in the correct order"
HOMEPAGE = "https://edu.kde.org/kanagram/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

inherit cmake_kdeapp
inherit kf6-kcoreaddons
inherit kf6-kconfig
inherit kf6-kauth


SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/kanagram-25.08.0.tar.xz"
SRC_URI[sha256sum] = "a8086e3f8a8a8faf40878d439aa754641444d93c8b0d0c529047530abdf46406"
DEPENDS += " \
    kcrash \
    ki18n6 \
    kio6 \
    knewstuff6 \
    knotifications \
    knotifyconfig6 \
    libkeduvocdocument6 \
"

RDEPENDS:${PN} += " \
    kdeedu-data6 \
"
