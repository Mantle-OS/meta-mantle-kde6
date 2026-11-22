SUMMARY = "Configuration module utilities for KDE and Qt applications"
DESCRIPTION = "KCMUtils provides a framework for creating, loading, and \
managing KDE Control Modules (KCMs) used to configure applications \
and desktop environments. It supports modular configuration interfaces, \
dynamic module discovery, and integration with Qt Widgets and QML. \
The framework provides reusable components for embedding configuration \
modules in applications and system settings interfaces."

HOMEPAGE = "https://api.kde.org/frameworks/kcmutils/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.0-only"

inherit kf6

DEPENDS:append:class-target = " \
    libxml2 \
    libxml2-native \
    qtdeclarative6 \
    \
    kio6 \
    kitemviews6 \
    kconfigwidgets6 \
    kguiaddons6 \
    kxmlgui6 \
    kwidgetsaddons6 \
"

PACKAGECONFIG[tools-only] = "-DTOOLS_ONLY=ON, -DTOOLS_ONLY=OFF, "
PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    dbus \
"

PACKAGECONFIG:class-native = "tools-only"
BBCLASSEXTEND = "native nativesdk"
