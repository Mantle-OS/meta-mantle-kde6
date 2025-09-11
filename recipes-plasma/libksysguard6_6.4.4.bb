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
PR = "r0"
inherit cmake_plasma

SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/libksysguard-6.4.4.tar.xz"
SRC_URI[sha256sum] = "f12fd2e11d2d504d57556cb520a442ed9660f59c7d4b1fbbb17bee02eba0fb2f"
DEPENDS = " \
    libnl \
    libcap \
    libpcap \
    lmsensors \
    \
    kcompletion6 \
    kconfig6 \
    kconfigwidgets6 \
    kcoreaddons6 \
    kiconthemes6 \
    knewstuff6 \
    kservice6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
    networkmanager-qt6 \
    libplasma6 \
    \
    qtwebchannel6 \
"


FILES:${PN} += " \
    ${datadir}/ksysguard \
    ${libdir}/qml \
"
