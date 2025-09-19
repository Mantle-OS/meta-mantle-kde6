# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

SUMMARY = "Plasma System Monitor"
DESCRIPTION = "An interface for monitoring system sensors, process information and other system resources"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-systemmonitor"
LICENSE = "GPL-2.0-only | GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"
PR = "r0"

inherit cmake_plasma
inherit kf6-kdoctools

SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/plasma-systemmonitor-6.4.4.tar.xz"
SRC_URI[sha256sum] = "c9d4a27ef93f1ec29a9b181c56dbf16ce81aa1a6ad30c837ff987c3552888e78"

DEPENDS = " \
    ki18n6 \
    kitemmodels6 \
    kconfig6 \
    kdeclarative6 \
    kservice6 \
    kglobalaccel6 \
    kio6 \
    kdbusaddons6 \
    knewstuff6 \
    libksysguard6 \
    kirigami6-addons6 \
    qtbase6 \
    qtdeclarative6 \
"

RDEPENDS:${PN} = "ksystemstats6"

FILES_SOLIBSDEV = ""

# Do not try to detect the ksystemstats runtime at configure, hardcode
# a value to skip detection.
EXTRA_OECMAKE:append = " \
    -Dksystemstats_EXE=/usr/bin/ksystemstats \
"
