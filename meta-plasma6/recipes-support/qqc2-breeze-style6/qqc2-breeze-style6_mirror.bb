# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2023 Maxime Roussin-Bélanger <maxime.roussinbelanger@gmail.com>
#
# SPDX-License-Identifier: MIT

SUMMARY = "Breeze inspired QtQuickControls 2 Style"
HOMEPAGE = "https://invent.kde.org/plasma/qqc2-breeze-style"
LICENSE = "LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-3.0-only.txt;md5=852ecadc0ac7e6f4d7144d5544a3815b"
PR = "r0"

inherit cmake_plasma

SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/qqc2-breeze-style-6.4.4.tar.xz"
SRC_URI[sha256sum] = "fb73e858d079f0a292ed571558f2cd25ef7e83cbecd15f62f5789540c3b0951c"

DEPENDS = " \
    qtdeclarative6 \
    kirigami6 \
    kquickcharts6 \
"

FILES:${PN} += " \
    ${libdir}/qt6/plugins/kf6/kirigami/platform/*.so \
    ${libdir}/qt6/qml/org/kde/kirigami/* \
    ${libdir}/qt6/qml/org/kde/breeze/* \
"
