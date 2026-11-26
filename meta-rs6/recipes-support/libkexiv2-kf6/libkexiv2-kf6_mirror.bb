SUMMARY = "KDE wrapper library for image metadata manipulation using Exiv2"
DESCRIPTION = "LibKExiv2 provides a Qt-based wrapper around the Exiv2 library \
for reading, writing and manipulating image metadata. It supports EXIF, IPTC \
and XMP metadata, including image orientation, timestamps, camera settings, \
geolocation and descriptive information. The library provides a consistent \
API for metadata management in KDE graphics and image processing applications."
HOMEPAGE = "https://invent.kde.org/graphics/libkexiv2"
BUGTRACKER = "https://bugs.kde.org/"
SECTION = "libs"

HOMEPAGE = "https://invent.kde.org/graphics/libkexiv2"
LICENSE = "BSD-3-Clause AND GPL-2.0-or-later"

inherit rs6

DEPENDS:append = " exiv2"
