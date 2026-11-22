SUMMARY = "Advanced embeddable text editor framework for KDE and Qt applications"
DESCRIPTION = "KTextEditor provides a powerful and extensible text editing \
framework for KDE and Qt applications. It includes syntax highlighting, \
code folding, automatic indentation, search and replace, and configurable \
editing modes. The framework supports scripting, plugins, session \
management, and integration with language servers for code intelligence. \
It provides the editing component used by Kate and KWrite, enabling \
applications to embed advanced text editing functionality."

HOMEPAGE = "https://api.kde.org/frameworks/ktexteditor/html/index.html"
SECTION = "libs"
LICENSE = "BSD-2-Clause AND LGPL-2.0-only AND MIT"

inherit kf6 features_check

REQUIRED_DISTRO_FEATURES = "polkit"

DEPENDS:append = " \
    qtdeclarative6 \
    qtspeech6 \
    \
    karchive6 \
    kguiaddons6 \
    kio6 \
    kparts6 \
    sonnet6 \
    syntax-highlighting6 \
    kcolorscheme6 \
"
PACKAGECONFIG[pch] = "-DENABLE_PCH=ON, -DENABLE_PCH=OFF"
PACKAGECONFIG[enable-kauth] = "-DENABLE_KAUTH=ON, -DENABLE_KAUTH=OFF, "

PACKAGECONFIG:append = " \
    kcoreaddons \
    kauth \
    enable-kauth \
    kconfig \
    ki18n \
"

FILES:${PN} += " \
    ${datadir}/polkit-1 \
"

FILES:${PN}-docs += " \
    ${datadir}/katepart5/script/README.md \
"
