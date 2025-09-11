# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "KGamma"
SUMMARY = "Adjust your monitor's gamma settings"
HOMEPAGE = "https://invent.kde.org/plasma/kgamma"
LICENSE = "GPL-2.0-or-later"
PR = "r0"

DEPENDS = " \
    plasma-wayland6-protocols6 \
    qtbase6 \
    qtwayland6 \
    qtwayland6-native \
    wayland-native \
    wayland \
    wayland-protocols6 \
    virtual/egl \
"

inherit cmake_plasma
inherit reuse_license_checksums

SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/kgamma-6.4.4.tar.xz"
SRC_URI[sha256sum] = "1bc752415d4711580f483f774a62d8959583686b93b164484a89d7d5571334a6"
INSANE_SKIP:${PN} = "dev-so"
FILES:${PN} += " \
    ${libdir}/plugins/plasma/kcminit/kcm_kgamma_init.so \
"
