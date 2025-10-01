# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Resource and network access abstraction"
HOMEPAGE = "https://api.kde.org/frameworks/kio/html/index.html"
SECTION = "libs"
REUSE_LICENSECHECK_WHITELIST = "Qt-Commercial-exception-1.0 CC0-1.0"
LICENSE = "LGPL-2.0-only & (LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL) & BSD-2-Clause & BSD-3-Clause & MIT & (GPL-2.0-only | GPL-3.0-only | LicenseRef-KDE-Accepted-GPL)"

inherit kf6

SRC_URI:append = " \
    file://0001-SwitcherooControl-is-runtime-and-not-buildtime.patch \
"

DEPENDS:append = " \
    acl \
    karchive6 \
    kservice6 \
    solid6 \
    kcrash6 \
    kguiaddons6 \
    kbookmarks6 \
    \
    kcolorscheme6 \
    kcompletion6 \
    kiconthemes6 \
    kitemviews6 \
    kjobwidgets6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
    kded6 \  
    kwallet6 \
    \
    switcheroo-control \
    polkit-qt6-1 \
"

PACKAGECONFIG[full] = "-DKIOCORE_ONLY=OFF, -DKIOCORE_ONLY=ON, "
PACKAGECONFIG[webdavtest] = "-DUSE_FTPD_WSGIDAV_UNITTEST=ON, -DUSE_FTPD_WSGIDAV_UNITTEST=OFF, "
PACKAGECONFIG[dbus] = "-DUSE_DBUS=-ON, -DUSE_DBUS=OFF, kdbusaddons6"
PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, virtual/libx11"
PACKAGECONFIG[wayland] = "-DWITH_WAYLAND=ON, -DWITH_WAYLAND=OFF, qtwayland6 qtwayland6-native"

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    ki18n \
    kdoctools \
    kauth \
    dbus \
    full \
    ${@bb.utils.filter('DISTRO_FEATURES', 'wayland', d)} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
"

EXTRA_OECMAKE:append = " -DBUILD_DESIGNERPLUGIN=OFF"

RDEPENDS:${PN} += " \
    ca-certificates \
    switcheroo-control \     
"

PACKAGES:prepend = "${PN}-private "
FILES:${PN}-private = " \
    ${libdir}/libkuriikwsfiltereng_private.so \
" 

