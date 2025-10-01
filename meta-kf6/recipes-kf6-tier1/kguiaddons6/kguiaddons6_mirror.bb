# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2014 Johan Thelin <johan.thelin@pelagicore.com>
# SPDX-FileCopyrightText: 2016 Sébastien Taylor <sebastien@au-zone.com>
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Addons to QtGui"
HOMEPAGE = "https://api.kde.org/frameworks/kguiaddons/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.0-or-later & (LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL)"
REUSE_LICENSECHECK_WHITELIST = "GPL-2.0-only GPL-3.0-only LicenseRef-KDE-Accepted-GPL"

inherit kf6 features_check

ANY_OF_DISTRO_FEATURES = "opengl wayland x11"

EXTRA_OECMAKE:append  = " \
    -DBUILD_GEO_SCHEME_HANDLER=OFF \
"

PACKAGECONFIG[wayland] = "-DWITH_WAYLAND=ON, -DWITH_WAYLAND=OFF, \
    qtwayland6 qtwayland6-native wayland-protocols plasma-wayland-protocols-kf6"

PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, virtual/libx11 libxcb xcb-util-keysyms"
PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "
PACKAGECONFIG[qml] = " ,  ,  qtdeclarative6"
PACKAGECONFIG[py-bindings] = "-DBUILD_PYTHON_BINDINGS=ON, \
    -DBUILD_PYTHON_BINDINGS=OFF, python3-native python3-pyside6  python3-shiboken6-native"

PACKAGECONFIG:append = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'wayland', d)} \
    dbus \
    qml \
"
PACKAGECONFIG:remove:class-native = " \
    ${@bb.utils.contains('DISTRO_FEATURES_NATIVE', 'x11', 'wayland x11', '', d)} \
"

PACKAGECONFIG:remove:class-nativesdk = " \
    ${@bb.utils.contains('DISTRO_FEATURES_NATIVESDK', 'x11', 'wayland x11', '', d)} \
"
BBCLASSEXTEND = "native nativesdk"
