# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018-2019 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Configuration system for KNotify"
HOMEPAGE = "https://api.kde.org/frameworks/knotifyconfig/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.0-only"

inherit kf6

DEPENDS:append = " \
    qtmultimedia6 \
    phonon-kf6 \
    \
    kcompletion6 \
    kio6 \
"

PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "
PACKAGECONFIG[canberra] = ",, libcanberra"
PACKAGECONFIG[tests] = "-DBUILD_TESTING=true, -DBUILD_TESTING=false, kxmlgui6 kwidgetsaddons6 knotifications6"

PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    kauth \
    dbus \
    canberra \
"
