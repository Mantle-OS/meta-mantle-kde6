# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2022 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Phonon for KF6"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1"
PR = "r0"

inherit kde6-ecm

SRC_URI = "https://download.kde.org/stable/phonon/${PV}/phonon-${PV}.tar.xz"
SRC_URI[sha256sum] = "3287ffe0fbcc2d4aa1363f9e15747302d0b080090fe76e5f211d809ecb43f39a"
S = "${WORKDIR}/phonon-${PV}"

DEPENDS:append = " \
    qt5compat6 \
    pulseaudio \
    glib-2.0 \
    qttools6 \
"

EXTRA_OECMAKE:append = " -DPHONON_BUILD_QT5=OFF"

#RRECOMMENDS:${PN} += " phonon-backend-vlc-kf6"

PACKAGECONFIG[experimental] = "-DPHONON_BUILD_EXPERIMENTAL=ON, -DPHONON_BUILD_EXPERIMENTAL=ON, " 
PACKAGECONFIG[demos] = "-DPHONON_BUILD_DEMOS=ON, -DPHONON_BUILD_DEMOS=OFF, "
PACKAGECONFIG[designer] = "-DPHONON_BUILD_DESIGNER_PLUGIN=ON, -DPHONON_BUILD_DESIGNER_PLUGIN=OFF, qttools6-native"
PACKAGECONFIG[docs] = "-DPHONON_BUILD_DOC=ON, -DPHONON_BUILD_DOC=OFF, qttools6-native"
PACKAGECONFIG[gui] = "-DPHONON_BUILD_SETTINGS=ON, -DPHONON_BUILD_SETTINGS=OFF, "
#PACKAGECONFIG[qt5] = "-DPHONON_BUILD_QT5=ON, -DPHONON_BUILD_QT5=OFF, qtbase"
PACKAGECONFIG[qt6] = "-DPHONON_BUILD_QT6=ON, -DPHONON_BUILD_QT6=OFF, "

PACKAGECONFIG:append = " qt6 gui experimental " 
