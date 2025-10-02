# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KDE Cli Tools"
HOMEPAGE = ""
LICENSE = "GPL-2.0-or-later & LGPL-2.0-or-later & (LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL) & (GPL-2.0-only | GPL-3.0-only | LicenseRef-KDE-Accepted-GPL) & LGPL-2.0-only & Artistic-2.0"

inherit plasma6

DEPENDS:append = " \
    qtsvg6 \
    \    
    kiconthemes6 \
    kcmutils6 kcmutils6-native \
    kio6 \
    kservice6 \
    kwindowsystem6 \
    kparts6 \
    kdesu6 \
    kwidgetsaddons6 \
"


#    plasma-activities6 
#    plasma-workspace6 

PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, "
PACKAGECONFIG:append = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
"

FILES:${PN} += " \
    ${datadir}/zsh \
"
