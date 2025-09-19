# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "KoKo Gallery Application"
HOMEPAGE = "https://invent.kde.org/libraries/kquickimageeditor/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68"
PR = "r0"

inherit kde6-cmake

SRC_URI = "git://invent.kde.org/libraries/kquickimageeditor;nobranch=1;protocol=https"
SRCREV = "673816f603550a9e7e62c5dadf077926d5de6857"
S = "${WORKDIR}/git"

DEPENDS:append = " \
    qtdeclarative6 \
"
