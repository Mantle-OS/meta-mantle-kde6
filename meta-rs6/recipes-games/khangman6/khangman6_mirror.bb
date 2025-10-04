# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
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
    qtdeclarative6 \
    qtsvg6 \
    \
    kcrash6 \
    kcompletion6 \
    knewstuff6 \
    knotifications6 \
    kio6 \
    kdoctools6 kdoctools6-native \
    libkeduvocdocument6 \
"

FILES:${PN} += " \
    ${datadir}/knsrcfiles/ \
    ${datadir}/metainfo \
    ${datadir}/config.kcfg \
    ${datadir}/khangman \
"
