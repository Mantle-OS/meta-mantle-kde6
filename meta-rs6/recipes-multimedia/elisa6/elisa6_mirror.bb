# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
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
    baloo6 \
    kirigami6 kirigami6-addons6 \
    kdeclarative6 \
    kfilemetadata6 \
    kxmlgui6 \
    kconfigwidgets6 \
    kcrash6 \
    kdbusaddons6 \
    qqc2-desktop-style6 \
    \
    qtdeclarative6 \
    qtmultimedia6 \
"
