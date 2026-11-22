SUMMARY = "Global keyboard shortcut framework for KDE and Qt applications"
DESCRIPTION = "KGlobalAccel provides a framework for registering and managing \
global keyboard shortcuts across KDE and Qt applications. It enables \
applications to define shortcuts that remain active independently of \
application focus, supporting system-wide actions and desktop integration. \
The framework provides shortcut configuration, conflict detection, and \
integration with the underlying windowing system."

HOMEPAGE = "https://api.kde.org/frameworks/kglobalaccel/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.0-or-later"

inherit kf6

## TODO LOOK AT NEW CODE
PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, "
PACKAGECONFIG:append:class-target = " ${@bb.utils.filter('DISTRO_FEATURES', 'kde_x11', d)}"

BBCLASSEXTEND = "native nativesdk"
