# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2014 Johan Thelin <johan.thelin@pelagicore.com>
# SPDX-FileCopyrightText: 2016 Sébastien Taylor <sebastien@au-zone.com>
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Addons to QtWidgets"
HOMEPAGE = "https://api.kde.org/frameworks/kwidgetsaddons/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.0-or-later & (LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL)"

inherit kf6

EXTRA_OECMAKE += " \
    -DBUILD_DESIGNERPLUGIN=OFF \
"

PACKAGECONFIG[py-bindings] = "-DBUILD_PYTHON_BINDINGS=ON, -DBUILD_PYTHON_BINDINGS=OFF, python3-native python3-pyside6  python3-shiboken6-native "
#PACKAGECONFIG[designer] = " -DBUILD_DESIGNERPLUGIN=ON, -DBUILD_DESIGNERPLUGIN=OFF, qttools6-native"

PACKAGECONFIG:append = " "
