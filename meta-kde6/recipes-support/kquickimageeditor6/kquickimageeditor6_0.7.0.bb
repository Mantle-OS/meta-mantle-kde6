DESCRIPTION = "KQuickImageEditor is a set of QtQuick components providing basic image editing capabilities."
HOMEPAGE = "https://invent.kde.org/libraries/kquickimageeditor/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68"
PR = "r0"

inherit kde6-ecm

SRC_URI = "git://invent.kde.org/libraries/kquickimageeditor;branch=master;protocol=https"
SRCREV = "ac7568044defc89c494b0d6edb60073cb0ad8b25"

DEPENDS:append = " \
    qtshadertools6 qtshadertools6-native \
    qtdeclarative6 \
"

# find_package(Qt6 ${REQUIRED_QT_VERSION} COMPONENTS Core Qml Quick Concurrent ShaderTools REQUIRED)
# find_package(KF6 ${REQUIRED_KF_VERSION} REQUIRED Config)
# find_package(HWY ${REQUIRED_HWY_VERSION} REQUIRED) ?????


# keep an eye on qt6_add_shaders(KQuickImageEditor "shaders"



# future
#PACKAGECONFIG[opencv] = " , , opencv"
#PACKAGECONFIG:append =  " ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'opencv', '', d)}"
