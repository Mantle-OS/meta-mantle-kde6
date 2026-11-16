SUMMARY = "Kde6 Monitoring user activity"
SECTION = "libs"
HOMEPAGE = "https://api.kde.org/frameworks/kidletime/html/index.html"
LICENSE = "LGPL-2.1-or-later AND MIT"

inherit kf6
REUSE_LICENSECHECK_WHITELIST = "GPL-2.0-or-later"


DEPENDS:append =  " \
    bison \
"

PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, \
    virtual/libx11 libxcb libxext libxscrnsaver libxkbcommon \
"
PACKAGECONFIG[wayland] = "-DWITH_WAYLAND=ON, -DWITH_WAYLAND=OFF, \
    qtwayland6 qtwayland6-native plasma-wayland-protocols-kf6"

# {@bb.utils.filter('DISTRO_FEATURES', 'x11', d)}
PACKAGECONFIG:append = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'wayland', d)} \
    qml \
"
