SUMMARY = "MIME message processing framework for KDE and Qt applications"
DESCRIPTION = "KMime provides a framework for parsing, constructing, \
modifying and serializing MIME messages, including email and Usenet \
messages. It supports message headers, multipart message structures, \
attachments, character encoding and content transfer encoding for \
standards-compliant message processing."

HOMEPAGE = "https://invent.kde.org/frameworks/kmime"
BUGTRACKER = "https://bugs.kde.org/"
SECTION = "libs"

LICENSE = "BSD-3-Clause AND LGPL-2.0-only AND MIT"

inherit kf6

DEPENDS:append = " \
    kcodecs6 \
"
