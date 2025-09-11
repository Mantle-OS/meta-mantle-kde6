# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

SUMMARY = "Gwenview - Image Viewer"
DESCRIPTION = "Gwenview is a fast and easy to use image viewer by KDE, ideal for browsing and displaying a collection of images."
HOMEPAGE = "https://apps.kde.org/de/gwenview"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=5a3169a2d39a757efd8b7aa66a69d97b"

inherit cmake_kdeapp
inherit kf6-kcoreaddons kf6-kconfig kf6-kauth

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/gwenview-25.08.0.tar.xz"
SRC_URI[sha256sum] = "5bf1bfccedef659f4df913adddd64a2c964d39b84cf1427104bb15dbaa86f08e"
DEPENDS += " \
    exiv2 \
    lcms \
    jpeg \
    baloo6 \
    kio6 \
    kitemmodels6 \
    ki18n6 \
    kparts6 \
    kwindowsystem6 \
    kiconthemes6 \
    kimageannotator6 \
    knotifications \
    kwidgetsaddons6 \
    plasma-activities6 \
    phonon \
    purpose6 \
    qtwayland6 qtwayland6-native \
"


EXTRA_OECMAKE += "-DJPEGLIB_RUN_RESULT=true"
EXTRA_OECMAKE += "-DJPEGLIB_RUN_RESULT__TRYRUN_OUTPUT=0"

FILES:${PN} += " \
    ${datadir}/solid/actions \
"
