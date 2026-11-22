SUMMARY = "Holiday calculation framework for KDE and Qt applications"
DESCRIPTION = "KHolidays provides a framework for calculating and querying \
public, religious, and regional holidays across different countries and \
cultures. It supports holiday definitions using rule-based calendar files, \
date calculations, and astronomical events. The framework provides APIs \
for integrating holiday information into KDE and Qt applications, \
including calendar, scheduling, and personal information management software."

HOMEPAGE = "https://api.kde.org/frameworks/kholidays/html/index.html"
SECTION = "libs"

LICENSE = "BSD-2-Clause AND LGPL-2.0-or-later"

inherit kf6

## DOC WHY AND ALSO RUNTIME.
DEPENDS:append = " qtdeclarative6"
