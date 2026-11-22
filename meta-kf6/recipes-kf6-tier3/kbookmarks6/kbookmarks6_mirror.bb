SUMMARY = "Bookmark management framework for KDE and Qt applications"
DESCRIPTION = "KBookmarks provides a framework for creating, organizing, \
and managing bookmarks in KDE and Qt applications. It supports the \
XML Bookmark Exchange Language (XBEL) format for storing and exchanging \
bookmark data, including hierarchical folders, titles, and URLs. \
The framework provides reusable bookmark menus, editing widgets, \
and integration with KDE application actions and configuration systems."

HOMEPAGE = "https://api.kde.org/frameworks/kbookmarks/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.0-only"

inherit kf6

DEPENDS:append = " \
    kcodecs6 \
    kconfigwidgets6 \
    kiconthemes6 \
    kwidgetsaddons6 \
    kxmlgui6 \
"

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
"

BBCLASSEXTEND = "native nativesdk"
