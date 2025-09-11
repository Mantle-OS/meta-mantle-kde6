# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Collection of Arcade games developed in Kirigami"
HOMEPAGE = ""
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-3.0-or-later.txt;md5=1c76c4cc354acaac30ed4d5eefea7245"
PR = "r0"

inherit cmake_kdeapp
inherit mime-xdg

SRC_URI = "git://invent.kde.org/games/${BPN};nobranch=1;protocol=https"
SRCREV = "8b127277206d3c7b1d25cb19ec0dbe3581063318"
S = "${WORKDIR}/git"

DEPENDS = " \
    qtbase6 \
    qtdeclarative6 \
    kdeclarative6 \
    kiconthemes6 \
    kirigami6 \
    kpackage6 \
"

RDEPENDS:${PN} += " \
    qtdeclarative6 \
"

FILES:${PN} += "\
    ${datadir}/game/packages/org.kde.arkade.fourinline \
    ${datadir}/game/packages/org.kde.arkade.samegame \
    ${datadir}/game/packages/org.kde.arkade.blocktennis \
"
