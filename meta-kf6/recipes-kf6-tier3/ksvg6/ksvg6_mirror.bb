# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Plugin based UI runtime used to write primary user interfaces"
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
