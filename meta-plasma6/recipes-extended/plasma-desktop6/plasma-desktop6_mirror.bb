# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Plasma for the Desktop"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-desktop"
LICENSE = "(LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL) & (GPL-2.0-only | GPL-3.0-only | LicenseRef-KDE-Accepted-GPL)"
LIC_FILES_CHKSUM = " \
    file://LICENSES/GPL-2.0-only.txt;md5=9e2385fe012386d34dcc5c9863070881 \
    file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881 \
    file://LICENSES/GPL-3.0-only.txt;md5=49fc03046e56a282c0c743b5d3a55b7c \
    file://LICENSES/LGPL-2.1-only.txt;md5=147a320ed8b16b036829a0c71d424153 \
    file://LICENSES/LGPL-3.0-only.txt;md5=8d51f5b5fd447f7a1040c3dc9f0a8de6 \
    file://LICENSES/LicenseRef-KDE-Accepted-GPL.txt;md5=b4c280013bbbadfbe92219498dc5228c \
"
inherit plasma6

DEPENDS:append = " \
    kwayland6 \
    libplasma6 \
    plasma-workspace6 \
    qtwayland6-native \
    libxkbfile \
"


EXTRA_OECMAKE += " \
    -DBUILD_KCM_MOUSE_X11=OFF \
    -DBUILD_KCM_TOUCHPAD_X11=OFF \
    -DBUILD_KCM_TABLET=OFF \
"


INSANE_SKIP:${PN} = "dev-so"

RDEPENDS:${PN} += " \
    gawk \
    kded6 \
    plasma-nano6 \
    kirigami6 \
    kirigami6-addons6 \
    qqc2-desktop-style6 \
"
