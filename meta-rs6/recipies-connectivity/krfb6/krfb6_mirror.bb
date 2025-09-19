# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Guillaume Champagne <champagne.guillaume.c@gmail.com>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KRfb"
SUMMARY = "Small server for the RFB protocol (vnc)"
HOMEPAGE = "https://invent.kde.org/network/krfb"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=3c8ea28d12b8a786603a5394fc50096d \
"

inherit cmake_kdeapp

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/krfb-25.08.0.tar.xz"
SRC_URI[sha256sum] = "f353c6603046fe97fd0808497d47a3eb18bbd7811e43d08c965414214496845a"

DEPENDS = " \
    kconfig6 kconfig6-native \
    kdbusaddons6 \
    kdnssd6 \
    knotifications6 \
    kpipewire6 \
    kstatusnotifieritem6 \
    kwallet6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
    kxmlgui6 \
    \
    libvncserver \
    qtbase6 \
    qtwayland6 qtwayland6-native \
"

RDEPENDS:${PN} += " \
    xdg-desktop-portal \
"
