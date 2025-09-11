# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2022 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "KPipeWire"
HOMEPAGE = "https://invent.kde.org/plasma/kpipewire"
LICENSE = "LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL"
LIC_FILES_CHKSUM = " \
    file://LICENSES/LGPL-2.1-only.txt;md5=41890f71f740302b785c27661123bff5 \
    file://LICENSES/LGPL-3.0-only.txt;md5=852ecadc0ac7e6f4d7144d5544a3815b \
    file://LICENSES/LicenseRef-KDE-Accepted-LGPL.txt;md5=e4b79a181b6483b37d39a27f4d75e60a \
"
PR = "r0"

inherit cmake_plasma

SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/kpipewire-6.4.4.tar.xz"
SRC_URI[sha256sum] = "097f9971580cc7ce3bd89e73ced7606e3978347bd44131d6744fe02d8a1b3ea6"

## YIKES FIXME ffmpeg license check
DEPENDS = " \
    libepoxy \
    qtdeclarative6 \
    qtwayland6-native \
    pipewire \
    kwayland6 \
    ffmpeg \
    libva \
"


FILES:${PN} += " \
    ${libdir}/qml/org/kde/pipewire/* \
    ${datadir}/zsh/site-functions/* \
"

RDEPENDS:${PN} += " \
    pipewire \
"
