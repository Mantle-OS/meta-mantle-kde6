# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2022 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "libphonenumber"
HOMEPAGE = "https://github.com/googlei18n/libphonenumber"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=363822a4d0f7a74c43ecdc2a4c11ae64"
PR = "r0"

inherit kde6-cmake

SRC_URI = " \
    git://github.com/googlei18n/${BPN}.git;nobranch=1;protocol=https \
"
SRCREV = "a4a46ef2eae8d7e9abf3eddee949ffca49548678"
S = "${WORKDIR}/git/cpp"

#FIXME Java ......
DEPENDS:append = " \
    gtest \
    protobuf protobuf-native \
    icu \
    virtual/javac-native \
    abseil-cpp \
"


EXTRA_OECMAKE += "-DUSE_BOOST=OFF -DBUILD_GEOCODER=OFF -DCMAKE_CXX_STANDARD=17"
OECMAKE_GENERATOR = "Unix Makefiles"
