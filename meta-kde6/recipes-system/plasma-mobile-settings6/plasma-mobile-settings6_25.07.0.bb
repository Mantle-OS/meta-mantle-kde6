# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Plasma Mobile Settings"
HOMEPAGE = "https://invent.kde.org/plasma-mobile/plasma-settings"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881"

inherit plasma6

# Version v25.07.0
SRC_URI = "git://invent.kde.org/plasma-mobile/plasma-phone-settings.git;nobranch=1;protocol=https"
SRCREV = "e63400ddffa936a77132b746a6792618f788ea76"
S = "${WORKDIR}/git"

DEPENDS:append = " \
    kitemmodels6 \
    modemmanager-qt6 \
    networkmanager-qt6 \
    kirigami6-addons \
    libplasma6 \
"
