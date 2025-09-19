# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2020-2025 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

SUMMARY = "Marble"
DESCRIPTION = "Marble is a virtual globe and world atlas."
HOMEPAGE = "https://marble.kde.org/"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-only.txt;md5=41890f71f740302b785c27661123bff5"

inherit cmake_kdeapp
inherit kf6-kcoreaddons
inherit kf6-kconfig
inherit kf6-kauth

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/marble-25.08.0.tar.xz"
SRC_URI[sha256sum] = "6f5bf1cebc3311ce2439c4243f90ad249722edf12a96fb927ca60b83e98c7c9a"

DEPENDS += " \
    gpsd \
    kcrash6 \
    ki18n6 \
    kio6 \
    knewstuff6 \
    kparts6 \
    phonon \
    protobuf \
    protobuf-native \
    qtlocation6 \
    qtserialport6 \
"

EXTRA_OECMAKE += "-DBUILD_TOUCH=ON"

# note: marble-maps is the dedicated mobile version
PACKAGES += " \
    ${PN}-desktop \
    ${PN}-shared \
"

FILES_SOLIBSDEV = ""
FILES:${PN} = " \
    ${bindir}/marble-maps \
    ${datadir}/applications/org.kde.marble.maps.desktop \
"

INSANE_SKIP:${PN}-shared = "dev-so"
FILES:${PN}-shared = " \
    ${datadir}/knsrcfiles \
    ${libdir}/libmarbledeclarative.so \
    ${libdir} \
    ${datadir}/marble/data/ \
    ${datadir}/icons \
    ${datadir}/kxmlgui5 \
    ${datadir}/mime/packages/*.xml \
"

FILES:${PN}-desktop = " \
    ${bindir}/marble \
    ${bindir}/marble-behaim \
    ${datadir}/applications/org.kde.marble.desktop \
    ${datadir}/applications/org.kde.marble.behaim.desktop \
    ${datadir}/applications/marble_*.desktop \
"

FILES:${PN}-dev += " \
    ${datadir}/knsrcfiles \
    ${libdir}/libastro.so \
"

RDEPENDS:${PN} += " \
    ${PN}-shared \
"

RDEPENDS:${PN}-desktop += " \
    ${PN}-shared \
"
