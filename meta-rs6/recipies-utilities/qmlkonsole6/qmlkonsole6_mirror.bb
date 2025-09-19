# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Terminal app for Plasma Mobile"
HOMEPAGE = "https://invent.kde.org/plasma-mobile/qmlkonsole"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881"

inherit cmake_kdeapp
inherit kf6-kcoreaddons kf6-kconfig

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/qmlkonsole-25.08.0.tar.xz"
SRC_URI[sha256sum] = "06cb9155536079d40a68cf8e8ac52dc8e686dfb68e9fb2f078c858c1465ca424"

DEPENDS += " \
    qtdeclarative6 \
    ki18n6 \
    kirigami6 kirigami6-addons6 \
    kwindowsystem6 \
    kpty6 \
"

RDEPENDS:${PN} += " \
    qtdeclarative6-qmlplugins \
    qmltermwidget6 \
"
