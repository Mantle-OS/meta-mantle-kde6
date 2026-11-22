SUMMARY = "Parallel query and search framework for KDE and Qt applications"
DESCRIPTION = "KRunner provides a framework for implementing and executing \
search queries across multiple data sources and application services. \
It supports asynchronous query processing, parallel result collection, \
relevance matching, and extensible search plugins known as runners. \
The framework enables KDE and Qt applications to provide application \
launching, command execution, file searching, and contextual actions \
through a unified search interface."

HOMEPAGE = "https://api.kde.org/frameworks/krunner/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.0-or-later AND LGPL-2.1-only"

inherit kf6

DEPENDS:append = " \
    kitemmodels6 \
    kwindowsystem6 \
"

PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    ki18n \
"
