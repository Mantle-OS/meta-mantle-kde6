# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Plasma Firewall"
SUMMARY = "Control Panel for your system firewall"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-firewall"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"

inherit plasma6

SRC_URI:append = " \
    file://0001-so-version-to-the-firewall-core-lib.patch \
"

DEPENDS:append = " \
    qtdeclarative6 \
    \
    kcmutils6 kcmutils6-native \
    python3-native \
"

PACKAGECONFIG[firewalld] = "-DBUILD_FIREWALLD_BACKEND=ON, -DBUILD_FIREWALLD_BACKEND=OFF, firewalld, firewalld"
PACKAGECONFIG[ufw] = "-DBUILD_UFW_BACKEND=ON, -DBUILD_UFW_BACKEND=OFF, ufw"
PACKAGECONFIG:append = " \
    firewalld \
"

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/applications \
"
