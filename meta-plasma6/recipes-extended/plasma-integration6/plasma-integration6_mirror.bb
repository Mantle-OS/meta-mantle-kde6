# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org> 
# SPDX-FileCopyrightText: 2019 Alistair Francis <alistair.francis@wdc.com>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Plasma Integration"
HOMEPAGE = ""
LICENSE = "LGPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.0-only.txt;md5=da48810c4ddf8e49efa031294a26b98c"

inherit plasma6

DEPENDS:append = " \
    wayland wayland-native \
    plasma-wayland-protocols-kf6 \
    \
    libxcb \
    \
    xdg-desktop-portal-kde6 \
    libxcursor \
    \
    qtdeclarative6 \
    qtwayland6 qtwayland6-native \
"


PACKAGECONFIG[noto-sans] = "-DCMAKE_DISABLE_FIND_PACKAGE_FontNotoSans=fasle, \
                            -DCMAKE_DISABLE_FIND_PACKAGE_FontNotoSans=true, \
                            ttf-roboto"

PACKAGECONFIG[noto-color-emoji] = "-DCMAKE_DISABLE_FIND_PACKAGE_FontNotoColorEmoji=false, \
                                   -DCMAKE_DISABLE_FIND_PACKAGE_FontNotoColorEmoji=true, \
                                   ttf-noto-emoji" 

PACKAGECONFIG[font-hack] = "-DCMAKE_DISABLE_FIND_PACKAGE_FontHack=false, \
                            -DCMAKE_DISABLE_FIND_PACKAGE_FontHack=true, \
                            fonthack "
PACKAGECONFIG:append = " \
    noto-sans \
    noto-color-emoji \
    font-hack \
"

RDEPENDS:${PN} += " \
    perl \
"

EXTRA_OECMAKE:append = " -DBUILD_QT5=OFF "
