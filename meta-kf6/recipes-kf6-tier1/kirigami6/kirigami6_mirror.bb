# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "QtQuick plugins to build user interfaces based on the KDE human interface guidelines"
HOMEPAGE = "https://api.kde.org/frameworks/kirigami/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.0-or-later"
PR = "r0"

inherit kf6

DEPENDS:append = " \
    qtdeclarative6 \
    qtsvg6 \
    openmp-native \
"

RDEPENDS:${PN} += " \
    qtdeclarative6-qmlplugins \
"

PACKAGECONFIG[desktop] = "-DDESKTOP_ENABLED=ON, -DDESKTOP_ENABLED=OFF, "
PACKAGECONFIG[ubuntu-touch] = "-DUBUNTU_TOUCH=ON, -DUBUNTU_TOUCH=OFF, "
PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "

PACKAGECONFIG:append = " desktop dbus"
