# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Companion application for conferences"
HOMEPAGE = "https://invent.kde.org/utilities/kongress"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-3.0-or-later.txt;md5=1c76c4cc354acaac30ed4d5eefea7245"

inherit rs6

DEPENDS:append = " \
    qtdeclarative6 \
    qtsvg6 \
    \
    kirigami6 kirigami6-addons \
    kcalendarcore6 \
    kosmindoormap6 \
    kcrash6 \
    \ 
    kdbusaddons6 \
    knotifications6 \
"

PACKAGECONFIG:append = " \
    kconfig \
    ki18n \
"

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/knotifications6 \
"
