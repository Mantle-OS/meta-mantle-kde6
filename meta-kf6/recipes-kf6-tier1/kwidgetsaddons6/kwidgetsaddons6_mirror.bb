SUMMARY = "Additional Qt Widgets components for KDE and Qt applications"
DESCRIPTION = "KWidgetsAddons provides a collection of reusable widgets, \
dialogs, and user interface components that extend the functionality of \
Qt Widgets. It includes enhanced input controls, configuration dialogs, \
color selectors, notification widgets, and other utilities for building \
desktop applications. The framework helps KDE and Qt applications provide \
consistent and feature-rich graphical user interfaces."

HOMEPAGE = "https://api.kde.org/frameworks/kwidgetsaddons/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.0-or-later AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"

inherit kf6

EXTRA_OECMAKE:append:class-target = " -DBUILD_DESIGNERPLUGIN=OFF"
EXTRA_OECMAKE:append:class-native = " -DBUILD_DESIGNERPLUGIN=OFF"
EXTRA_OECMAKE:append:class-nativesdk = " -DBUILD_DESIGNERPLUGIN=ON "

PACKAGECONFIG[py-bindings] = "-DBUILD_PYTHON_BINDINGS=ON, -DBUILD_PYTHON_BINDINGS=OFF, \
    python3-native \
    python3-pyside6 \
    python3-shiboken6-native \
"

BBCLASSEXTEND = "native nativesdk"
