# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Itinerary"
SUMMARY = "Itinerary and boarding pass management application"
HOMEPAGE = "https://invent.kde.org/pim/itinerary"
LICENSE = "LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.0-or-later.txt;md5=6d2d9952d88b50a51a5c73dc431d06c7"

inherit rs6

PACKAGECONFIG:append = " \
    kconfig \
"

DEPENDS:append = " \
    qtdeclarative6 \
    qtpositioning6 \
    \
    qtkeychain6 \
    kirigami6 kirigami6-addons \
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


#INSANE_SKIP:${PN} = "dev-so"
#FILES_SOLIBSDEV = ""
