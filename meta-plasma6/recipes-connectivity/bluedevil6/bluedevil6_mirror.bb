# SPDX-FileCopyrightText: 2018-2019 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Bluedevil"
HOMEPAGE = ""
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"

inherit plasma6

DEPENDS:append = " \
    qtdeclarative6 \
    \
    bluez-qt6 \
    kdbusaddons6 \
    kjobwidgets6 \
    kcmutils6 kcmutils6-native \
    kio6 \
    knotifications6 \
    kservice6 \
    ksvg6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
    libplasma6 \
    shared-mime-info shared-mime-info-native \
    kirigami6 \
"

FILES:${PN} += " \
    ${datadir}/bluedevilwizard \
    ${datadir}/metainfo \
    ${datadir}/remoteview \
    ${datadir}/knotifications6 \
    ${datadir}/plasma \
"
