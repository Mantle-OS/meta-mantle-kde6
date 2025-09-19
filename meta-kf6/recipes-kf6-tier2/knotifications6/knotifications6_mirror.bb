# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Abstraction for system notifications"
HOMEPAGE = "https://api.kde.org/frameworks/knotifications/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"

inherit kf6

DEPENDS += " \
    qtdeclarative6 \
    kwindowsystem6 \
    libcanberra \
"

PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "
PACKAGECONFIG[py-bindings] = "-DBUILD_PYTHON_BINDINGS=ON, -DBUILD_PYTHON_BINDINGS=OFF, \
    python3-native python3-pyside6 python3-shiboken6-native"

PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    dbus \
"
