# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

SUMMARY = "KHangMan"
DESCRIPTION = "KHangMan is a game based on the well-known hangman game."
HOMEPAGE = "https://edu.kde.org/khangman/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=cb56c8b10bbb1c97490192a78c4fb273"
PR = "r0"

DEPENDS += " \
    kcompletion \
    kcrash \
    ki18n6 \
    kio6 \
    knewstuff6 \
    knotifications \
    knotifyconfig6 \
    kparts6 \
    kxmlgui6 \
    libkeduvocdocument6 \
"

inherit cmake_kdeapp
inherit kf6-kcoreaddons
inherit kf6-kconfig
inherit kf6-kauth
inherit mime-xdg

RDEPENDS:${PN} += " \
    kdeedu-data6 \
"

FILES:${PN} += " \
    ${datadir}/knsrcfiles/ \
"
