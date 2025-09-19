# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "The KDE calendar access library"
HOMEPAGE = "https://api.kde.org/frameworks/kcalendarcore/html/index.html"
SECTION = "libs"
LICENSE = "BSD-3-Clause & LGPL-2.0-or-later"

inherit kf6
REUSE_LICENSECHECK_WHITELIST = "LGPL-3.0-or-later"

DEPENDS:append = " \
    libical \
"
