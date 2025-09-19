# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018-2019 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
# SPDX-FileCopyrightText: 2025 Joseph Mills <josephjamesmills@gmail.com>  
# SPDX-License-Identifier: MIT
SUMMARY = "Utilities for working with KCModules"
HOMEPAGE = "https://api.kde.org/frameworks/kcmutils/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.0-only"

inherit kf6

DEPENDS:append = " \
    libxml2 libxml2-native \
    qtdeclarative6 \
    \
    kio6 \
    kitemviews6 \
    kconfigwidgets6 \
    kguiaddons6 \
    kxmlgui6 \
    kwidgetsaddons6 \
"

PACKAGECONFIG[tools-only] = "-DTOOLS_ONLY=ON, TOOLS_ONLY=OFF, "
PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    dbus \
"

PACKAGECONFIG:append:class-native = " tools-only"
