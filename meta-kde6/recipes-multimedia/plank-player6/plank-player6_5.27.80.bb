# SPDX-FileCopyrightText: 2022 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Plank Player"
HOMEPAGE = "https://invent.kde.org/plasma-bigscreen/plank-player/"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881"

inherit plasma6

SRC_URI = "git://invent.kde.org/plasma-bigscreen/plank-player;nobranch=1;protocol=https"
SRCREV = "d113e535f2e2682bfaeb09262e36fb6ef5360d82"

DEPENDS:append = " \
    kirigami6 \
    qtdeclarative6 \
    qtmultimedia6 \
    qt5compat6 \
    ki18n6-native ki18n6 \
"

RDEPENDS:${PN} += " \
    qtmultimedia6 \
    qtmultimedia6-plugins \
    qtmultimedia6-qmlplugins \
    qt5compat6-qmlplugins \
    qqc2-desktop-style6 \
"


# FIXME cmakelist shows tahgt it wants Qt.labs.folderlistmodel will have to add this to the projects.
FILES:${PN} += " \
    ${datadir}/metainfo \
"
