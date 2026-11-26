SUMMARY = "QML integration framework for KDE and Qt Quick applications"
DESCRIPTION = "KDeclarative provides integration between KDE Frameworks and \
Qt Quick applications through reusable QML components and utilities. \
It enables access to KDE services, configuration systems, and desktop \
functionality from QML, including application actions, context menus, \
and graphical components. The framework simplifies the development \
of KDE applications using Qt Quick while maintaining consistent \
integration with the KDE Plasma desktop environment."
HOMEPAGE = "https://api.kde.org/frameworks/kdeclarative/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.0-or-later AND LGPL-2.1-or-later AND MIT"

inherit kf6

DEPENDS:append = " \
    qtdeclarative6 \
    \
    kglobalaccel6 \
    kguiaddons6 \
    kwidgetsaddons6 \
    \
    kiconthemes6 \
    kio6 \
    knotifications6 \
    kpackage6 \
    kwindowsystem6 \
"

DEPENDS:class-target += " \
    kpackage6-native \
"

PACKAGECONFIG:append = " \
    kauth \
    kconfig \
    ki18n \
"

RDEPENDS:${PN} += " \
    qtdeclarative6-qmlplugins \
"
