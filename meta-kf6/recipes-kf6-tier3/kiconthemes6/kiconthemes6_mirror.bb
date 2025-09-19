# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Support for icon themes"
HOMEPAGE = "https://api.kde.org/frameworks/kiconthemes/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"

inherit kf6 allarch gtk-icon-cache

DEPENDS:append = " \
    breeze-icons6 \
    karchive6 \
    kcolorscheme6 \
    kwidgetsaddons6 \
    qtsvg6 \
"
PACKAGECONFIG[qml] = "-DKICONTHEMES_USE_QTQUICK=ON, -DKICONTHEMES_USE_QTQUICK=OFF, qtdeclarative6"
PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "

PACKAGECONFIG:append = " \
    kconfig \
    ki18n \
    qml \
    dbus \
"
