# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "QtQuickControls 2 style that integrates with the desktop"
HOMEPAGE = "https://api.kde.org/frameworks/qqc2-desktop-style/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-3.0-only"

inherit kf6 features_check

REQUIRED_DISTRO_FEATURES = "x11"

DEPENDS:append = " \
    qtdeclarative6 \
    kirigami6 \
    kiconthemes6 \
    kconfigwidgets6 \
    kcolorscheme6 \
"

PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "
PACKAGECONFIG[x11] = " , , virtual/libx11"

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
    dbus \
"

PACKAGECONFIG:append:class-target = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'X11', d)} \
"
