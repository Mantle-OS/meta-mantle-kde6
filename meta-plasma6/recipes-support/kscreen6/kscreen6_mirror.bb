# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KScreen"
HOMEPAGE = ""
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=9e2385fe012386d34dcc5c9863070881"

inherit plasma6


DEPENDS:append = " \
    kdbusaddons6 \
    kcmutils6 kcmutils6-native \
    ksvg6 \
    kxmlgui6 \
    kcrash6 \
    \
    qtdeclarative6 \
    qtsensors6 \
    wayland qtwayland6 qtwayland6-native \
    \
    layer-shell-qt6 \
    libkscreen6 \
    libplasma6 \
"

PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, libxcb virtual/libx11"
PACKAGECONFIG:append = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
"

FILES:${PN} += " \
    ${datadir}/kscreen \
    ${datadir}/kglobalaccel \
"
