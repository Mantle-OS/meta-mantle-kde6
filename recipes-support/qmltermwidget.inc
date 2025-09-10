# SPDX-FileCopyrightText: 2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "QMLTermWidget"
HOMEPAGE = "https://github.com/Swordfish90/qmltermwidget"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4641e94ec96f98fabc56ff9cc48be14b"
PR = "r0"

DEPENDS = " \
    qtbase6 \
    qtdeclarative6 \
    qttools6 \
    qttools6-native \
    qt5compat6 \
"

SRC_URI = " \
    git://github.com/Swordfish90/qmltermwidget.git;nobranch=1;protocol=https \
    file://0001-Qt6-migration.patch \
    file://0002-Vt102Emulation-improve-QKeyPress-event-management.patch \
    file://0003-Remove-duplicate-files-from-install.patch \
"
S = "${WORKDIR}/git"

inherit qt6-qmake

FILES:${PN} += " \
    ${libdir}/qml/QMLTermWidget/* \
"
