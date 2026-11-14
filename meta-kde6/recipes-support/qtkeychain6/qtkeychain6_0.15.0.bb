# SPDX-FileCopyrightText: 2021-2022 Andreas Cord-Landwehr <cordlandwehr@kde.org>
# SPDX-License-Identifier: MIT
DESCRIPTION = "QtKeychain"
HOMEPAGE = "https://github.com/frankosterfeld/qtkeychain"
SECTION = "lib"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=ebece3896618ecf0b60c4fade17f026f"

inherit kde6-ecm

SRC_URI = " \
    git://github.com/frankosterfeld/qtkeychain.git;nobranch=1;protocol=https \
"
SRCREV = "ad7344c45a86a4f66cbafc4b081b5f7b876cb0b7"

PACKAGECONFIG[test-app] = "-DBUILD_TEST_APPLICATION=ON, -DBUILD_TEST_APPLICATION=OFF, "
PACKAGECONFIG[qt6] = "-DBUILD_WITH_QT6=ON, -DBUILD_WITH_QT6=OFF, "
PACKAGECONFIG[translations] = "-DBUILD_TRANSLATIONS=ON, -DBUILD_TRANSLATIONS=OFF,  "
PACKAGECONFIG[shared] = "-DBUILD_SHARED_LIBS=ON, -DBUILD_SHARED_LIBS=OFF  "
PACKAGECONFIG[libsecret] = "-DLIBSECRET_SUPPORT=ON, -DLIBSECRET_SUPPORT=OFF, libsecret"

PACKAGECONFIG:append = " \
    qt6 \
    shared \
    translations \
    libsecret \
"

FILES:${PN} += " \
    ${datadir}/qt6keychain \
"
