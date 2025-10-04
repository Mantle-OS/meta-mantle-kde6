# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KItinerary"
SUMMARY = "Data Model and Extraction System for Travel Reservation information"
HOMEPAGE = "https://invent.kde.org/pim/kitinerary"
LICENSE = "LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.0-or-later.txt;md5=6d2d9952d88b50a51a5c73dc431d06c7"

inherit rs6

# OsmTools
DEPENDS:append = " \
    qtdeclarative6 \
    \
    kcontacts6 \
    kcalendarcore6 \
    \
    kpkpass6 \
    kmime6 \
    shared-mime-info shared-mime-info-native \    
    poppler \
    zxing-cpp-kf6 \
    zlib \
    libxml2 \
    libphonenumber-cpp \
    openssl \
    kcalendarcore6 \
"

PACKAGECONFIG[unity-cmake] = "-DUSE_UNITY_CMAKE_SUPPORT=OFF, -DUSE_UNITY_CMAKE_SUPPORT=OFF, "

PACKAGECONFIG:append = " \
    kconfig \
    ki18n \
"
