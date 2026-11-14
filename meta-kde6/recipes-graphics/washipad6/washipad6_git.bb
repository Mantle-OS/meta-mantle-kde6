# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Minimalist Sketchnoting Application"
HOMEPAGE = ""
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-3.0-only.txt;md5=49fc03046e56a282c0c743b5d3a55b7c"
PR = "r0"

inherit kde6-ecm

SRC_URI = "git://invent.kde.org/graphics/washipad;nobranch=1;protocol=https"
SRCREV = "ab68812b70703fdc357eb05d2cce43669d74f7cb"
S = "${UNPACKDIR}/git"

DEPENDS = " \
    kirigami6 \
    ki18n6-native ki18n6 \
    kcoreaddons6 \
    kcrash6 \
    \
    qtdeclarative6 \
    qtsvg6 \
"

RDEPENDS:${PN} += " \
    qtdeclarative6-qmlplugins \
"

FILES:${PN} += " \
    ${datadir}/metainfo \
"
