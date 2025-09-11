# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2021-2022 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "QCoro"
HOMEPAGE = "https://github.com/danvratil/qcoro"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSES/MIT.txt;md5=387248cdf922178ee3fdc379e28542dc"
PR = "r0"

inherit qt6-cmake

SRC_URI = "git://github.com/danvratil/qcoro.git;nobranch=1;protocol=https"
SRCREV = "ef1231ae490465b4f592ca326eee6d56a3d802ae"
S = "${WORKDIR}/git"

DEPENDS = " \
    qtbase6 qtdeclarative6 \
    qttools6 qttools6-native \
"

# FIXME POACKAGECONFIG
EXTRA_OECMAKE += " \
    -DQCORO_WITH_QTWEBSOCKETS=OFF \
    -DCMAKE_POSITION_INDEPENDENT_CODE=ON \
"
