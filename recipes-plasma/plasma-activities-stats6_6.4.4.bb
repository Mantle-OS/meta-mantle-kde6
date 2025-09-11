# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

SUMMARY = "A library for accessing the usage data collected by the activities system"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-activities-stats"
LICENSE = "LGPL-2.0-or-later & (LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL) & (GPL-2.0-only | GPL-3.0-only | LicenseRef-KDE-Accepted-GPL)"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-only.txt;md5=fabba2a3bfeb22a6483d44e9ae824d3f"
PR = "r0"

inherit cmake_plasma

SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/plasma-activities-stats-6.4.4.tar.xz"
SRC_URI[sha256sum] = "dd43899617b0cc0e927a13bfb88146b761f7390c1fba568e2e4522f07f0f5901"
DEPENDS = " \
    kconfig6 kconfig6-native \
    plasma-activities6 \
    kcoreaddons6-native \
    kauth6-native \
    boost \
    qtbase6 \
"
