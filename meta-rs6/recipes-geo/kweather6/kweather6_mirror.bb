# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Weather application for Plasma Mobile"
HOMEPAGE = ""
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=4cc91856b08b094b4f406a29dc61db21"

inherit rs6

DEPENDS = " \
    qtdeclarative6 \
    qtcharts6 \
    \
    kirigami6 kirigami6-addons \
    knotifications6 \
    \
    kcrash6 \
    libplasma6 \
    kweathercore6 \
"
#    kquickcharts6 

PACKAGECONFIG[plasmoid] = "-DBUILD_PLASMOID=ON, -DBUILD_PLASMOID=OFF, "

PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    kauth \
    ki18n \
    plasmoid \
"

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/plasma \
"
