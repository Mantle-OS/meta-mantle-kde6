# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Marc Ferland <marc.ferland@sonatest.com>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Phonon VLC backend"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=46d98c0b7542668d6015b992889b2020"
PR = "r0"

inherit kde6-ecm

SRC_URI = "https://download.kde.org/stable/phonon/phonon-backend-vlc/${PV}/phonon-backend-vlc-${PV}.tar.xz"
SRC_URI[sha256sum] = "338479dc451e4b94b3ca5b578def741dcf82f5c626a2807d36235be2dce7c9a5"
S = "${WORKDIR}/phonon-backend-vlc-${PV}"

DEPENDS:append = " \
    phonon-kf6 \
    qttools6-native \
    vlc \
"

PACKAGECONFIG[qt5] = "-DPHONON_BUILD_QT5=ON, -DPHONON_BUILD_QT5=OFF, "
PACKAGECONFIG[qt6] = "-DPHONON_BUILD_QT6=ON, -DPHONON_BUILD_QT6=OFF, "
PACKAGECONFIG:append = " qt6"

RDEPENDS:${PN} += "vlc"
