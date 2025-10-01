# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Alistair Francis <alistair.francis@wdc.com>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "KSysGuard library"
HOMEPAGE = ""
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-only.txt;md5=147a320ed8b16b036829a0c71d424153"

inherit plasma6

DEPENDS:append = " \
    zlib \
    qtdeclarative6 \
    libnl \
    libpcap libcap \
    lmsensors \
    libdrm \    
    \
    kservice6 \
    kpackage6 kpackage6-native \
    knewstuff6 \
    solid6 \
"

PACKAGECONFIG[kuath-helper] = "-DENABLE_KAUTH_HELPER=ON, -DENABLE_KAUTH_HELPER=OFF, kauth kauth-native polkit-qt6-1"
PACKAGECONFIG:append = " \
    kauth-helper \
"

FILES:${PN} += " \
    ${datadir}/ksysguard \
    ${datadir}/knsrcfiles \
    ${datadir}/polkit-1 \
"
