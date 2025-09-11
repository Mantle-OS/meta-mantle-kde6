# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0

require ${PN}.inc
SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/kwayland-6.4.4.tar.xz"
SRC_URI[sha256sum] = "f458ae7ed1118e1a2165a37378c8ce0e9fff80d1a4cfe86217f06cf24ca49f9b"

# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

SUMMARY = "Qt-style API to interact with the wayland-client and wayland-server API"
HOMEPAGE = "https://api.kde.org/frameworks/kwayland/html/index.html"
LICENSE = "MIT & MIT-CMU & BSD-3-Clause & (LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL)"
PR = "r0"

DEPENDS = " \
    plasma-wayland-protocols6 \
    qtbase6 \
    qtwayland6 qtwayland6-native \
    wayland-native \
    wayland \
    wayland-protocols \
    virtual/egl \
"

inherit cmake_plasma
inherit reuse_license_checksums
