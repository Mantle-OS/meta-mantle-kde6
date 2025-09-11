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

inherit cmake_kdeapp
inherit kf6-kconfig

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/kitinerary-25.08.0.tar.xz"
SRC_URI[sha256sum] = "635530c31d67fc35f5f5bcdf43f06d896e47b423d02df6546ef3c5fea1d212c6"
DEPENDS += " \
    qtbase6 \
    qtdeclarative6 \
    \
    kcontacts6 \
    kcalendarcore6 \
    ki18n6 \
    kpkpass6 \
    kmime6 \
    poppler \
    zxing-cpp-kf6 \
"


FILES:${PN} += " \
    ${datadir}/mime/packages/application-vnd-kde-itinerary.xml \
"
