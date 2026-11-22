SUMMARY = "Contact aggregation and management framework for KDE and Qt applications"
DESCRIPTION = "KPeople provides a framework for aggregating and accessing \
contact information from multiple data sources in KDE and Qt applications. \
It offers a unified interface for identifying people, linking contact \
records, and retrieving associated information across different services. \
The framework supports extensible contact data backends, Qt Widgets, \
and QML integration, enabling applications to display and interact \
with consolidated contact information."
HOMEPAGE = "https://api.kde.org/frameworks/kpeople/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.1-or-later"

inherit kf6

DEPENDS:append = " \
    qtdeclarative6 \
    kcontacts6 \
    kwidgetsaddons6 \
    kitemviews6 \
"

PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "
PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    ki18n \
    dbus \
"
