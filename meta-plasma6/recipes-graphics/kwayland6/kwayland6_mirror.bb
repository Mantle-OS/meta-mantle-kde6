# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Qt-style API to interact with the wayland-client and wayland-server API"
HOMEPAGE = "https://api.kde.org/frameworks/kwayland/html/index.html"
LICENSE = "MIT & MIT-CMU & BSD-3-Clause & (LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL)"

inherit plasma6

DEPENDS:append = " \
    plasma-wayland-protocols6 \
    qtbase6 \
    qtwayland6 qtwayland6-native \
    wayland-native wayland \
    wayland-protocols \
    virtual/egl \
"
