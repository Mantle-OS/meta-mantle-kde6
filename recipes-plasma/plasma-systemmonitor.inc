# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

SUMMARY = "Plasma System Monitor"
DESCRIPTION = "An interface for monitoring system sensors, process information and other system resources"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-systemmonitor"
LICENSE = "GPL-2.0-only | GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"
PR = "r0"

DEPENDS = " \
    qtbase6 \
    qtdeclarative6 \
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
"

RDEPENDS:${PN} = "ksystemstats6"

inherit cmake_plasma
inherit kf6-kdoctools

FILES_SOLIBSDEV = ""
FILES:${PN} += " \
    ${datadir}/kglobalaccel/ \
    ${datadir}/ksysguard/sensorfaces/ \
    ${datadir}/plasma/kinfocenter/externalmodules/ \
    ${libdir}/qml/org/kde/ksysguard/table/ \
    ${libdir}/qml/org/kde/ksysguard/page/ \
    ${libdir}/libPlasmaSystemMonitorTable.so \
    ${libdir}/libPlasmaSystemMonitorPage.so \
"

# Do not try to detect the ksystemstats runtime at configure, hardcode
# a value to skip detection.
EXTRA_OECMAKE:append = " \
    -Dksystemstats_EXE=/usr/bin/ksystemstats \
"
