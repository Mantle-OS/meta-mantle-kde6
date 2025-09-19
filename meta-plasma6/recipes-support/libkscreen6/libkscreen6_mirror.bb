# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "KScreen library"
HOMEPAGE = ""
LICENSE = "LGPL-2.1-or-later | (GPL-2.0-only | GPL-3.0-only | LicenseRef-KDE-Accepted-GPL)"
LIC_FILES_CHKSUM = " \
    file://LICENSES/GPL-2.0-only.txt;md5=9e2385fe012386d34dcc5c9863070881 \
    file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881 \
    file://LICENSES/GPL-3.0-only.txt;md5=49fc03046e56a282c0c743b5d3a55b7c \
    file://LICENSES/LGPL-2.1-or-later.txt;md5=147a320ed8b16b036829a0c71d424153 \
    file://LICENSES/LicenseRef-KDE-Accepted-GPL.txt;md5=b4c280013bbbadfbe92219498dc5228c \
"
PR = "r0"
inherit cmake_plasma

SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/libkscreen-6.4.4.tar.xz"
SRC_URI[sha256sum] = "dc9fee7a8334c24b4f94c33d9f31b093c13cd8fb7de06314e8edfa3a28cab4f6"

DEPENDS = " \
    qtwayland6-native \
    kwayland6 \
"


FILES:${PN} += " \
    ${datadir}/zsh/site-functions/_kscreen-doctor \
"
