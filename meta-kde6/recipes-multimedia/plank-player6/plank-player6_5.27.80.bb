# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2022 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Plank Player"
HOMEPAGE = "https://invent.kde.org/plasma-bigscreen/plank-player/"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = " \
    file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881 \
"
PR = "r0"

inherit cmake_plasma
inherit mime-xdg

SRCREV = "d113e535f2e2682bfaeb09262e36fb6ef5360d82"
SRC_URI = "git://invent.kde.org/plasma-bigscreen/${BPN};nobranch=1;protocol=https"
S = "${WORKDIR}/git"

DEPENDS += " \
    kirigami6 \
    qtdeclarative6 \
    qtmultimedia6 \
"

RDEPENDS:${PN} += " \
    qtmultimedia6 \
    qtmultimedia6-plugins \
    qtmultimedia6-qmlplugins \
    qqc2-desktop-style6 \
"

