# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Kirigami-based RSS reader"
HOMEPAGE = "https://invent.kde.org/plasma-mobile/alligator"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-3.0-only.txt;md5=1c76c4cc354acaac30ed4d5eefea7245"

inherit cmake_kdeapp
inherit kf6-kconfig kf6-kcoreaddons

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/alligator-25.08.0.tar.xz"
SRC_URI[sha256sum] = "bb39c244f7e26d06d342515a8e69888fbe7a5ea3b7c224ff1f0a0c2718a32d8c"
DEPENDS += " \
    qtdeclarative6 \
    syndication6 \
    kirigami6 kirigami6-addons6 \
    ki18n6 \
    libplasma6 \
"

