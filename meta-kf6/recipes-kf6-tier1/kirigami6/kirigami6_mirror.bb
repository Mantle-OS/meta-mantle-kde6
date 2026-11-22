SUMMARY = "Convergent Qt Quick application framework for KDE and Qt applications"
DESCRIPTION = "Kirigami provides a framework for building adaptive and \
responsive user interfaces using Qt Quick and QML. It offers reusable \
components for navigation, page layouts, drawers, toolbars, and interactive \
controls that adapt to different screen sizes and input methods. \
The framework enables developers to create applications that work across \
desktop, mobile, tablet, and embedded devices while maintaining a \
consistent user experience."

HOMEPAGE = "https://api.kde.org/frameworks/kirigami/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.0-or-later"
PR = "r0"

inherit kf6

DEPENDS:append = " \
    qtdeclarative6 \
    qtsvg6 \
    openmp-native \
"

RDEPENDS:${PN} += " \
    qtdeclarative6-qmlplugins \
"

PACKAGECONFIG[desktop] = "-DDESKTOP_ENABLED=ON, -DDESKTOP_ENABLED=OFF, "
PACKAGECONFIG[ubuntu-touch] = "-DUBUNTU_TOUCH=ON, -DUBUNTU_TOUCH=OFF, "
PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "

PACKAGECONFIG:append = " desktop dbus"
