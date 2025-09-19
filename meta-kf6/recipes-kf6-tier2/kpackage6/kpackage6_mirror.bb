# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
# SPDX-FileCopyrightText: 2022 MBition GmbH
# SPDX-FileContributor: Kai Uwe Broulik <kai_uwe.broulik@mbition.io>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Library to load and install packages of non binary files as they were a plugin"
HOMEPAGE = "https://api.kde.org/frameworks/kpackage/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.0-or-later & GPL-2.0-or-later"

inherit kf6


DEPENDS:append = " \
    karchive6 \
"

DEPENDS:append:class-target = " ${PN}-native" 
DEPENDS:append:class-nativesdk = " ${PN}-native"

PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "

PACKAGECONFIG:append = " \
    kcoreaddons \
    ki18n \
    kdoctools \
    dbus \
" 
