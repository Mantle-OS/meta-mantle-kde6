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

inherit cmake_kdeapp
inherit kf6-kcoreaddons
inherit kf6-kconfig
inherit kf6-kauth


SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/khangman-25.08.0.tar.xz"
SRC_URI[sha256sum] = "6e0395602370083082525717485fa1030ae7b4e31ecb97ad2035aa3c4788a890"

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

RDEPENDS:${PN} += " \
    kdeedu-data6 \
"

FILES:${PN} += " \
    ${datadir}/knsrcfiles/ \
"
