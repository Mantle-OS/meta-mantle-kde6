# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Powerdevil"
HOMEPAGE = ""
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=5c213a7de3f013310bd272cdb6eb7a24"
PR = "r0"

inherit cmake_plasma

SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/powerdevil-6.4.4.tar.xz"
SRC_URI[sha256sum] = "caaf0b4dc6547c9490f499f46ba3cde750d8f4d393776a1fff652b1be9810028"

DEPENDS = " \
    plasma-activities6 \
    kidletime6 \
    kconfig6 \
    kdbusaddons6 \
    solid6 \
    kglobalaccel6 \
    kio6 \
    knotifyconfig6 \
    libkscreen6 \
    kwayland6 \
    kitemmodels6 \
    plasma-workspace6 \
    bluez-qt6 \
    ddcutil6 \
    qtwayland6-native \
"

FILES:${PN} += " \
    ${libdir}/plugins/plasma/kcms/systemsettings_qwidgets/* \
    ${libdir}/plugins/powerdevil/action/kcm/* \
    ${libdir}/qml/org/kde/plasma/private/* \
    ${datadir}/plasma/plasmoids/* \
"
