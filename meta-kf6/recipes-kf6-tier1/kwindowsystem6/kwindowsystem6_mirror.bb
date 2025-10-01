# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2014 Johan Thelin <johan.thelin@pelagicore.com>
# SPDX-FileCopyrightText: 2016 Sébastien Taylor <sebastien@au-zone.com>
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Access to the windowing system"
SECTION = "libs"
HOMEPAGE = "https://api.kde.org/frameworks/kwindowsystem/html/index.html"
LICENSE = "MIT & BSD-3-Clause & (LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL)"

inherit kf6

PACKAGECONFIG[qml] = "-DKWINDOWSYSTEM_QML=ON, -DKWINDOWSYSTEM_QML=OFF, qtdeclarative6"
PACKAGECONFIG[x11] = "-DKWINDOWSYSTEM_X11=ON, -DKWINDOWSYSTEM_X11=OFF, \
    virtual/libx11 libxcb xcb-util xcb-util-keysyms libxfixes xcb-util-wm"


PACKAGECONFIG[wayland] = "-DKWINDOWSYSTEM_WAYLAND=ON, -DKWINDOWSYSTEM_WAYLAND=OFF, \
    qtwayland6 qtwayland6-native plasma-wayland-protocols-kf6 \
"

PACKAGECONFIG:append:class-target = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'wayland', d)} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
    qml \
"
#PACKAGECONFIG:append:class-native = " 
#    ${@bb.utils.filter('DISTRO_FEATURES', 'wayland', d)} 
#"


RDEPENDS:${PN}:class-target += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'directfb', '', d)} \ 
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'directfb', '', d)}  \
"

BBCLASSEXTEND = "native nativesdk"
