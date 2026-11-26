SUMMARY = "Job progress tracking widgets for KDE and Qt applications"
DESCRIPTION = "KJobWidgets provides graphical components for monitoring \
and displaying the progress of asynchronous operations managed by KJob. \
It supports progress dialogs, status indicators, cancellation controls, \
and integration with desktop notification systems. The framework enables \
KDE and Qt applications to present consistent feedback for background \
tasks such as file transfers, downloads, and other long-running operations."
BUGTRACKER = "https://bugs.kde.org/"
HOMEPAGE = "https://api.kde.org/frameworks/kjobwidgets/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.0-only"
REUSE_LICENSECHECK_WHITELIST = "LicenseRef-KDE-Accepted-GPL LGPL-3.0"

inherit kf6 

DEPENDS:append = " \
    knotifications6 \
    kwidgetsaddons6 \
"

## TODO LOOK AT THE NEW CODE
PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, virtual/libx11"
PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "
PACKAGECONFIG[py-bindings] = "-DBUILD_PYTHON_BINDINGS=ON, -DBUILD_PYTHON_BINDINGS=OFF, \
    python3-native \
    python3-pyside6 \
    python3-shiboken6-native \
"

PACKAGECONFIG:append = " \
    kcoreaddons \
    dbus \
"

PACKAGECONFIG:append:class-target = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'kde_x11', 'x11', '', d)} \
"

BBCLASSEXTEND = "native nativesdk"
