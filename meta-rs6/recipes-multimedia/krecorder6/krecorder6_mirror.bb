# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KRecorder"
HOMEPAGE = "https://invent.kde.org/plasma-mobile/krecorder"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-3.0-or-later.txt;md5=8da5784ab1c72e63ac74971f88658166"

inherit rs6

PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
"

DEPENDS = " \
    qtdeclarative6 \
    qtsvg6 \
    qtmultimedia6 \
    \
    kirigami6 kirigami6-addons \
"

FILES:${PN} += " \
    ${datadir}/metainfo \
"
