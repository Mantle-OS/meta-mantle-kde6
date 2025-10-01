# SPDX-FileCopyrightText: 2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Support components for porting from KF5/Qt5 to KF6/Qt6"
HOMEPAGE = "https://invent.kde.org/plasma/plasma5support"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=fed54355545ffd980b814dab4a3b312c"

inherit plasma6

DEPENDS:append = " \
    kguiaddons6 \
    knotifications6 \
    solid6 \
    kservice6 \
    kidletime6 \
    kio6 \
    \
    libksysguard6 \
    networkmanager-qt6 \
    plasma-activities6 \
    \
    qtdeclarative6 \
"

PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, virtual/libx11" 
PACKAGECONFIG[coverage] = "-DBUILD_COVERAGE=ON, -DBUILD_COVERAGE=OFF, "

PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    ki18n \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
"

FILES:${PN} += " \
    ${datadir}/plasma5support \
"

