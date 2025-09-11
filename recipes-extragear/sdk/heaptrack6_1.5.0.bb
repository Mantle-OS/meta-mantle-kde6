# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Heaptrack"
HOMEPAGE = "https://invent.kde.org/sdk/heaptrack"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-only.txt;md5=41890f71f740302b785c27661123bff5"

inherit kde-cmake

SRC_URI = " \
    git://invent.kde.org/sdk/${BPN};nobranch=1;protocol=https \
    file://0001-cmake-Fix-C-compatibility-of-libunwind-probes.patch \
"
SRCREV = "b54e92e88b0895f910a5b107f499d1155a086b80"
S = "${WORKDIR}/git"

DEPENDS = " \
    zlib \
    boost \
    libunwind \
    elfutils \
"

EXTRA_OECMAKE += " -DHEAPTRACK_BUILD_GUI=OFF"
