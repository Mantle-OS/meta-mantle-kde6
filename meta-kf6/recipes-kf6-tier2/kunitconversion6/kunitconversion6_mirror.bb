# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2014 Johan Thelin <johan.thelin@pelagicore.com>
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Support for unit conversion"
HOMEPAGE = "https://api.kde.org/frameworks/kunitconversion/html/index.html"
LICENSE = "LGPL-2.0-or-later"

inherit kf6

DEPENDS:append = " \
    ki18n6 ki18n6-native \
" 

PACKAGECONFIG[py-bindings] = "BUILD_PYTHON_BINDINGS=ON, -DBUILD_PYTHON_BINDINGS=OFF,\
    python3-pyside6-native python3-native python3-shiboken6 "

PACKAGECONFIG:append = " "
