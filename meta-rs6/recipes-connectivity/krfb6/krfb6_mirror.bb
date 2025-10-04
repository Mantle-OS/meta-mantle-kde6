# SPDX-FileCopyrightText: 2024 Guillaume Champagne <champagne.guillaume.c@gmail.com>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KRfb"
SUMMARY = "Small server for the RFB protocol (vnc)"
HOMEPAGE = "https://invent.kde.org/network/krfb"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=3c8ea28d12b8a786603a5394fc50096d"

inherit rs6
inherit features_check

REQUIRED_DISTRO_FEATURES =  "x11"

SRC_URI:append = " \
    file://0001-add-zlib-to-the-mix-as-it-fails.patch \
"

PACKAGECONFIG:append = " \
    kconfig \
    ki18n \
    kcoreaddons \
    kdoctools \
"

DEPENDS:append = " \
    kdbusaddons6 \
    kdnssd6 \
    knotifications6 \
    kwallet6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
    kxmlgui6 \
    kstatusnotifieritem6 \
    kcrash6 \
    \
    virtual/libx11 \
    libxcb \
    xcb-util-renderutil \
    libxfixes \
    libxdamage \
    libxext \
    xcb-util-image \
    \
    libvncserver \
    \
    plasma-wayland-protocols-kf6 \
    kwayland6 \
    wayland wayland-native \
    qtwayland6 qtwayland6-native \
    virtual/egl \
    \
    zlib \
"

PACKAGECONFIG[pipewire] = "-DISABLE_PIPEWIRE=OFF, -DDISABLE_PIPEWIRE=ON, kpipewire6 pipewire"
PACKAGECONFIG:append  = " \
    pipewire \
"

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/krfb \
"

