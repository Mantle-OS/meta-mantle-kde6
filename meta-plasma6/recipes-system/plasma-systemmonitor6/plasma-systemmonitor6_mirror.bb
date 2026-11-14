# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Plasma System Monitor"
DESCRIPTION = "An interface for monitoring system sensors, process information and other system resources"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-systemmonitor"
LICENSE = "GPL-2.0-only OR GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"

inherit plasma6

SRC_URI:append = " \
    file://0001-so-version-to-shutup-qa.patch \
    file://0002-add-table-so-version-as-well.patch \
"

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
    kirigami6-addons \
    kitemmodels6 \
    kwindowsystem6 \
    kpackage6 kpackage6-native \
    libksysguard6 \
    ksystemstats6 \
"

#EXTRA_OECMAKE:append = " -Dksystemstats_EXE=/usr/bin/ksystemstats \"

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/kconf_update \
    ${datadir}/plasma-systemmonitor \
    ${datadir}/plasma \
    ${datadir}/knsrcfiles \
    ${datadir}/ksysguard \
    ${datadir}/kglobalaccel \
"

