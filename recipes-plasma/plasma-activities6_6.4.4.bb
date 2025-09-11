# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020-2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

SUMMARY = "Core components for the KDE's Activities System"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-activities"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-only.txt;md5=fabba2a3bfeb22a6483d44e9ae824d3f"
PR = "r0"

inherit cmake_plasma

SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/plasma-activities-6.4.4.tar.xz"
SRC_URI[sha256sum] = "ab810aa594ed3386f6a8564705ea2a46cc62ec367de2039625e030f6af955fe8"
DEPENDS = " \
    kcoreaddons6 kcoreaddons6-native \
    kconfig6 kconfig6-native \
    kwindowsystem6 \
    boost \
    qtbase6 \
    qtdeclarative6 \
"


FILES:${PN} += " \
    ${libdir}/qt6/qml/org/kde/activities/* \
"
