SUMMARY = "Plasma Wayland Protocols"
HOMEPAGE = "https://community.kde.org/Plasma/Wayland"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1"

PV = "1.23.0"
PR = "r0"  

inherit kde6-ecm features_check

REQUIRED_DISTRO_FEATURES = "wayland"

SRC_URI = "https://download.kde.org/stable/plasma-wayland-protocols/plasma-wayland-protocols-1.23.0.tar.xz"
SRC_URI[sha256sum] = "16c5ad917bde2ed795942dacba76654819ddc6a1566842325ef34e0b553ef138"
S = "${UNPACKDIR}/plasma-wayland-protocols-1.23.0"

DEPENDS:append = " \
   qtwayland6-native \
   qtdeclarative6 \
"

PACKAGECONFIG[testing] = "-DBUILD_TESTING=ON, -DBUILD_TESTING=OFF, qttools6-native"

FILES:${PN} += " \
    ${libdir}/cmake \
    ${datadir}/plasma-wayland-protocols/* \
"

BBCLASSEXTEND = "native nativesdk"
