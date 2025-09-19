# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020-2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Plasma library and runtime components"
HOMEPAGE = "https://invent.kde.org/plasma/libplasma"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-only.txt;md5=fabba2a3bfeb22a6483d44e9ae824d3f"

inherit plasma6

DEPENDS:append = " \
    plasma-activities6 \
    karchive6 \
    kcmutils6 kcmutils6-native \
    kconfig6 kconfig6-native \
    kconfigwidgets6 \
    kdbusaddons6 \
    kdeclarative6 \
    kglobalaccel6 \
    kguiaddons6 kcoreaddons6-native \
    kiconthemes6 \
    kio6 \
    kauth-native \
    kservice6 \
    ksvg6 \
    kwindowsystem6 \
    kxmlgui6 \
    knotifications6 \
    kpackage6 kpackage6-native \
    kwayland6 \
    kirigami6 \
    \
    gzip-native \
    \
    qtbase6 \
    qtwayland6-native \
"
