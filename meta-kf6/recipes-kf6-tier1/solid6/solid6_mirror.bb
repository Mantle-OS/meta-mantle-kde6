# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2014 Johan Thelin <johan.thelin@pelagicore.com>
# SPDX-FileCopyrightText: 2016 Sébastien Taylor <sebastien@au-zone.com>
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020-2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Hardware integration and detection"
HOMEPAGE = "https://api.kde.org/frameworks/solid/html/index.html"
LICENSE = "BSD-3-Clause & LGPL-2.1-or-later & (LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL)"

inherit kf6

DEPENDS:append = " \
    flex-native \
    bison-native \
    util-linux \
"

DEPENDS:append:class-target = " upower"

PACKAGECONFIG[udev] = "-DUDEV_DISABLED=OFF, -DUDEV_DISABLED=ON, udev"
PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "

PACKAGECONFIG:append = " dbus"
PACKAGECONFIG:append:class-target = " udev"

RRECOMMENDS:${PN}:append:class-target = " \
    udisks2 \
    upower \
"

BBCLASSEXTEND = "native nativesdk"
