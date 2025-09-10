# SPDX-FileCopyrightText: 2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Terminal app for Plasma Mobile"
HOMEPAGE = "https://invent.kde.org/plasma-mobile/qmlkonsole"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881"
PR = "r0"

DEPENDS = " \
    qtbase6 \
    qtdeclarative6 \
    kconfig6 \
    kconfig6-native \
    ki18n6 \
    kirigami6 \
    kirigami6-addons6 \
    kwindowsystem6 \
    kpty6 \
"

inherit cmake_kdeapp
inherit kf6-kcoreaddons
inherit mime-xdg

RDEPENDS:${PN} += " \
    qtdeclarative6-qmlplugins \
    qmltermwidget6 \
"
