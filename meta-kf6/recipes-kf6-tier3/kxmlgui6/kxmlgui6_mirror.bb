SUMMARY = "Configurable main window and GUI framework for KDE and Qt applications"
DESCRIPTION = "KXmlGui provides a framework for creating configurable \
application interfaces using XML-based GUI definitions. It supports \
customizable menus, toolbars, actions, and keyboard shortcuts, allowing \
applications to separate interface structure from implementation. \
The framework includes facilities for merging GUI components, managing \
user interface configurations, and integrating with KDE's action and \
shortcut management systems."

HOMEPAGE = "https://api.kde.org/frameworks/kxmlgui/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.1-only"

inherit kf6

DEPENDS:append = " \
    kitemviews6 \
    kconfigwidgets6 \
    kiconthemes6 \
    kguiaddons6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
    kglobalaccel6 \
"

PACKAGECONFIG[no-global-accel] = "-DFORCE_DISABLE_KGLOBALACCEL=ON, -DFORCE_DISABLE_KGLOBALACCEL=OFF, "
PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "
PACKAGECONFIG[py-bindings] = "-DBUILD_PYTHON_BINDINGS=ON, -DBUILD_PYTHON_BINDINGS=OFF,\
    python3-native \
    python3-pyside6 \
    python3-shiboken6-native \
"

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
    dbus \
    ki18n \
"
BBCLASSEXTEND = "native nativesdk"
