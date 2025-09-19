# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Provides access to all contacts and the people who hold them"
HOMEPAGE = "https://api.kde.org/frameworks/kpeople/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.1-or-later"

inherit kf6

DEPENDS:append = " \
    qtdeclarative6 \
    kcontacts6 \
    kwidgetsaddons6 \
    kitemviews6 \
"

PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "
PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    ki18n \
    dbus \
"
