# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KGamma"
SUMMARY = "Adjust your monitor's gamma settings"
HOMEPAGE = "https://invent.kde.org/plasma/kgamma"
LICENSE = "GPL-2.0-or-later"

inherit plasma6

DEPENDS:append = " \
    kconfigwidgets6 \
    kcmutils6 kcmutils6-native \
    libxxf86vm \
    plasma-wayland-protocols-kf6 \
    \
    qtwayland6 qtwayland6-native \
    wayland-native wayland \
    wayland-protocols \
    virtual/egl \
"

FILES:${PN} += " \
    ${datadir}/kgamma \
"

INSANE_SKIP:${PN}-plugins = "dev-so"
