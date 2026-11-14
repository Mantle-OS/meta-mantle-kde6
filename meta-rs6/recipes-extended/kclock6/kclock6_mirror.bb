# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Clock app for Plasma Mobile"
HOMEPAGE = ""
LICENSE = "GPL-2.0-only OR GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=4cc91856b08b094b4f406a29dc61db21"

inherit rs6

PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    kauth \
    ki18n \
"

DEPENDS:append = " \
    qtdeclarative6 \
    qtmultimedia6 \
    qtsvg6 \
    \
    kirigami6 kirigami6-addons \
    knotifications6 \
    kdbusaddons6 \
    kcrash6 \
    kstatusnotifieritem6 \
    ksvg6 \
"

PACKAGECONFIG[plasmiod] = "-DBUILD_PLASMOID=ON, -DBUILD_PLASMOID=OFF, kio6 libplasma6 kjobwidgets6"
PACKAGECONFIG:append = " \
    plasmiod \
"

FILES:${PN} += " \
    ${datadir}/krunner \
    ${datadir}/metainfo \
    ${datadir}/knotifications6 \
    ${datadir}/plasma \
"
