SUMMARY = "Online content discovery and installation framework for KDE and Qt applications"
DESCRIPTION = "KNewStuff provides a framework for discovering, downloading, \
installing, and managing community-contributed content in KDE and Qt \
applications. It supports resources such as themes, wallpapers, plugins, \
templates, and other application assets through online content providers. \
The framework integrates with the Open Collaboration Services API and \
provides reusable Qt Widgets and QML components for browsing, rating, \
updating, and managing downloadable content."

HOMEPAGE = "https://api.kde.org/frameworks/knewstuff/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.1-only"
REUSE_LICENSECHECK_WHITELIST = "CC0-1.0"

inherit kf6


## FIXME THIS WAS NEVER VALIDATED

DEPENDS:append = " \
    qtdeclarative6 \
    \
    karchive6 \
    kpackage6 \
    kpackage6-native \
    kwidgetsaddons6 \
    attica6 \
    kirigami6 \
    syndication6 \
"

PACKAGECONFIG:append = " \
    kauth \
    kcoreaddons \
    kconfig  \
    ki18n \
"

EXTRA_OECMAKE:append = " -DBUILD_DESIGNERPLUGIN=OFF"
