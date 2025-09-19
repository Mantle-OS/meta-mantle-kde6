# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2014 Johan Thelin <johan.thelin@pelagicore.com>
# SPDX-FileCopyrightText: 2016 Sébastien Taylor <sebastien@au-zone.com>
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Addons to QtDBus"
HOMEPAGE = "https://api.kde.org/frameworks/kdbusaddons/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.0-or-later & (LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL)"

inherit kf6 features_check
ANY_OF_DISTRO_FEATURES = "opengl wayland"

PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, virtual/libx11"
PACKAGECONFIG:append = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
"
