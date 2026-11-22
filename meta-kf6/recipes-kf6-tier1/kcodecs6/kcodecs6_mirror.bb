SUMMARY = "Text encoding and decoding utilities for KDE and Qt applications"
DESCRIPTION = "KCodecs provides a collection of utilities for encoding \
and decoding text and binary data in KDE and Qt applications. It supports \
common encoding formats including Base64, quoted-printable, hexadecimal, \
and various text transformation algorithms. The framework provides \
reusable APIs for processing encoded content in applications, network \
protocols, and data interchange formats."

HOMEPAGE = "https://api.kde.org/frameworks/kcodecs/html/index.html"
SECTION = "libs"
LICENSE = "BSD-3-Clause AND LGPL-2.0-only AND LGPL-2.0-or-later AND MIT"

inherit kf6

DEPENDS:append = " gperf-native"
BBCLASSEXTEND = "native nativesdk"
