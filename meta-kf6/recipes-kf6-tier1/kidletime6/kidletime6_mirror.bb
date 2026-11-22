SUMMARY = "User idle time detection framework for KDE and Qt applications"
DESCRIPTION = "KIdleTime provides a framework for detecting and monitoring \
user inactivity in KDE and Qt applications. It supports configurable \
idle time thresholds, activity notifications, and detection of user \
interaction through X11 and Wayland backends. The framework enables \
applications to implement power management, screen locking, and other \
idle-dependent functionality."
HOMEPAGE = "https://api.kde.org/frameworks/kidletime/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.1-or-later AND MIT"
REUSE_LICENSECHECK_WHITELIST = "GPL-2.0-or-later"

inherit kf6 features_check

ANY_OF_DISTRO_FEATURES = "kde_x11 wayland"


DEPENDS:append =  " bison"

## FIXME LOOK AT libxkbcommon seems off here for wayland
PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, \
    virtual/libx11 \
    libxcb libxext \
    libxscrnsaver \
    libxkbcommon \
"

PACKAGECONFIG[wayland] = "-DWITH_WAYLAND=ON, -DWITH_WAYLAND=OFF, \
    qtwayland6 \
    qtwayland6-native \
    plasma-wayland-protocols-kf6 \
"

PACKAGECONFIG:append = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'wayland', d)} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'kde_x11', d)} \
    qml \
"
