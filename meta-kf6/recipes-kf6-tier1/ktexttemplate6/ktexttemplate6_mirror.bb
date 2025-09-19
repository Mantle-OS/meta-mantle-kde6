# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Library to allow application developers to separate the structure of documents from the data they contain."
HOMEPAGE = "https://api.kde.org/frameworks/ktexttemplate/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.1-or-later"

inherit kf6

DEPENDS += " \
    qtbase6 \
    qtdeclarative6 \
"


## examples neeeds sql
