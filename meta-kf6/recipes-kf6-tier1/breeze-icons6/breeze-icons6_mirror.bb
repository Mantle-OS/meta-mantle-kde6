SUMMARY = "Breeze icon theme for KDE Plasma and Qt applications"
DESCRIPTION = "Breeze Icons provides the default icon theme for KDE Plasma, \
offering a comprehensive collection of scalable vector and raster icons \
for applications, devices, actions, status indicators, and file types. \
It includes symbolic icons and light and dark theme variants designed \
to maintain visual consistency across KDE applications and desktop \
environments. The theme supports the FreeDesktop icon naming specification \
and integration with Qt and GTK applications."

HOMEPAGE = "https://api.kde.org/frameworks/breeze-icons/html/index.html"
SECTION = "x11/themes"

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1"

inherit kf6 gtk-icon-cache

SRC_URI:append = " \
    file://0001-add-cross-compiled-generate-symbolic-dark-cmake-opti.patch \
"

## FIXME add packageconfig for dark light etc

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
