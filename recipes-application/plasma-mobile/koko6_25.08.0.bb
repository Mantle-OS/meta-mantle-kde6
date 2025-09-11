# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2019-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KoKo Gallery Application"
HOMEPAGE = "https://invent.kde.org/graphics/koko/-"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-only.txt;md5=fabba2a3bfeb22a6483d44e9ae824d3f"

inherit cmake_kdeapp
inherit kf6-kconfig kf6-kcoreaddons kf6-kauth

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/koko-25.08.0.tar.xz"
SRC_URI[sha256sum] = "4fdb14b1d03e74d65634eef47925fd21216bb4019d189232a9831fabbcdf4fe5"

DEPENDS = " \
    qtlocation6 \
    \
    kdeclarative6 \
    kiconthemes6 \
    kio6 \
    kirigami6 kirigami6-addons6 \
    kfilemetadata6 \
    knotifications6 \
    kquickimageeditor6 \
    exiv2-kf6 \
    purpose6 \
"

RDEPENDS:${PN} += " \
    qtdeclarative6-qmlplugins \
    purpose6 \
    kirigami6 \
    kirigami6-addons6 \
    kquickimageeditor6 \
"

KOKO_CITIES1000="cities1000.zip"
KOKO_ADMIN1CODES="admin1CodesASCII.txt"
KOKO_ADMIN2CODES="admin2Codes.txt"
SRC_URI:append = " git://invent.kde.org/packaging/geonames-data.git;prototol=https;name=geodata;nobranch=1;destsuffix=geonames-data;rev=608af80ddf04265496eccd0c63b2945042cdccdc"

# To build Koko, it is required to import a few files from outside
# this repository. These files are licensed under the CC-BY-4.0 and
# are maintained by the GeoNames project. These files need to be
# copied to the src directory. CMake will take care of the rest.
do_configure:prepend() {
    # compatibility workaround for Yocto versions older than Styhead
    [ -z ${UNPACKDIR+x} ] && UNPACKDIR=${WORKDIR}

    cp ${UNPACKDIR}/geonames-data/export/${KOKO_CITIES1000} ${S}/src
    cp ${UNPACKDIR}/geonames-data/export/${KOKO_ADMIN1CODES} ${S}/src
    cp ${UNPACKDIR}/geonames-data/export/${KOKO_ADMIN2CODES} ${S}/src
}
