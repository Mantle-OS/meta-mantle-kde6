LICENSE = "LGPL-2.1-only"
SECTION = "icons"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1"

PR = "r0"

inherit kde6-ecm gtk-icon-cache

SRC_URI = "https://download.kde.org/stable/oxygen-icons/oxygen-icons-6.2.0.tar.xz"
SRC_URI[sha256sum] = "61fd2ef56e7afbbbab034052017264ad00d074c7be06f0855d4c805a5cbacfdd"
S = "${UNPACKDIR}/oxygen-icons-6.2.0"

DEPENDS:append = " \
    hicolor-icon-theme \
"
