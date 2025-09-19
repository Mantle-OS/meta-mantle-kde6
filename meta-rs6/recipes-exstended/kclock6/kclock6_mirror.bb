# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Clock app for Plasma Mobile"
HOMEPAGE = ""
LICENSE = "GPL-2.0-only | GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=4cc91856b08b094b4f406a29dc61db21"

inherit cmake_kdeapp
inherit kf6-kconfig kf6-kcoreaddons kf6-kauth

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/kclock-25.08.0.tar.xz"
SRC_URI[sha256sum] = "b0fe8c3a4c423c5ca40d9daef287f1d84498ea6b2acf66af55bdbd77e077f896"

DEPENDS = " \
    qtbase6 \
    qtdeclarative6 \
    qtmultimedia6 \
    \
    kdeclarative6 \
    libplasma6 \
    kiconthemes6 \
    ki18n6 \
    kirigami6 kirigami6-addons6 \
    knotifications6 \
    kstatusnotifieritem6 \
"

RDEPENDS:${PN} += " \
    qtdeclarative6-qmlplugins \
"

FILES:${PN} += " \
    ${datadir}/plasma/plasmoids/ \
    ${datadir}/krunner/ \
"
