# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "User configurable main windows"
HOMEPAGE = "https://api.kde.org/frameworks/kxmlgui/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"

inherit kf6

DEPENDS:append = "\
    kitemviews6 \
    kconfigwidgets6 \
    kiconthemes6 \
    kguiaddons6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
    kglobalaccel6 \
"

PACKAGECONFIG[no-global-accel] = "-DFORCE_DISABLE_KGLOBALACCEL=ON, -DFORCE_DISABLE_KGLOBALACCEL=OFF, "
PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "
PACKAGECONFIG[py-bindings] = "-DBUILD_PYTHON_BINDINGS=ON, -DBUILD_PYTHON_BINDINGS=OFF,\
    python3-native python3-pyside6 python3-shiboken6-native"

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
    dbus \
    ki18n \
"
BBCLASSEXTEND = "native nativesdk"
