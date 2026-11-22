SUMMARY = "Embeddable application component framework for KDE and Qt applications"
DESCRIPTION = "KParts provides a framework for creating and embedding \
reusable application components within KDE and Qt applications. It \
enables applications to integrate document viewers, editors, and other \
interactive components through a standardized plugin architecture. \
The framework supports component lifecycle management, shared actions, \
menus, toolbars, and integration with KDE's XML-based GUI system."

HOMEPAGE = "https://api.kde.org/frameworks/kparts/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.0-or-later AND LGPL-2.1-or-later"

inherit kf6

DEPENDS:append = " \
    kio6 \
    kjobwidgets6 \
    kservice6 \
    kwidgetsaddons6 \
    kxmlgui6 \
"

PACKAGECONFIG:append = " \
    kauth \
    kcoreaddons \
    kconfig \
    ki18n  \
"
