# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "KDE Holiday calculation library"
HOMEPAGE = "https://api.kde.org/frameworks/kholidays/html/index.html"
SECTION = "libs"
LICENSE = "BSD-2-Clause AND LGPL-2.0-or-later"

inherit kf6

DEPENDS:append = " qtdeclarative6"
