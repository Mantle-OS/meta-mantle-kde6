# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
# SPDX-FileCopyrightText: 2025 Joseph Mills <josephjamesmills@gmail.com>  
# SPDX-License-Identifier: MIT
SUMMARY = "An DAV protocol implementation with KJobs"
HOMEPAGE = "https://api.kde.org/frameworks/kdav/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.0-or-later"

inherit kf6

DEPENDS:append = " \
    kio6 \
"

PACKAGECONFIG:append = " \
    kcoreaddons \
    ki18n \
    kconfig \
"
