# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Plasma Wayland Protocols"
HOMEPAGE = "https://community.kde.org/Plasma/Wayland"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1"

PV="1.18.0"
PR = "r0"  

inherit kde6-ecm features_check

REQUIRED_DISTRO_FEATURES = "wayland"

SRC_URI = "https://download.kde.org/stable/plasma-wayland-protocols/plasma-wayland-protocols-1.18.0.tar.xz"
SRC_URI[sha256sum] = "2567472671ad5d989f88b51baef9dd59353a5e7c3f2ed7e6b989755cb9004233"
S = "${WORKDIR}/plasma-wayland-protocols-1.18.0"

DEPENDS:append = " \
   qtwayland6-native \
   qtdeclarative6 \
"

PACKAGECONFIG[testing] = "-DBUILD_TESTING=ON, -DBUILD_TESTING=OFF, qttools6-native"

FILES:${PN} += " \
    ${libdir}/cmake \
    ${datadir}/plasma-wayland-protocols/* \
"

BBCLASSEXTEND = "native nativesdk"
