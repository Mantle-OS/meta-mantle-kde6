# SPDX-FileCopyrightText: 2025 Joseph Mills <josepjamesmills@gmail.com>
# 
# SPDX-License-Identifier: MIT
DESCRIPTION = "libphonenumber-tools"
HOMEPAGE = "https://github.com/googlei18n/libphonenumber"
SECTION = "libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/cpp/LICENSE;md5=363822a4d0f7a74c43ecdc2a4c11ae64"

inherit kde6-cmake native

SRC_URI = " \
    git://github.com/google/libphonenumber.git;branch=master;protocol=https \
    file://0001-add-absl-for-yocto-build.patch \
"

SRCREV = "543e633d35865a3cdfa4ae7bd20ba4a0f6bb7a0d"
S = "${UNPACKDIR}/${BP}/tools/cpp"

DEPENDS:append = " \
    abseil-cpp-native \
"

PACKAGECONFIG ??= ""
PACKAGECONFIG[testing] = "-DBUILD_TESTING=ON, -DBUILD_TESTING=OFF, gtest"
