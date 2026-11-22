SUMMARY = "Text completion framework and widgets for KDE and Qt applications"
DESCRIPTION = "KCompletion provides reusable text completion utilities and \
widgets for KDE and Qt applications. It supports automatic and interactive \
completion of user input, including matching, filtering, and suggestion \
generation. The framework includes configurable completion modes, \
completion history, and enhanced input widgets for implementing \
consistent autocompletion functionality in graphical applications."

HOMEPAGE = "https://api.kde.org/frameworks/kcompletion/html/index.html"
SECTION = "libs"

LICENSE = "BSD-3-Clause AND LGPL-2.0-or-later AND LGPL-2.1-or-later"

inherit kf6

DEPENDS:append = " \
    kwidgetsaddons6 \
    kcodecs6 \
"

PACKAGECONFIG:append = " kconfig"

BBCLASSEXTEND = "native nativesdk"
