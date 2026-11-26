SUMMARY = "KDE multimedia abstraction library"
DESCRIPTION = "Phonon provides a Qt based multimedia abstraction layer \
for audio and video playback, device management and multimedia backend \
integration. This recipe builds the Qt6 variant of Phonon."
HOMEPAGE = "https://invent.kde.org/libraries/phonon"
BUGTRACKER = "https://bugs.kde.org/"
SECTION = "libs"

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1"


inherit kde6-ecm features_check
REQUIRED_DISTRO_FEATURES = "pulseaudio"

## WARNING ATTIC
SRC_URI = "https://download.kde.org/Attic/phonon/${PV}/phonon-${PV}.tar.xz"
SRC_URI[sha256sum] = "3287ffe0fbcc2d4aa1363f9e15747302d0b080090fe76e5f211d809ecb43f39a"
S = "${UNPACKDIR}/phonon-${PV}"

DEPENDS:append = " \
    qt5compat6 \
    pulseaudio \
    glib-2.0 \
    qttools6 \
"

EXTRA_OECMAKE:append = " -DPHONON_BUILD_QT5=OFF"

#RRECOMMENDS:${PN} += " phonon-backend-vlc-kf6"

PACKAGECONFIG[experimental] = "-DPHONON_BUILD_EXPERIMENTAL=ON, -DPHONON_BUILD_EXPERIMENTAL=OFF, "
PACKAGECONFIG[demos] = "-DPHONON_BUILD_DEMOS=ON, -DPHONON_BUILD_DEMOS=OFF, "
PACKAGECONFIG[designer] = "-DPHONON_BUILD_DESIGNER_PLUGIN=ON, -DPHONON_BUILD_DESIGNER_PLUGIN=OFF, qttools6-native"
PACKAGECONFIG[docs] = "-DPHONON_BUILD_DOC=ON, -DPHONON_BUILD_DOC=OFF, qttools6-native"
PACKAGECONFIG[gui] = "-DPHONON_BUILD_SETTINGS=ON, -DPHONON_BUILD_SETTINGS=OFF, "
#PACKAGECONFIG[qt5] = "-DPHONON_BUILD_QT5=ON, -DPHONON_BUILD_QT5=OFF, qtbase"
PACKAGECONFIG[qt6] = "-DPHONON_BUILD_QT6=ON, -DPHONON_BUILD_QT6=OFF, "

PACKAGECONFIG:append = " \
    qt6 \
    gui \
    experimental \
"
