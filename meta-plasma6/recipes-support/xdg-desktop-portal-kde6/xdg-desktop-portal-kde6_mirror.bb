# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020-2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "XDG Desktop Portal KDE"
HOMEPAGE = ""
LICENSE = "LGPL-2.1-only & GPL-2.0-or-later"

inherit plasma6

DEPENDS:append = " \
    knotifications \
    kwayland6 \
    kwidgetsaddons6 \
    kirigami6 \
    kio6 \
    kdeclarative6 \
    kstatusnotifieritem6 \
    libplasma6 \
    pipewire \
    qtwayland6-native \
"

RDEPENDS:${PN} += " \
    xdg-desktop-portal \
"
PACKAGECONFIG:append = " \
    kcoreaddons \
"
