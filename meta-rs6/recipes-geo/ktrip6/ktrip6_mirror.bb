# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Public Transport Assistance for Mobile Devices"
HOMEPAGE = "https://invent.kde.org/utilities/ktrip"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"

inherit cmake_kdeapp
inherit kf6-kconfig kf6-kcoreaddons

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/ktrip-25.08.0.tar.xz"
SRC_URI[sha256sum] = "bfcadd7101809c0ae371e0050485c669ad1636440dd22fc352db895a949460b7"

DEPENDS = " \
    qqc2-desktop-style6 \
    qtdeclarative6 \
    ki18n6 \
    kitemmodels6 \
    kcontacts6 \
    kirigami6 \
    kirigami6-addons6 \
    kpublictransport6 \
"


RDEPENDS:${PN} += " \
    kirigami6-addons6 \
"
