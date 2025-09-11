# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Support components for porting from KF5/Qt5 to KF6/Qt6"
HOMEPAGE = "https://invent.kde.org/plasma/plasma5support"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=fed54355545ffd980b814dab4a3b312c"
PR = "r0"

inherit cmake_plasma

SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/plasma5support-6.4.4.tar.xz"
SRC_URI[sha256sum] = "066c0456149bacad6dfd99eb36783fb61f1918df91b9d70ff4aeac56e3d43bf5"

DEPENDS = " \
    kconfig6 kcoreaddons6 \
    ki18n6 \
    kio6 \
    kidletime6 \
    kservice6 \
    kxmlgui6 \
    libksysguard6 \
"

FILES:${PN} += " \
    ${libdir}/qml/org/kde/plasma/plasma5support/* \
"
