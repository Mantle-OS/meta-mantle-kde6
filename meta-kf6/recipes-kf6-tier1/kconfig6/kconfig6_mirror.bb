SUMMARY = "Configuration management framework for KDE and Qt applications"
DESCRIPTION = "KConfig provides a framework for managing application \
settings and configuration data in KDE and Qt applications. It supports \
reading and writing configuration files, hierarchical configuration \
groups, default values, and configuration change notifications. \
The framework includes tools for generating type-safe configuration \
classes, integration with Qt Widgets and QML, and support for \
D-Bus-based configuration notifications."

HOMEPAGE = "https://api.kde.org/frameworks/kconfig/html/index.html"
SECTION = "libs"

LICENSE = "BSD-2-Clause AND BSD-3-Clause AND LGPL-2.0-or-later AND MIT AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"

inherit kde6-ecm kf6-checksums

SRC_URI = "${KDE6_MIRROR}/stable/frameworks/${KDE6_KF6_VERSION_DIR}/kconfig-${KDE6_KF6_VERSION}.tar.xz;name=${BPN}"
S = "${UNPACKDIR}/kconfig-${KDE6_KF6_VERSION}"

DEPENDS:append:class-target = " ${PN}-native"
DEPENDS:append:class-nativesdk = " ${PN}-native"

PACKAGECONFIG[gui] = "-DKCONFIG_USE_GUI=ON, -DKCONFIG_USE_GUI=OFF,  " 
PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "
PACKAGECONFIG[qml] = "-DKCONFIG_USE_QML=ON, -DKCONFIG_USE_QML=OFF, qtdeclarative6"

PACKAGECONFIG  = " gui dbus qml"

## TODO LOOK AT
#PACKAGECONFIG:append:class-native = " gui dbus qml"

BBCLASSEXTEND = "native nativesdk"
