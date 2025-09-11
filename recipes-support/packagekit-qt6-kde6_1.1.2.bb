# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Marc Ferland <marc.ferland@sonatest.com>
#
# SPDX-License-Identifier: MIT
SUMMARY = "PackageKit-Qt"
DESCRIPTION = "Qt bindings for PackageKit"
HOMEPAGE = "https://github.com/PackageKit/PackageKit-Qt"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"
PR = "r0"

inherit qt6-cmake xmlcatalog

SRC_URI = "git://github.com/PackageKit/PackageKit-Qt.git;protocol=https;branch=main"
SRCREV = "c888e1c7be5f8a7868c7728ab18d878376fb61b8"
S="${WORKDIR}/git"

DEPENDS = " \
    qtbase6 \
    packagekit6 \
"

RDEPENDS:${PN} += "qtbase6"
EXTRA_OECMAKE += " \
    -DBUILD_WITH_QT6=ON \
"
