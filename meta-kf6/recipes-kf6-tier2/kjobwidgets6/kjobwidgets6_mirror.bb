# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2014 Johan Thelin <johan.thelin@pelagicore.com>
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

# the licenses are only options for some files
SUMMARY = "Widgets for tracking KJob instances"
HOMEPAGE = "https://api.kde.org/frameworks/kjobwidgets/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.0-only"
REUSE_LICENSECHECK_WHITELIST = "LicenseRef-KDE-Accepted-GPL LGPL-3.0"

inherit kf6 

DEPENDS:append = " \
    knotifications6 \
    kwidgetsaddons6 \
"

PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, virtual/libx11"
PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "
PACKAGECONFIG[py-bindings] = "-DBUILD_PYTHON_BINDINGS=ON, -DBUILD_PYTHON_BINDINGS=OFF, \
    python3-native python3-pyside6  python3-shiboken6-native"

PACKAGECONFIG:append = " \
    kcoreaddons \
    dbus \
"

PACKAGECONFIG:append:class-target = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
"
