# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2021-2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Add-ons for the Kirigami framework"
HOMEPAGE = "https://invent.kde.org/libraries/kirigami-addons"
LICENSE = "LGPL-2.1-or-later & BSD-2-Clause"
LIC_FILES_CHKSUM = " \
    file://LICENSES/LGPL-2.1-or-later.txt;md5=41890f71f740302b785c27661123bff5 \
    file://LICENSES/BSD-2-Clause.txt;md5=534956ece1a160a65f088a8f82672ef9 \
"
PR = "r0"

inherit cmake_kdeapp
inherit kf6-kconfig

SRC_URI = "git://invent.kde.org/libraries/${BPN};nobranch=1;protocol=https"
SRCREV = "d53b2b24608ae66dbaa4f09d8d7ebae6c9f642b5"
S = "${WORKDIR}/git"

DEPENDS = " \
    kirigami6 \
    ki18n6 \
    qt5compat6 \
    qtdeclarative6 \
    plasma-activities6 \
"
