# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Elisa"
DESCRIPTION = "Elisa music player"
HOMEPAGE = "https://apps.kde.org/de/elisa"
LICENSE = "LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-3.0-only.txt;md5=c51d3eef3be114124d11349ca0d7e117"

inherit rs6

PACKAGECONFIG:append = " \
    kcoreaddons \ 
    kconfig \
    kauth \
    ki18n \
"

DEPENDS:append = " \
    qtdeclarative6 \
    qtmultimedia6 \
    qtsvg6 \
    \
    kiconthemes6 \
    kfilemetadata6 \
    kxmlgui6 \
    kcolorscheme6 \
    kcodecs6 \
    kcrash6 \
    kdbusaddons6 \
    kirigami6 kirigami6-addons \
    kio6 \
    qqc2-desktop-style6 \
    \
    gupnp \        
    vlc \
"
#    baloo6 
#    kdeclarative6 
#    kconfigwidgets6 

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${libdir}/elisa \
"
