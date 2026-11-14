# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "kImageAnnotator"
SUMMARY = "Tool for annotating images"
HOMEPAGE = "https://github.com/ksnip/kImageAnnotator"
LICENSE = "LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3000208d539ec061b899bce1d9ce9404"
PR = "r0"

inherit kde6-cmake features_check
REQUIRED_DISTRO_FEATURES = "x11"

SRC_URI = "git://github.com/ksnip/kImageAnnotator.git;nobranch=1;protocol=https"
SRCREV = "75a1f38700842aee3047728424df623a14644acb"

DEPENDS = " \
    kcolorpicker6 \
    qtsvg6 \
    virtual/libx11 \
"

EXTRA_OECMAKE += " \
    -DBUILD_WITH_QT6=ON \
    -DBUILD_EXAMPLE=OFF \
    -DCMAKE_POSITION_INDEPENDENT_CODE=ON \
"

FILES:${PN} += " \
    ${datadir}/kImageAnnotator \
"
