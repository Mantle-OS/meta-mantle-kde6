# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0

# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "KWordQuiz"
DESCRIPTION = "Flash Card Trainer"
HOMEPAGE = "https://invent.kde.org/education/kwordquiz"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881"

inherit cmake_kdeapp
inherit kf6-kcoreaddons
inherit kf6-kconfig
inherit kf6-kauth

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/kwordquiz-25.08.0.tar.xz"
SRC_URI[sha256sum] = "0b3c71fc33526d060976db0e1d4d88b5e965a5bef144ad0be828c783e8f4167d"

DEPENDS += " \
    qtbase6 \
    qtdeclarative6 \
    kcrash6 \
    kirigami6 \
    kirigami6-addons6 \
    knewstuff6 \
    libkeduvocdocument6 \
"

FILES:${PN} += " \
    ${datadir}/knsrcfiles/kwordquiz.knsrc \
"
