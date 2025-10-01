# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KScreen library"
HOMEPAGE = ""
LICENSE = "LGPL-2.1-or-later | (GPL-2.0-only | GPL-3.0-only | LicenseRef-KDE-Accepted-GPL)"

inherit plasma6
inherit features_check 

REQUIRED_DISTRO_FEATURES = "wayland"

DEPENDS:append = " \
    qtwayland6 qtwayland6-native \
    plasma-wayland-protocols-kf6 \
    wayland \
    kwayland6 \
    jq-native \
    libxcb libxrandr libxext \
"

RDEPENDS:${PN} += " \
    jq \
"

FILES:${PN} += " \
    ${datadir}/zsh \
"
