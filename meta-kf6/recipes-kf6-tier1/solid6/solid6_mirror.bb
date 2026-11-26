SUMMARY = "Hardware integration and detection"
DESCRIPTION = "Solid is a KDE Frameworks library providing a unified \
API for hardware discovery, device enumeration and hardware property \
access. It abstracts platform-specific backends for storage devices, \
network interfaces, batteries and other system hardware."

HOMEPAGE = "https://api.kde.org/frameworks/solid/html/index.html"
SECTION = "libs"

LICENSE = "BSD-3-Clause AND LGPL-2.1-or-later AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"

inherit kf6

DEPENDS:append = " \
    flex-native \
    bison-native \
    util-linux \
"

DEPENDS:append:class-target = " upower"

PACKAGECONFIG[udev] = "-DUDEV_DISABLED=OFF, -DUDEV_DISABLED=ON, udev"
PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "

PACKAGECONFIG:append = " dbus"
PACKAGECONFIG:append:class-target = " udev"

RRECOMMENDS:${PN}:append:class-target = " \
    udisks2 \
    upower \
"

BBCLASSEXTEND = "native nativesdk"
