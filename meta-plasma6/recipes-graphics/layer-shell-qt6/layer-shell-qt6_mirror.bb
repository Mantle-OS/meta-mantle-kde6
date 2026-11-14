# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Layer Shell Qt"
DESCRIPTION = "Qt component to allow applications to make use of the Wayland wl-layer-shell protocol"
HOMEPAGE = "https://invent.kde.org/plasma/layer-shell-qt"
LICENSE = "BSD-3-Clause AND LGPL-3.0-or-later AND MIT"

inherit plasma6
inherit features_check

REQUIRED_DISTRO_FEATURES = "wayland"

DEPENDS:append = " \
    qtdeclarative6 \
    qtwayland6 qtwayland6-native \
    wayland-native wayland \
    wayland-protocols \
    libxkbcommon \
    xkeyboard-config \
"
