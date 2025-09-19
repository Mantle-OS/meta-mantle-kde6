# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Terminal app for Plasma Mobile"
HOMEPAGE = "https://invent.kde.org/plasma-mobile/qmlkonsole"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881"

inherit rs6
PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    ki18n \
"

DEPENDS:append = " \
    qtdeclarative6 \
    kirigami6 kirigami6-addons6 \
    kwindowsystem6 \
    kpty6 \
"

RDEPENDS:${PN} += " \
    qtdeclarative6-qmlplugins \
    qmltermwidget6 \
"
