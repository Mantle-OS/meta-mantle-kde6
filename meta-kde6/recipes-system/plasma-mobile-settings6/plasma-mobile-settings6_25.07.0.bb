# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Plasma Mobile Settings"
HOMEPAGE = "https://invent.kde.org/plasma-mobile/plasma-settings"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881"
PR = "r0"

inherit cmake_plasma
inherit kf6-kdoctools

# Version v25.07.0
SRC_URI = "git://commits.kde.org/${BPN};nobranch=1;protocol=https"
SRCREV = "e63400ddffa936a77132b746a6792618f788ea76"
S = "${WORKDIR}/git"

DEPENDS = " \
    kitemmodels6 \
    modemmanager-qt6 \
    networkmanager-qt6 \
    kirigami-addons6 \
    libplasma6 \
"

FILES:${PN} += " \
    ${libdir}/qt6/qml \
    ${datadir}/kpackage \
"
