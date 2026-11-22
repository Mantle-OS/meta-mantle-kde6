SUMMARY = "KDE Frameworks 6 item models for Qt Model/View"
DESCRIPTION = "KItemModels provides additional item models and proxy models \
for Qt's Model/View framework. It includes reusable components for sorting, \
filtering, selection, and transforming model data, helping applications \
implement complex data views without duplicating model logic."

HOMEPAGE = "https://api.kde.org/frameworks/kitemmodels/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.0-or-later AND (LGPL-2.1-only OR LGPL-3.0-only)"

inherit kf6

DEPENDS:append = " qtdeclarative6"
