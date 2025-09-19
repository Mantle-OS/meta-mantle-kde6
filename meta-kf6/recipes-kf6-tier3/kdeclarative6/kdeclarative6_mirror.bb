# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018-2019 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Provides integration of QML and KDE Frameworks"
HOMEPAGE = "https://api.kde.org/frameworks/kdeclarative/html/index.html"
LICENSE = "LGPL-2.0-or-later & LGPL-2.1-or-later & MIT"

inherit kf6

DEPENDS:append = " \
    kglobalaccel6 \
    kguiaddons6 \
    kiconthemes6 \
    kio6 \
    knotifications6 \
    kpackage6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
    \
    qtdeclarative6 \
"

DEPENDS:class-target += " \
    kpackage6-native \
"

PACKAGECONFIG:append = " \
    kauth \
    kconfig \
    ki18n \
"

RDEPENDS:${PN} += " \
    qtdeclarative6-qmlplugins \
"
