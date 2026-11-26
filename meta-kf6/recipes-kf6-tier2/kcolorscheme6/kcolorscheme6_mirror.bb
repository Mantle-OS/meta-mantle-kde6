SUMMARY = "Color scheme management framework for KDE and Qt applications"
DESCRIPTION = "KColorScheme provides a framework for accessing and managing \
application color schemes in KDE and Qt applications. It offers APIs for \
retrieving colors based on semantic roles, including text, backgrounds, \
selections, and interactive elements. The framework supports consistent \
visual styling across applications, integration with system color schemes, \
and automatic adaptation to light and dark themes."
HOMEPAGE = "https://api.kde.org/frameworks/kcolorscheme/html/index.html"
BUGTRACKER = "https://bugs.kde.org/"
SECTION = "libs"

LICENSE = "BSD-2-Clause AND LGPL-2.0-or-later AND LGPL-2.1-only AND LGPL-3.0-only"

inherit kf6

DEPENDS:append = " kguiaddons6"

PACKAGECONFIG:append = " \
    ki18n \
    kconfig \
"

BBCLASSEXTEND = "native nativesdk"
