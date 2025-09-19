# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KItinerary"
SUMMARY = "Data Model and Extraction System for Travel Reservation information"
HOMEPAGE = "https://invent.kde.org/pim/kitinerary"
LICENSE = "LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.0-or-later.txt;md5=6d2d9952d88b50a51a5c73dc431d06c7"

inherit rs6

PACKAGECONFIG:append = " \
    kcongig \
    ki18n \
"

DEPENDS:append = " \
    kcontacts6 \
    kcalendarcore6 \
    kpkpass6 \
    kmime6 \
    \
    qtdeclarative6 \
    poppler \
    zxing-cpp-kf6 \
"
