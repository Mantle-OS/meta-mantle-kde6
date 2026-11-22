SUMMARY = "Status notifier item framework for KDE and Qt applications"
DESCRIPTION = "KStatusNotifierItem provides a framework for integrating \
application status indicators with system trays and desktop notification \
areas. It implements the StatusNotifierItem specification, allowing \
applications to expose status icons, context menus, tooltips, and \
interactive actions through D-Bus. The framework supports integration \
with KDE Plasma and other compatible desktop environments, providing \
a standardized alternative to legacy system tray interfaces."

HOMEPAGE = "https://api.kde.org/frameworks/kstatusnotifieritem/html/index.html"
SECTION = "libs"

LICENSE = "CC0-1.0 AND LGPL-2.0-or-later"

inherit kf6

DEPENDS:append = " \
    kwindowsystem6 \
"

PACKAGECONFIG[x11] = "-DWITHOUT_X11=OFF, -DWITHOUT_X11=ON, virtual/libx11"
PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF,"
PACKAGECONFIG[py-bindings] = "-DBUILD_PYTHON_BINDINGS=ON, -DBUILD_PYTHON_BINDINGS=OFF, \
    python3-native \
    python3-pyside6  \
    python3-shiboken6-native \
"

PACKAGECONFIG:append = " \
    dbus \
"

# TODO  NEW CODE CHECK
PACKAGECONFIG:append:class-target = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'kde_x11', 'x11', '', d)} \
"
