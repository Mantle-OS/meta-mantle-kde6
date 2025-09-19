SUMMARY = "ddcutil"
DESCRIPTION = "ddcutil is a Linux program for querying and changing monitor settings, such as brightness and color levels."
HOMEPAGE = "https://github.com/rockowitz/ddcutil"
SECTION = "libs"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
PV = "2.2.1"

inherit autotools
inherit pkgconfig
inherit features_check

REQUIRED_DISTRO_FEATURES  = "x11 systemd"
SRC_URI = " \
    git://github.com/rockowitz/ddcutil.git;protocol=https;branch=master \
    file://0001-Fix-out-of-tree-build.patch \
"
SRCREV = "0b26bbec67b09b5ab594a06de8bbbdea621e8628"
S = "${WORKDIR}/git"

DEPENDS:append = " \
    i2c-tools \
    glib-2.0 \
    kmod \
    jansson \
"

CFLAGS += "-Wno-unused-but-set-variable"

EXTRA_OECONF += "--enable-build-timestamp=no"

PACKAGECONFIG ??= " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}  \
    drm \
"

PACKAGECONFIG[drm] = "--enable-drm=yes,--enable-drm=no,libdrm"
PACKAGECONFIG[systemd] = "--enable-udev=yes,--enable-udev=no,udev"
PACKAGECONFIG[usb] = "--enable-usb=yes,--enable-usb=no,libusb1"
PACKAGECONFIG[x11] = "--enable-x11=yes,--enable-x11=no,libx11 xrandr"

do_install:append () {
	install -d ${D}${sysconfdir}/udev/rules.d
	cp -rf ${D}${datadir}/ddcutil/data/* ${D}${sysconfdir}/udev/rules.d
}

FILES:${PN} += " \
    ${sysconfdir} ${libdir}/modules-load.d/ddcutil.conf \
"
