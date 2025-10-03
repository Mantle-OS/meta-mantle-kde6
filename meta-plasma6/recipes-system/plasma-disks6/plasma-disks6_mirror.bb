# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Plasma Disks"
SUMMARY = "Monitors S.M.A.R.T. capable devices for imminent failure."
HOMEPAGE = "https://invent.kde.org/plasma/plasma-disks"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"

inherit plasma6

DEPENDS:append = " \
    kdbusaddons6 \
    knotifications6 \
    solid6 \
    kservice6 \
    kio6 \
    kcmutils6 kcmutils6-native \
    smartmontools \
"

PACKAGECONFIG[simulation] = "-DWITH_SIMULATION=ON, -DWITH_SIMULATION=OFF, "

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/knotifications6 \
    ${datadir}/polkit-1 \
" 
