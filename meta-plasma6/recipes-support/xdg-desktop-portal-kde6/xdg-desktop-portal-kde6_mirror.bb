# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020-2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "XDG Desktop Portal KDE"
HOMEPAGE = ""
LICENSE = "GPL-2.0-or-later AND LGPL-2.1-only"

inherit plasma6

DEPENDS:append = " \
    kguiaddons6 \
    kglobalaccel6 \
    kio6 \
    kirigami6 \
    knotifications6 \
    kservice6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
    kiconthemes6 \
    kstatusnotifieritem6 \
    kcrash6 \
    \
    kwayland6 \
    wayland wayland-native \
    plasma-wayland-protocols-kf6 \
    libxkbcommon \
    \
    qtwayland6 qtwayland6-native \
    qtdeclarative6 \
"

RDEPENDS:${PN} += " \
    xdg-desktop-portal \
"

FILES:${PN} += " \
    ${datadir}/xdg-desktop-portal \
    ${datadir}/knotifications6 \
"
