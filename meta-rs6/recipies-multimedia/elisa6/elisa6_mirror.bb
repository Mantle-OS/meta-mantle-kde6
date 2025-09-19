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

inherit cmake_kdeapp
inherit kf6-kcoreaddons kf6-kconfig kf6-kauth

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/elisa-25.08.0.tar.xz"
SRC_URI[sha256sum] = "76029c54e927873039c3c282ada787a78f1d0d6dbc8fa9bd2e819aeec5df2d5c"

DEPENDS += " \
    baloo6 \
    kirigami6 kirigami6-addons6 \
    ki18n6 \
    kdeclarative6 \
    kfilemetadata6 \
    kxmlgui6 \
    kconfigwidgets6 \
    kcrash6 \
    kdbusaddons6 \
    qqc2-desktop-style6 \
    \
    qtbase6 \
    qtdeclarative6 \
    qtmultimedia6 \
"
