SUMMARY = "Text template engine for KDE and Qt applications"
DESCRIPTION = "KTextTemplate provides a flexible template processing engine \
for generating text-based documents and structured output in KDE and Qt \
applications. It separates document structure from application data using \
templates with variables, filters, control structures, and reusable \
components. The framework supports customizable output generation for \
reports, HTML, configuration files, and other text-based formats."

HOMEPAGE = "https://api.kde.org/frameworks/ktexttemplate/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.1-or-later"

inherit kf6

DEPENDS:append = " qtbase6 qtdeclarative6"
