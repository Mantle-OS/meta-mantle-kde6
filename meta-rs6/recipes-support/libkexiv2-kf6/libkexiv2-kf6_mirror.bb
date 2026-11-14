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
LICENSE = "BSD-3-Clause AND GPL-2.0-or-later"

inherit rs6

DEPENDS:append = " \
    exiv2 \
"
