# SPDX-FileCopyrightText: 2019-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KDialog"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881"

inherit rs6

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
"

DEPENDS:append = " \
    ktextwidgets6 \
    knotifications6 \
    kdbusaddons6 \
    kiconthemes6 \
    kwindowsystem6 \    
    kio6 \
"

PACKAGECONFIG[x11] = "-DWITHOUT_X11=OFF, -DWITHOUT_X11=ON, virtual/libx11"
PACKAGECONFIG:append = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
"

FILES:${PN} += " \
    ${datadir}/metainfo \
"
