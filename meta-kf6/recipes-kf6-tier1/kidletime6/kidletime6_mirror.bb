# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2014 Johan Thelin <johan.thelin@pelagicore.com>
# SPDX-FileCopyrightText: 2016 Sébastien Taylor <sebastien@au-zone.com>
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

# GPL-2.0+ only used in exmaple and test code
SUMMARY = "Kde6 Monitoring user activity"
SECTION = "libs"
HOMEPAGE = "https://api.kde.org/frameworks/kidletime/html/index.html"
LICENSE = "LGPL-2.1-or-later AND MIT"

inherit kf6
REUSE_LICENSECHECK_WHITELIST = "GPL-2.0-or-later"

PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, \
    virtual/libx11 libxcb libxext libxscrnsaver libxkbcommon \
"
PACKAGECONFIG[wayland] = "-DWITH_WAYLAND=ON, -DWITH_WAYLAND=OFF, \
    qtwayland6 qtwayland6-native plasma-wayland-protocols-kf6"

PACKAGECONFIG:append = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'wayland', d)} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
    qml \
"
