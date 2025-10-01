# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2019 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Breeze Style"
HOMEPAGE = ""
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"

inherit plasma6

DEPENDS:append = " \
    frameworkintegration6 \
    kcmutils6 kcmutils6-native \
    \ 
    qtsvg6 \
    qtdeclarative6 \
    \
    kcolorscheme6 \
    kguiaddons6 \
    kiconthemes6 \
    kwindowsystem6 \
    kirigami6 \
"

EXTRA_OECMAKE:append = " \
    -DBUILD_QT5:BOOL=OFF \
"

PACKAGECONFIG[decorations] = "-DWITH_DECORATIONS=ON, -DWITH_DECORATIONS=OFF, kdecoration6"
PACKAGECONFIG[wallpaper] = "-DWITH_WALLPAPERS=ON, -DWITH_WALLPAPERS=OFF, "
PACKAGECONFIG:append = " \
    decorations \
    wallpaper \
"

RDEPENDS:${PN} += " \
    breeze-icons6 \
"

FILES:${PN} += " \
    ${datadir}/kstyle \
    ${datadir}/QtCurve \
    ${datadir}/color-schemes \
    ${datadir}/wallpapers \
"
