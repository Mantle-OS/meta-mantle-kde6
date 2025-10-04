# SPDX-FileCopyrightText: 2019-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KoKo Gallery Application"
HOMEPAGE = "https://invent.kde.org/graphics/koko/-"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-only.txt;md5=fabba2a3bfeb22a6483d44e9ae824d3f"

inherit rs6

SRC_URI:append = " \
    git://invent.kde.org/packaging/geonames-data.git;prototol=https;name=geodata;nobranch=1;destsuffix=geonames-data \
"
SRCREV_geodata = "608af80ddf04265496eccd0c63b2945042cdccdc"

DEPENDS:append = " \
    qtdeclarative6 \
    qtpositioning6 \
    qtsvg6 \
    \
    kdeclarative6 \
    kconfigwidgets6 \
    kio6 \
    kguiaddons6 \
    knotifications6 \
    kfilemetadata6 \
    kdbusaddons6 \
    \
    libkexiv2-kf6 \
    kquickimageeditor6 \
    \
    libxcb \
    kirigami6 kirigami6-addons \
    purpose6 \
"

PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    kauth \
"

do_configure:prepend() {
    cp ${WORKDIR}/geonames-data/export/cities1000.zip ${S}/src
    cp ${WORKDIR}/geonames-data/export/admin1CodesASCII.txt ${S}/src
    cp ${WORKDIR}/geonames-data/export/admin2Codes.txt ${S}/src
}

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/koko \
    ${datadir}/knotifications6 \
"
