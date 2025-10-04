# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "KWordQuiz"
DESCRIPTION = "Flash Card Trainer"
HOMEPAGE = "https://invent.kde.org/education/kwordquiz"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881"

inherit rs6

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
"

DEPENDS:append = " \
    qtdeclarative6 \
    qtmultimedia6 \
    qtsvg6  \
    \
    kcrash6 \
    knewstuff6 \
    kirigami6 kirigami6-addons \
    kdbusaddons6 \
    kdoctools6 kdoctools6-native \
    libkeduvocdocument6 \
"

FILES:${PN} += " \
    ${datadir}/kwordquiz \
    ${datadir}/metainfo \
    ${datadir}/config.kcfg \
    ${datadir}/knsrcfiles \
"
