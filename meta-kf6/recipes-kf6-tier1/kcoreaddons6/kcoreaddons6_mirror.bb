# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2014 Johan Thelin <johan.thelin@pelagicore.com>
# SPDX-FileCopyrightText: 2016 Sébastien Taylor <sebastien@au-zone.com>
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Addons to QtCore"
HOMEPAGE = "https://api.kde.org/frameworks/kcoreaddons/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"
REUSE_LICENSECHECK_WHITELIST = "CC0-1.0"

inherit kf6

DEPENDS:append:class-target = " udev ${PN}-native"
DEPENDS:append:class-nativesdk = " ${PN}-native"

PACKAGECONFIG[pch] = "-DENABLE_PCH=ON, -DENABLE_PCH=OFF, "
PACKAGECONFIG[qml] = "-DKCOREADDONS_USE_QML=ON, -DKCOREADDONS_USE_QML=OFF, qtdeclarative6"
PACKAGECONFIG[inotify] = "-DENABLE_INOTIFY=ON, -DENABLE_INOTIFY=OFF, inotify-tools"
PACKAGECONFIG[py-bindings] = "-DBUILD_PYTHON_BINDINGS=ON, -DBUILD_PYTHON_BINDINGS=OFF, python3-native python3-pyside6  python3-shiboken6-native "

PACKAGECONFIG:append:class-target = " inotify pch qml"
BBCLASSEXTEND = "native nativesdk"
