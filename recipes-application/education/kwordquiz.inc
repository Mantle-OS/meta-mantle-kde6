# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

SUMMARY = "KWordQuiz"
DESCRIPTION = "Flash Card Trainer"
HOMEPAGE = "https://invent.kde.org/education/kwordquiz"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881"
PR = "r0"

DEPENDS += " \
    qtbase6 \
    qtdeclarative6 \
    kcrash \
    kirigami6 \
    kirigami6-addons6 \
    knewstuff6 \
    libkeduvocdocument6 \
"

inherit cmake_kdeapp
inherit kf6-kcoreaddons
inherit kf6-kconfig
inherit kf6-kauth
inherit mime-xdg

FILES:${PN} += " \
    ${datadir}/knsrcfiles/kwordquiz.knsrc \
"
