SUMMARY = "Core utility extensions for KDE and Qt applications"
DESCRIPTION = "KCoreAddons provides a collection of non-graphical utilities \
and extensions to QtCore for KDE and Qt applications. It includes \
functionality for application metadata, configuration management, \
filesystem operations, plugin discovery, process management, and job \
handling. The framework also provides system integration features, \
including device information, filesystem monitoring, and D-Bus support, \
along with QML bindings for use in Qt Quick applications."

HOMEPAGE = "https://api.kde.org/frameworks/kcoreaddons/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"
REUSE_LICENSECHECK_WHITELIST = "CC0-1.0"

inherit kf6

DEPENDS:append = " \
    util-linux \
"

DEPENDS:append:class-target = " \
    udev \
    ${PN}-native \
"
DEPENDS:append:class-nativesdk = " \
    ${PN}-native \
"

PACKAGECONFIG[pch] = "-DENABLE_PCH=ON, -DENABLE_PCH=OFF, "
PACKAGECONFIG[qml] = "-DKCOREADDONS_USE_QML=ON, -DKCOREADDONS_USE_QML=OFF, qtdeclarative6"
PACKAGECONFIG[inotify] = "-DENABLE_INOTIFY=ON, -DENABLE_INOTIFY=OFF, inotify-tools"
PACKAGECONFIG[py-bindings] = "-DBUILD_PYTHON_BINDINGS=ON, -DBUILD_PYTHON_BINDINGS=OFF, \
    python3-native \
    python3-pyside6 \
    python3-shiboken6-native \
"
PACKAGECONFIG[dbus] = "-DUSE_DBUS_DEFAULT=ON, -DUSE_DBUS_DEFAULT=OFF, dbus"

PACKAGECONFIG:append:class-target = " \
    inotify \
    pch \
    qml \
    dbus \
"

BBCLASSEXTEND = "native nativesdk"
