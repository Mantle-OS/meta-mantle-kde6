
# SPDX-FileCopyrightText: 2020-2025 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Marble"
DESCRIPTION = "Marble is a virtual globe and world atlas."
HOMEPAGE = "https://marble.kde.org/"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-only.txt;md5=41890f71f740302b785c27661123bff5"

inherit rs6

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
    ki18n \
"

DEPENDS:append = " \
    qtsvg6 \
    qtdeclarative6 \
    qt5compat6 \
    qtwebengine6 \
    qtwebchannel6 \
    qtpositioning6 \
    qtlocation6 \
    qtserialport6 \
    \
    kcrash6 \
    kparts6 \
    kio6 \
    krunner6 \
    libplasma6 \
    kdoctools6 kdoctools6-native \
    \
    zlib \
    protobuf protobuf-native \
    gpsd \
    gpsd-machine-conf \
    \
    knewstuff6 \
    phonon-kf6 \
"

PACKAGECONFIG[mobile] = "-DMOBILE=ON, -DMOBILE=OFF,  "
PACKAGECONFIG[mobile] = "-DBUILD_TOUCH=ON, -DBUILD_TOUCH=OFF,  "
PACKAGECONFIG[qtandkde] = "-DBUILD_QT_AND_KDE=ON, -DBUILD_QT_AND_KDE=OFF, "
PACKAGECONFIG[dbus] = "-DBUILD_WITH_DBUS=ON, -DBUILD_WITH_DBUS=OFF, "
PACKAGECONFIG[apps] = "-DBUILD_MARBLE_APPS=ON, -DBUILD_MARBLE_APPS=OFF, "
PACKAGECONFIG[marble-tools] = "-DBUILD_MARBLE_TOOLS=ON, -DBUILD_MARBLE_TOOLS=OFF, "
PACKAGECONFIG[marble-examples] = "-DBUILD_MARBLE_EXAMPLES=ON, -DBUILD_MARBLE_EXAMPLES=OFF, "

PACKAGECONFIG:append = " \
    qtandkde \
    dbus \
    apps \
"

FILES:${PN} += " \
    ${datadir}/marble \
    ${datadir}/plasma \
    ${datadir}/metainfo \
    ${datadir}/config.kcfg \
    ${datadir}/kxmlgui6 \
    ${prefix}/mkspecs \
"

EXTRA_OECMAKE += " \
    -DMARBLE_PLUGIN_PATH=${QT6_INSTALL_PLUGINSDIR}/marble \
"

FILES:${PN}-plugins += " \
    ${libdir}/marble \
    ${libdir}/plugins \
    ${libdir}/qt6/plugins \
"
