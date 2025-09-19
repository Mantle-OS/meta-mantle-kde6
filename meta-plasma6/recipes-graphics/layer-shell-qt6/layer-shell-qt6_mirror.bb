# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Layer Shell Qt"
DESCRIPTION = "Qt component to allow applications to make use of the Wayland wl-layer-shell protocol"
HOMEPAGE = "https://invent.kde.org/plasma/layer-shell-qt"
LICENSE = "BSD-3-Clause & MIT & LGPL-3.0-or-later"

inherit plasma6

DEPENDS = " \
    qtwayland6 qtwayland6-native \
    wayland-native wayland \
    wayland-protocols \
"

FILES:${PN} += " \
    ${libdir}/qml/org/kde/layershell/* \
"

