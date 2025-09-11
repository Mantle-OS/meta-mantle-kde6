# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Weather application for Plasma Mobile"
HOMEPAGE = ""
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=4cc91856b08b094b4f406a29dc61db21"

inherit cmake_kdeapp
inherit kf6-kconfig kf6-kcoreaddons kf6-kauth

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/kweather-25.08.0.tar.xz"
SRC_URI[sha256sum] = "acd8d383abc97323a08283f5c34511d05a06b7019320d024968a01ccf6fe2106"

DEPENDS = " \
    qtbase6 \
    qtdeclarative6 \
    qtcharts6 \
    \
    ki18n6 \
    kirigami6 kirigami6-addons6 \
    knotifications6 \
    kquickcharts6 \
    libplasma6 \
    kweathercore6 \
    kconfig6 \
    kcoreaddons6 \
    kauth6 \
"

RDEPENDS:${PN} += " \
    qtdeclarative6-qmlplugins \
    qtcharts6-qmlplugins \
"

FILES:${PN} += " \
    ${datadir}/plasma/plasmoids/ \
"
