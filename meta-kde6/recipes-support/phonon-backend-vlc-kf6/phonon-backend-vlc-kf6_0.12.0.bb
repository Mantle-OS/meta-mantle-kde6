DESCRIPTION = "Phonon VLC backend"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=46d98c0b7542668d6015b992889b2020"

PV = "0.12.0"
PR = "r0"

inherit kde6-ecm

# Attic

SRC_URI = "https://download.kde.org/Attic/phonon/phonon-backend-vlc/0.12.0/phonon-backend-vlc-0.12.0.tar.xz"
SRC_URI[sha256sum] = "338479dc451e4b94b3ca5b578def741dcf82f5c626a2807d36235be2dce7c9a5"
S = "${UNPACKDIR}/phonon-backend-vlc-${PV}"

DEPENDS:append = " \
    phonon-kf6 \
    qttools6-native \
    vlc \
"

PACKAGECONFIG[qt5] = "-DPHONON_BUILD_QT5=ON, -DPHONON_BUILD_QT5=OFF, "
PACKAGECONFIG[qt6] = "-DPHONON_BUILD_QT6=ON, -DPHONON_BUILD_QT6=OFF, "
PACKAGECONFIG:append = " qt6"

RDEPENDS:${PN} += "vlc"
