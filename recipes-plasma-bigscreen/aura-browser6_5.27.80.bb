# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2022 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Aura Browser"
HOMEPAGE = "https://invent.kde.org/plasma-bigscreen/aura-browser/"
LICENSE = "GPL-2.0-or-later & GPL-3.0-or-later"
LIC_FILES_CHKSUM = " \
    file://LICENSES/GPL-2.0-or-later.txt;md5=fed54355545ffd980b814dab4a3b312c \
    file://LICENSES/GPL-3.0-or-later.txt;md5=1ebbd3e34237af26da5dc08a4e440464 \
"
PR = "r0"

inherit cmake_plasma
inherit mime-xdg

SRCREV = "69bf5a2234ce490edc7018adcb82949c987fcf43"
SRC_URI = "git://invent.kde.org/plasma-bigscreen/${BPN};nobranch=1;protocol=https"
S = "${WORKDIR}/git"

DEPENDS = " \
    kirigami6 \
    qtdeclarative6 \
    qtmultimedia6 \
    qtwebengine6 \
"

