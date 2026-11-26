SUMMARY = "RSS and Atom feed parsing library for KDE and Qt applications"
DESCRIPTION = "Syndication provides a framework for parsing and processing \
RSS and Atom web feeds in KDE and Qt applications. It supports multiple \
RSS versions and the Atom syndication format, providing a unified API \
for accessing feed metadata, articles, authors, publication dates, \
and associated content. The library simplifies feed aggregation and \
integration into news readers and other content syndication applications."
BUGTRACKER = "https://bugs.kde.org/"
HOMEPAGE = "https://api.kde.org/frameworks/syndication/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.0-or-later"

inherit kf6

DEPENDS:append = " kcodecs6"
