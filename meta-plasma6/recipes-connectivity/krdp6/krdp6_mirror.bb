# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KRdp"
SUMMARY = "Library and examples for creating an RDP server"
HOMEPAGE = "https://invent.kde.org/plasma/krdp"
LICENSE = "LGPL-2.1-only OR LGPL-3.0-only"
LIC_FILES_CHKSUM = " \
    file://LICENSES/LGPL-2.1-only.txt;md5=41890f71f740302b785c27661123bff5 \
    file://LICENSES/LGPL-3.0-only.txt;md5=852ecadc0ac7e6f4d7144d5544a3815b \
"

inherit plasma6 systemd

DEPENDS:append = " \
    qtbase6 \
    qtwayland6 qtwayland6-native \
    \
    kdbusaddons6 \
    kcmutils6 kcmutils6-native \
    kstatusnotifieritem6 \
    kcrash6 \
    \
    freerdp3 \
    \
    kpipewire6 \
    libxkbcommon \
    qtkeychain6 \
"

PACKAGECONFIG[plasma-session] = "-DBUILD_PLASMA_SESSION=ON, -DBUILD_PLASMA_SESSION=OFF, plasma-wayland-protocols-kf6"
PACKAGECONFIG:append = " \
    plasma-session \
"

RDEPENDS:${PN} += " \
    xdg-desktop-portal \
"

SYSTEMD_SERVICE:${PN} = "app-org.kde.krdpserver.service"
SYSTEMD_AUTO_ENABLE:${PN} = "disable"
