# SPDX-FileCopyrightText: 2022 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

SUMMARY = "KDE Plasma Bigscreen"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup features_check
require plasma-workspace-common.inc

RDEPENDS:${PN} += " \
    plasma-bigscreen6 \
    plasma-remotecontrollers6 \
    plank-player6 \
"

# TODO currently excluded because of high appstream dependency
# discover
# aura-browser
