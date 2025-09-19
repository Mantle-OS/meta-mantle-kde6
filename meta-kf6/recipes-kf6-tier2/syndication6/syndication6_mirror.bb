# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "An RSS/Atom parser library"
HOMEPAGE = "https://api.kde.org/frameworks/syndication/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.0-or-later"

inherit kf6

DEPENDS:append = " kcodecs6"
