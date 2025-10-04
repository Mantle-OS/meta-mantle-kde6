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
# cfitsio KDcrawQt6
DEPENDS:append = " \
    qtsvg6 \
    qtmultimedia6 \
    qtwayland6 qtwayland6-native \
    \
    wayland-protocols \
    wayland wayland-native \
    \
    kio6 \
    kitemmodels6 \
    kparts6 \
    kwindowsystem6 \
    kiconthemes6 \
    knotifications6 \
    kguiaddons6 \
    kwidgetsaddons6 \
    kcrash6 \
    kdoctools6 kdoctools6-native \
    purpose6 \
    plasma-activities6 \
    \
    libjpeg-turbo \
    libpng \ 
    libkexiv2-kf6 \
    tiff \
    lcms \
    baloo6 \
"


PACKAGECONFIG[x11] = "-DWITHOUT_X11=OFF, -DWITHOUT_X11=ON, virtual/libx11"
PACKAGECONFIG[kimage-annotator] = "-DGWENVIEW_IMAGEANNOTATOR=ON, -DGWENVIEW_IMAGEANNOTATOR=OFF, kcolorpicker6 kimageannotator6"
PACKAGECONFIG:append = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
    kimage-annotator \    
"

EXTRA_OECMAKE:append = " \
    -DGWENVIEW_SEMANTICINFO_BACKEND=Baloo \
"

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/solid \
    ${datadir}/gwenview \
"
 
