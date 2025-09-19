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

inherit rs6

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig  \
    kauth \
    ki18n \
"

DEPENDS:append = " \
    exiv2 \
    lcms \
    jpeg \
    phonon \
    \
    baloo6 \
    kio6 \
    kitemmodels6 \
    kparts6 \
    kwindowsystem6 \
    kiconthemes6 \
    kimageannotator6 \
    knotifications6 \
    kwidgetsaddons6 \
    plasma-activities6 \
    purpose6 \
    \    
    qtwayland6 qtwayland6-native \
"


EXTRA_OECMAKE += "-DJPEGLIB_RUN_RESULT=true"
EXTRA_OECMAKE += "-DJPEGLIB_RUN_RESULT__TRYRUN_OUTPUT=0"
