# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2025 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "QML Box2D plugin"
HOMEPAGE = "https://github.com/qml-box2d/qml-box2d"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=7c1ecaedec0d10ec77d289e71b42165f"
PR = "r0"

inherit kde6-cmake

SRC_URI = " \
    git://github.com/qml-box2d/qml-box2d.git;nobranch=1;protocol=https \
"
SRCREV = "3a85439726d1ac4d082308feba45f23859ba71e0"
S = "${WORKDIR}/git"

DEPENDS = " \
    qtbase6 \
    qtdeclarative6 qtdeclarative6-native \
"
