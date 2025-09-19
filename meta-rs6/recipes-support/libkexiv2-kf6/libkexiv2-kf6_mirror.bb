# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2023 Justin Hammond <justin@dynam.ac>
# SPDX-FileCopyrightText: 2024 Marc Ferland <marc.ferland@sonatest.com>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Wrapper around Exiv2 library to manipulate picture metadata as EXIF and XMP"
DESCRIPTION = "Wrapper around Exiv2 library to manipulate picture metadata as EXIF and XMP"
SECTION = "libs"
HOMEPAGE = "https://invent.kde.org/graphics/libkexiv2"
LICENSE = "BSD-3-Clause & GPL-2.0-or-later"

inherit kde6-ecm

SRC_URI = "https://download.kde.org/stable/release-service/${PV}/src/libkexiv2-${PV}.tar.xz"
SRC_URI[sha256sum] = "9bb4e5858f35d0dba813ade3c0ff6e7fe750b0930d60a7f4609b48c9563d45eb"
S = "${WORKDIR}/libkexiv2-${PV}"

DEPENDS:append = " exiv2"

PACKAGECONFIG[testing] = "-DBUILD_TESTING=ON, -DBUILD_TESTING=OFF, "
