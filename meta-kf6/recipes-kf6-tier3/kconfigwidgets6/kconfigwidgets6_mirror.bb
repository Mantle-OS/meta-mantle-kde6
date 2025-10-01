# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Widgets for configuration dialogs"
HOMEPAGE = "https://api.kde.org/frameworks/kconfigwidgets/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"

inherit kf6

DEPENDS:append = " \
    kcodecs6 \
    kcolorscheme6 \
    kguiaddons6 \
    kwidgetsaddons6 \
"

PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "
PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    kauth \
    ki18n \
    dbus \
"

RDEPENDS:${PN}-dev += " \
    perl \
"
BBCLASSEXTEND = "native nativesdk"
