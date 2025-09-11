# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2019 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Bluedevil"
HOMEPAGE = ""
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"


inherit cmake_plasma
SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/bluedevil-6.4.4.tar.xz"
SRC_URI[sha256sum] = "7e5d386a7c42a1b4ef00e79469c8f47300944c9c42023647f2efd3a12ecc3175"
DEPENDS = " \
    bluez-qt6 \
    kcoreaddons6 \
    kdbusaddons6 \
    kded6 \
    kiconthemes6 \
    kio6 \
    knotifications \
    ksvg6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
    libplasma6 \
    qtdeclarative6 \
    shared-mime-info \
    shared-mime-info-native \
"



FILES:${PN} += " \
    ${datadir}/bluedevilwizard \
    ${datadir}/kpackage/kcms/ \
    ${datadir}/mime/packages \
    ${datadir}/plasma \
    ${datadir}/remoteview \
    ${libdir}/qml \
    ${libdir}/plugins/plasma/kcms/systemsettings/ \
"
