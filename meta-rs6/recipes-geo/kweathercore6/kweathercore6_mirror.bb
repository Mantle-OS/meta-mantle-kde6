# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Library to facilitate retrieval of weather information including forecasts and alerts"
HOMEPAGE = "https://invent.kde.org/libraries/kweathercore"
LICENSE = "LGPL-2.0-or-later"
LIC_FILES_CHKSUM = " \
    file://LICENSES/LGPL-2.0-or-later.txt;md5=6d2d9952d88b50a51a5c73dc431d06c7 \
"

inherit rs6

PACKAGECONFIG:append = " \
    ki18n \
" 

DEPENDS:append = " \
    qtpositioning6 \
    qtdeclarative6 \
    \
    kholidays6 \
"
