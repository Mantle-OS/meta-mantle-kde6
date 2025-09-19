# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Plugin based library to create window decorations"
HOMEPAGE = ""
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-only.txt;md5=fabba2a3bfeb22a6483d44e9ae824d3f"
PR = "r0"

inherit cmake_plasma

SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/kdecoration-6.4.4.tar.xz"
SRC_URI[sha256sum] = "a02ac71ea8ee7ad6ab34578f1b50f32b91347a05a932cfc50b253729ccadee6f"
FILES:${PN} += " \
    ${datadir}/locale \
"
