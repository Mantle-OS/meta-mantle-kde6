# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
SUMMARY = "KHangMan"
DESCRIPTION = "KHangMan is a game based on the well-known hangman game."
HOMEPAGE = "https://edu.kde.org/khangman/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=cb56c8b10bbb1c97490192a78c4fb273"

inherit rs6

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
    ki18n \
"

DEPENDS:append = " \
    kcompletion \
    kcrash6 \
    kio6 \
    knewstuff6 \
    knotifications \
    knotifyconfig6 \
    kparts6 \
    kxmlgui6 \
    libkeduvocdocument6 \
"

RDEPENDS:${PN} += " \
    kdeedu-data6 \
"

FILES:${PN} += " \
    ${datadir}/knsrcfiles/ \
"
