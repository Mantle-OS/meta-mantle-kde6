SUMMARY = "System notification framework for KDE and Qt applications"
DESCRIPTION = "KNotifications provides a framework for delivering and \
managing application notifications in KDE and Qt applications. It supports \
desktop notifications, visual alerts, sound notifications, and persistent \
notification events through configurable notification backends. The \
framework integrates with D-Bus notification services and desktop \
environments, allowing applications to provide consistent, event-driven \
user notifications."

HOMEPAGE = "https://api.kde.org/frameworks/knotifications/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"

inherit kf6

DEPENDS:append = " \
    qtdeclarative6 \
    kwindowsystem6 \
    libcanberra \
"

PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "
PACKAGECONFIG[py-bindings] = "-DBUILD_PYTHON_BINDINGS=ON, -DBUILD_PYTHON_BINDINGS=OFF, \
    python3-native \
    python3-pyside6 \
    python3-shiboken6-native \
"

PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    dbus \
"

BBCLASSEXTEND = "native nativesdk"
