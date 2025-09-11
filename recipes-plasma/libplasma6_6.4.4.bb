# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0

require ${PN}.inc
SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/libplasma-6.4.4.tar.xz"
SRC_URI[sha256sum] = "ab0f4b426829821e0abf479052f326a5ce01c3b604e890c9f79949c3eb5fdebe"

# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020-2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

SUMMARY = "Plasma library and runtime components"
HOMEPAGE = "https://invent.kde.org/plasma/libplasma"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-only.txt;md5=fabba2a3bfeb22a6483d44e9ae824d3f"
PR = "r0"

DEPENDS = " \
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

inherit cmake_plasma
inherit kf6-ki18n
inherit kf6-kdoctools

FILES:${PN} += " \
    ${libdir}/plugins/*.so \
    ${libdir}/plugins/kf6/kirigami/platform/KirigamiPlasmaStyle.so \
    ${libdir}/plugins/kpackage/packagestructure/*.so \
    ${libdir}/plugins/plasma/scriptengines/*.so \
    ${libdir}/plugins/kf6/packagestructure/* \
    ${libdir}/qml/org/kde/plasma/accessdenied/* \
    ${libdir}/qml/org/kde/plasma/*  \
    ${libdir}/qml/org/kde/styles/Plasma/*  \
    ${libdir}/qml/org/kde/kirigami/* \
    ${libdir}/qml/QtQuick/Controls.2/Plasma \
    ${libdir}/qml/QtQuick/Controls/Styles/Plasma \
    ${datadir}/plasma/services/*.operations \
    ${datadir}/plasma/desktoptheme/air/* \
    ${datadir}/plasma/desktoptheme/air/*/* \
    ${datadir}/plasma/desktoptheme/breeze-dark/* \
    ${datadir}/plasma/desktoptheme/breeze-dark/*/* \
    ${datadir}/plasma/desktoptheme/breeze-light/* \
    ${datadir}/plasma/desktoptheme/breeze-light/*/* \
    ${datadir}/plasma/desktoptheme/default/* \
    ${datadir}/plasma/desktoptheme/default/*/* \
    ${datadir}/plasma/desktoptheme/oxygen/* \
    ${datadir}/plasma/desktoptheme/oxygen/*/* \
"
