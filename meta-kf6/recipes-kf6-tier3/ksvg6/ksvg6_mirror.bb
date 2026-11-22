SUMMARY = "SVG rendering and theming framework for KDE and Qt Quick applications"
DESCRIPTION = "KSvg provides a framework for rendering scalable vector graphics \
in KDE and Qt Quick applications. It extends Qt SVG functionality with \
support for themed graphics, element-based rendering, caching, and \
dynamic color schemes. The framework integrates with KDE Plasma and \
Kirigami to provide consistent, scalable graphical elements across \
desktop, mobile, and embedded user interfaces."

HOMEPAGE = "https://api.kde.org/frameworks/ksvg/html/index.html"
SECTION = "libs"

LICENSE = "BSD-2-Clause AND LGPL-2.0-or-later"

inherit kf6

DEPENDS:append = " \
    qtdeclarative6 \
    qtsvg6 \
    karchive6 \
    kcolorscheme6 \
    kguiaddons6 \
    kirigami6 \
"

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
"

#FIXME  RDEPENDS NEEDS QTSVG HERE
