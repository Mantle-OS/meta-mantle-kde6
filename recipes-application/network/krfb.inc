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
PR = "r0"

DEPENDS = " \
    kconfig6 \
    kconfig6-native \
    kdbusaddons6 \
    kdnssd \
    knotifications \
    kpipewire6 \
    kstatusnotifieritem6 \
    kwallet6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
    kxmlgui6 \
    libvncserver \
    qtbase6 \
    qtwayland6 \
    qtwayland6-native \
"

inherit cmake_kdeapp

RDEPENDS:${PN} += " \
    xdg-desktop-portal \
"
