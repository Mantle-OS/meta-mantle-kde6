# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "KRdp"
SUMMARY = "Library and examples for creating an RDP server"
HOMEPAGE = "https://invent.kde.org/plasma/krdp"
LICENSE = "LGPL-2.1-only | LGPL-3.0-only"
LIC_FILES_CHKSUM = " \
    file://LICENSES/LGPL-2.1-only.txt;md5=41890f71f740302b785c27661123bff5 \
    file://LICENSES/LGPL-3.0-only.txt;md5=852ecadc0ac7e6f4d7144d5544a3815b \
"
PR = "r0"

inherit cmake_kdeapp kf6-kconfig kf6-kcmutils systemd

SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/krdp-6.4.4.tar.xz"
SRC_URI[sha256sum] = "228d8630ff2e14fa899ad611d1865399f9e6128538f9df91273ab3968f7cec84"
DEPENDS = " \
    freerdp3 \
    qtbase6 \
    qtwayland6 \
    qtwayland6-native \
    qtkeychain6 \
    kpipewire6 \
    kstatusnotifieritem6 \
    libxkbcommon \
    plasma-wayland6-protocols6 \
"


RDEPENDS:${PN} += " \
    xdg-desktop-portal \
"

SYSTEMD_SERVICE:${PN} = "app-org.kde.krdpserver.service"
SYSTEMD_AUTO_ENABLE:${PN} = "disable"

FILES:${PN} += " \
    ${libdir}/plugins/plasma/kcms/systemsettings/* \
"
