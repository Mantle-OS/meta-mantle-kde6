
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Plasma System Monitor"
DESCRIPTION = "An interface for monitoring system sensors, process information and other system resources"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-systemmonitor"
LICENSE = "GPL-2.0-only | GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"

inherit plasma6

DEPENDS:append = " \
    qtdeclarative6 \
    \
    kcrash6 \
    kservice6 \
    kglobalaccel6 \
    kio6 \
    kdbusaddons6 \
    knewstuff6 \
    kirigami6 \
    kitemmodels6 \
    kwindowsystem6 \
    kpackage6 kpackage6-native \
    libksysguard6 \
    ksystemstats6 \
    kirigami6 \
"

# Do not try to detect the ksystemstats runtime at configure, hardcode
# a value to skip detection.
#EXTRA_OECMAKE:append = " -Dksystemstats_EXE=/usr/bin/ksystemstats \"
