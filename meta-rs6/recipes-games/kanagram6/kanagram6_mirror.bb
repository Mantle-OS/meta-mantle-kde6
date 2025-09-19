# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0

# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Kanagram"
DESCRIPTION = "Kanagram is a game based on anagrams of words: the puzzle is solved when the letters of the scrambled word are put back in the correct order"
HOMEPAGE = "https://edu.kde.org/kanagram/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

inherit rs6

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
    ki18n \
"

DEPENDS:append = " \
    kcrash \
    kio6 \
    knewstuff6 \
    knotifications \
    knotifyconfig6 \
    libkeduvocdocument6 \
"

RDEPENDS:${PN} += " \
    kdeedu-data6 \
"
