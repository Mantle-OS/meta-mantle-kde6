# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Support for bookmarks and the XBEL format"
HOMEPAGE = "https://api.kde.org/frameworks/kbookmarks/html/index.html"
SECTIONS = "libs"
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
