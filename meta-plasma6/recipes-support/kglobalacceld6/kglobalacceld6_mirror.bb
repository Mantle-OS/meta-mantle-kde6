# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KGlobalAccelD"
HOMEPAGE = ""
LICENSE = "LGPL-2.1-only OR LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-only.txt;md5=41890f71f740302b785c27661123bff5"

inherit plasma6

DEPENDS:append = " \
    kcrash6 \
    kdbusaddons6 \
    kwindowsystem6 \
    kglobalaccel6 \
    kservice6 \
    kio6 \
    kjobwidgets6 \
"

PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, \
 virtual/libx11 \
 libxcb \
 xcb-util-keysyms \
 xorgproto xcb-proto \
 libxkbcommon \
 libxtst \ 
"

PACKAGECONFIG:append = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
"
