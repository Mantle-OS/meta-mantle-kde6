SUMMARY = "D-Bus integration utilities for KDE and Qt applications"
DESCRIPTION = "KDBusAddons provides additional functionality for working \
with D-Bus in KDE and Qt applications. It extends QtDBus with utilities \
for managing application services, registering D-Bus objects, handling \
service ownership, and coordinating communication between processes. \
The framework simplifies interprocess communication and integration \
with D-Bus-based desktop and system services."

HOMEPAGE = "https://api.kde.org/frameworks/kdbusaddons/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.0-or-later AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"

inherit kf6 
inherit features_check

ANY_OF_DISTRO_FEATURES = "opengl wayland kde_x11"

# TODO  LOOK AT NEW CODE
PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, virtual/libx11"
PACKAGECONFIG:append:class-target = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'kde_x11', 'x11', '', d)} \
"

BBCLASSEXTEND = "native nativesdk"
