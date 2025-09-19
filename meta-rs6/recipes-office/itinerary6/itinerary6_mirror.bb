# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Itinerary"
SUMMARY = "Itinerary and boarding pass management application"
HOMEPAGE = "https://invent.kde.org/pim/itinerary"
LICENSE = "LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.0-or-later.txt;md5=6d2d9952d88b50a51a5c73dc431d06c7"

inherit cmake_kdeapp
inherit kf6-kconfig

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/itinerary-25.08.0.tar.xz"
SRC_URI[sha256sum] = "1f2c10c1f47a5919f447accdf60997038a528a66b8ee172fcc184dd2bd2f2716"

DEPENDS += " \
    qtbase6 \
    qtdeclarative6 \
    qtpositioning6 \
    \
    qtkeychain6 \
    kirigami6 kirigami6-addons6 \
    kcontacts6 \
    kcalendarcore6 \
    kholidays6 \
    kunitconversion6 \
    kpkpass6 \
    kitinerary6 \
    kosmindoormap6 \
    kpublictransport6 \
    qqc2-desktop-style6 \
"


INSANE_SKIP:${PN} = "dev-so"
FILES:${PN} += " \
    ${libdir}/libSolidExtras.so \
"
FILES_SOLIBSDEV = ""

RDEPENDS:${PN} += " \
    prison6 \
    solid6 \
    \
    qtlocation6-qmlplugins \
    qtmultimedia6-qmlplugins \
"
