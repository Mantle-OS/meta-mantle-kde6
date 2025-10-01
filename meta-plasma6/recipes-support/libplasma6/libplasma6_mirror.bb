# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020-2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
# SPDX-License-Identifier: MIT
SUMMARY = "Plasma library and runtime components"
HOMEPAGE = "https://invent.kde.org/plasma/libplasma"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"

inherit plasma6
inherit features_check

REQUIRED_DISTRO_FEATURES = "opengl wayland"

DEPENDS:append = " \
    plasma-activities6 \
    \
    karchive6 \
    kcmutils6 kcmutils6-native \
    kconfigwidgets6 \
    kdbusaddons6 \
    kdeclarative6 \
    kglobalaccel6 \
    kguiaddons6 \
    kiconthemes6 \
    kio6 \
    kservice6 \
    ksvg6 \
    kwindowsystem6 \
    kxmlgui6 \
    knotifications6 \
    kwayland6 \
    kirigami6 \
    \
    gzip-native \
    \
    qtdeclarative6 \
    qtwayland6-native \
    virtual/libgl \
    virtual/egl \
"

PACKAGECONFIG[x11] = "-DWITHOUT_X11=OFF, -DWITHOUT_X11=ON, libxdamage virtual/libx11 libxcb libxcomposite libxfixes libxrender"
PACKAGECONFIG[coverage] = "-DBUILD_COVERAGE=ON, -DBUILD_COVERAGE=OFF, " 
PACKAGECONFIG:append = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
"
FILES:${PN} += " \
    ${datadir}/plasma/desktoptheme \
"
