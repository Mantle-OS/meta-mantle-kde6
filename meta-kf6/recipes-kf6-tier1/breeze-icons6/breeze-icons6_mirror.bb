# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Breeze icon theme"
HOMEPAGE = "https://api.kde.org/frameworks/breeze-icons/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1"

inherit kf6 gtk-icon-cache
SRC_URI:append = " \
    file://0001-add-cross-compiled-generate-symbolic-dark-cmake-opti.patch \
"

RDEPENDS:${PN} += " bash"

DEPENDS:append:class-target = " \
    ${PN}-native \
    python3-native \
    python3-lxml-native \
"

DEPENDS:append:class-nativesdk = " ${PN}-native"

EXTRA_OECMAKE:append:class-target = " \
    -DGEN_SYM_DARK_EXE=${STAGING_BINDIR_NATIVE}/generate-symbolic-dark \
"

EXTRA_OECMAKE:append:class-nativesdk = " \
    -DGEN_SYM_DARK_EXE=${STAGING_BINDIR_NATIVE}/generate-symbolic-dark \
"

EXTRA_OECMAKE:append:class-native = " \
    -DSKIP_INSTALL_ICONS=ON \
    -DWITH_ICON_GENERATION=OFF \
"

do_install:append:class-native() {
    install -d ${D}/${bindir}
    install -m 0755 ${B}/bin/qrcAlias ${D}/${bindir}
    install -m 0755 ${B}/bin/generate-symbolic-dark ${D}${bindir}
}

BBCLASSEXTEND = "native nativesdk"
