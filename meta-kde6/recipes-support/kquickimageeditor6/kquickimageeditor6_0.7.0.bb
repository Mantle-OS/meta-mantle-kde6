DESCRIPTION = "KQuickImageEditor is a set of QtQuick components providing basic image editing capabilities."
HOMEPAGE = "https://invent.kde.org/libraries/kquickimageeditor/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68"
PR = "r0"

inherit kde6-ecm features_check
REQUIRED_DISTRO_FEATURE = "opengl"

SRC_URI = "git://invent.kde.org/libraries/kquickimageeditor;branch=master;protocol=https"
SRCREV = "262177e8ec026a7af7338578647290fe9de7c084"

DEPENDS:append = " \
    kconfig6 kconfig6-native \
    qtshadertools6 qtshadertools6-native \
    qtdeclarative6 \
    highway \
"
