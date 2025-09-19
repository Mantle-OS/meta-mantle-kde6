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

inherit rs6

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
    ki18n \
"

DEPENDS:append = " \
    gpsd \
    kcrash6 \
    kio6 \
    knewstuff6 \
    kparts6 \
    phonon \
    protobuf protobuf-native \
    qtlocation6 \
    qtserialport6 \
"

EXTRA_OECMAKE += "-DBUILD_TOUCH=ON"

INSANE_SKIP:${PN}-shared = "dev-so" 

RDEPENDS:${PN} += " \
    ${PN}-shared \
"
