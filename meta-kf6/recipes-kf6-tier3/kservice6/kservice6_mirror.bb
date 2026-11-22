SUMMARY = "Plugin and service discovery framework for KDE and Qt applications"
DESCRIPTION = "KService provides a framework for discovering, querying, \
and managing application services and plugins in KDE and Qt applications. \
It supports desktop entry files, service metadata, MIME type associations, \
and application discovery through standardized service definitions. \
The framework enables applications to locate compatible services, \
resolve preferred applications, and integrate extensible components \
into the KDE desktop environment."

HOMEPAGE = "https://api.kde.org/frameworks/kservice/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"

inherit kf6

DEPENDS:append = " kdbusaddons6"

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    ki18n \
    kdoctools \
"

BBCLASSEXTEND = "native nativesdk"
