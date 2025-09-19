# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
SUMMARY = "Blinken"
DESCRIPTION = "Memory Enhancement Game"
HOMEPAGE = "https://edu.kde.org/blinken/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881"
PR = "r0"

inherit rs6

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
    ki18n \
"

DEPENDS:append = " \
    kcrash6 \
    kio6 \
    phonon \
"
