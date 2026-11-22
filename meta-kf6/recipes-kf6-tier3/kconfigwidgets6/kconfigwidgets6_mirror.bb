SUMMARY = "Configuration interface widgets for KDE and Qt applications"
DESCRIPTION = "KConfigWidgets provides reusable Qt Widgets components for \
creating and managing application configuration interfaces. It includes \
widgets for color and font selection, configuration dialogs, and \
standardized user interface actions. The framework integrates with \
KConfig to support persistent settings, automatic synchronization \
between configuration data and widgets, and consistent configuration \
interfaces across KDE applications."

HOMEPAGE = "https://api.kde.org/frameworks/kconfigwidgets/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.1-only"

inherit kf6

DEPENDS:append = " \
    kcodecs6 \
    kcolorscheme6 \
    kguiaddons6 \
    kwidgetsaddons6 \
"

PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "
PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    kauth \
    ki18n \
    dbus \
"

RDEPENDS:${PN}-dev += " \
    perl \
"

BBCLASSEXTEND = "native nativesdk"
