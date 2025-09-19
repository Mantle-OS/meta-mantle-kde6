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

inherit rs6

PACKAGECONFIG:append " \
    kconfig \
"

DEPENDS:append = " \
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
    qtwayland6 qtwayland6-native \
"

RDEPENDS:${PN} += " \
    xdg-desktop-portal \
"
