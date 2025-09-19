# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Companion application for conferences"
HOMEPAGE = "https://invent.kde.org/utilities/kongress"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-3.0-or-later.txt;md5=1c76c4cc354acaac30ed4d5eefea7245"

inherit rs6

PACKAGECONFIG:append = " \
    kconfig \
"

DEPENDS:append = " \
    qtdeclarative6 \
    qtsvg6 \
    \
    kcalendarcore6 \
    kdbusaddons6 \
    kirigami6 kirigami6-addons6 \
    knotifications6 \
    kosmindoormap6 \
"
