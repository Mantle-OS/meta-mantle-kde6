# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2019-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Support for vCard contacts"
HOMEPAGE = "https://api.kde.org/frameworks/kcontacts/html/index.html"
LICENSE = "BSD-3-Clause AND LGPL-2.0-or-later AND MIT AND Unicode-DFS-2016"

inherit kf6

DEPENDS:append = " kcodecs6"

PACKAGECONFIG[qml] = "-DUSE_QML=ON, -DUSE_QML=OFF, qtdeclarative6"

PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    ki18n \
"
PACKAGECONFIG:append:class-target = " qml"
