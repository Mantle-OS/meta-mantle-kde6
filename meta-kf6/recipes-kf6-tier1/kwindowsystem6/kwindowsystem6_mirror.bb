SUMMARY = "Window system integration framework for KDE and Qt applications"
DESCRIPTION = "KWindowSystem provides an abstraction layer for interacting \
with window management systems in KDE and Qt applications. It supports \
window identification, activation, stacking, virtual desktops, and window \
state management through X11 and Wayland backends. The framework enables \
applications to interact with the underlying windowing system using \
platform-specific interfaces."

HOMEPAGE = "https://api.kde.org/frameworks/kwindowsystem/html/index.html"
SECTION = "libs"

LICENSE = "BSD-3-Clause AND MIT AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"

inherit kf6 features_check
ANY_OF_DISTRO_FEATURES = "kde_x11 wayland"

PACKAGECONFIG[qml] = "-DKWINDOWSYSTEM_QML=ON, -DKWINDOWSYSTEM_QML=OFF, qtdeclarative6"
PACKAGECONFIG[x11] = "-DKWINDOWSYSTEM_X11=ON, -DKWINDOWSYSTEM_X11=OFF, \
    virtual/libx11 \
    libxcb \
    xcb-util \
    xcb-util-keysyms \
    libxfixes \
    xcb-util-wm \
"

PACKAGECONFIG[wayland] = "-DKWINDOWSYSTEM_WAYLAND=ON, -DKWINDOWSYSTEM_WAYLAND=OFF, \
    wayland \
    wayland-protocols \
    qtwayland6 \
    qtwayland6-native \
    plasma-wayland-protocols-kf6 \
"

PACKAGECONFIG:append:class-target = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'wayland', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'kde_x11', 'x11', '',  d)} \
    qml \
"

BBCLASSEXTEND = "native nativesdk"
