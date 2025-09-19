# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Public Transport Assistance for Mobile Devices"
HOMEPAGE = "https://invent.kde.org/utilities/ktrip"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"

inherit rs6

PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    ki18n \
"

DEPENDS:append = " \
    qqc2-desktop-style6 \
    qtdeclarative6 \
    kitemmodels6 \
    kcontacts6 \
    kirigami6 kirigami6-addons6 \
    kpublictransport6 \
"

RDEPENDS:${PN} += " \
    kirigami6-addons6 \
"
