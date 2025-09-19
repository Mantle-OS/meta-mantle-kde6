# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Implementation of Status Notifier Items"
HOMEPAGE = "https://api.kde.org/frameworks/kstatusnotifieritem/html/index.html"
SECTION = "libs"
LICENSE = "CC0-1.0 & LGPL-2.0-or-later"

inherit kf6
DEPENDS:append = " kwindowsystem6"

PACKAGECONFIG[x11] = "-DWITHOUT_X11=OFF, -DWITHOUT_X11=ON, virtual/libx11"
PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "
PACKAGECONFIG[py-bindings] = "-DBUILD_PYTHON_BINDINGS=ON, -DBUILD_PYTHON_BINDINGS=OFF, \
    python3-native python3-pyside6  python3-shiboken6-native "

PACKAGECONFIG:append = " \
    dbus \
"

PACKAGECONFIG:append:class-target = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
"
