# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KRecorder"
HOMEPAGE = "https://invent.kde.org/plasma-mobile/krecorder"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-3.0-or-later.txt;md5=8da5784ab1c72e63ac74971f88658166"

inherit cmake_kdeapp
inherit kf6-kconfig kf6-kcoreaddons

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/krecorder-25.08.0.tar.xz"
SRC_URI[sha256sum] = "7c2838a0cb6171f273f98f2d0b9b9908163e426f957b8af947506be8d8e2107a"

DEPENDS = " \
    qtdeclarative6 \
    qtsvg6 \
    qtmultimedia6 \
    \
    kirigami6 kirigami6-addons6 \
    kwindowsystem6 \
"

