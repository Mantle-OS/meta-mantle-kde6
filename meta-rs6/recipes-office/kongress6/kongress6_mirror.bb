# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Companion application for conferences"
HOMEPAGE = "https://invent.kde.org/utilities/kongress"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-3.0-or-later.txt;md5=1c76c4cc354acaac30ed4d5eefea7245"

inherit cmake_kdeapp
inherit kf6-kconfig

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/kongress-25.08.0.tar.xz"
SRC_URI[sha256sum] = "0b72a9b8b885f2730b74fb4e0bc9ce2812d0b92d1e6ea089d74c82db44922637"

DEPENDS = " \
    qtdeclarative6 \
    qtsvg6 \
    \
    kcalendarcore6 \
    kdbusaddons6 \
    kirigami6 kirigami6-addons6 \
    knotifications6 \
    kosmindoormap6 \
    kconfig6 \
"

