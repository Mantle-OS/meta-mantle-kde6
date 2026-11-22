SUMMARY = "Enhanced item view widgets for KDE and Qt applications"
DESCRIPTION = "KItemViews provides a collection of advanced item view \
widgets and utilities that extend Qt's Model/View framework. It includes \
categorized views, filtering interfaces, and customizable item delegates \
for displaying and interacting with structured data. The framework \
enables KDE and Qt applications to build consistent, feature-rich \
list and tree views."

HOMEPAGE = "https://api.kde.org/frameworks/kitemviews/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.0-or-later"
inherit kf6
BBCLASSEXTEND = "native nativesdk"
