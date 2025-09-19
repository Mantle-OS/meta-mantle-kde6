# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020-2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Add support for global workspace shortcuts"
HOMEPAGE = "https://api.kde.org/frameworks/kglobalaccel/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.0-or-later"

inherit kf6

PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, "
PACKAGECONFIG:append = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
"
