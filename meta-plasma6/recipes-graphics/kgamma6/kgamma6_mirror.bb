# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KGamma"
SUMMARY = "Adjust your monitor's gamma settings"
HOMEPAGE = "https://invent.kde.org/plasma/kgamma"
LICENSE = "GPL-2.0-or-later"

inherit plasma6

DEPENDS:append = " \
    plasma-wayland-protocols-kf6 \
    \
    qtwayland6 qtwayland6-native \
    wayland-native wayland \
    wayland-protocols6 \
    virtual/egl \
"

## FIXME
INSANE_SKIP:${PN} = "dev-so"
