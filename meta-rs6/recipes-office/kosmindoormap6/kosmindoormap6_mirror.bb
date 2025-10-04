# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KOSMIndoorMap"
SUMMARY = "OSM multi-floor indoor map renderer"
HOMEPAGE = "https://invent.kde.org/libraries/kosmindoormap"
LICENSE = "LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.0-or-later.txt;md5=6d2d9952d88b50a51a5c73dc431d06c7"

inherit rs6

#OsmTools
DEPENDS:append = " \
    qtdeclarative6 \
    qtsvg6 \
    \
    kopeninghours6 \
    kpublictransport6 \
    kirigami6 kirigami6-addons \
    kcoreaddons6 \
    kservice6 \
    kweathercore6 \
    zlib \
    protobuf protobuf-native \
    flex-native bison-native \
    openssl \
"

PACKAGECONFIG[standalone] = "-DBUILD_STANDALONE_APP=ON, -DBUILD_STANDALONE_APP=OFF, "
PACKAGECONFIG[tools-only] = "-DBUILD_TOOLS_ONLY=ON, -DBUILD_TOOLS_ONLY=OFF, "

PACKAGECONFIG:append = " \
    ki18n \
    kconfig \
"
