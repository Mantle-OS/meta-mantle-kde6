# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2019 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Breeze Style"
HOMEPAGE = ""
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"

inherit plasma6

DEPENDS:append = " \
    fftw \
    kconfigwidgets6 \
    kcoreaddons6 \
    kdecoration6 \
    kguiaddons6 \
    kirigami6 \
    kpackage6 \
    kwayland6 \
    kwindowsystem6 \
    frameworkintegration6 \
"

EXTRA_OECMAKE += " \
    -DBUILD_QT5:BOOL=OFF \
"

RDEPENDS:${PN} += " \
    breeze6-icons \
"
