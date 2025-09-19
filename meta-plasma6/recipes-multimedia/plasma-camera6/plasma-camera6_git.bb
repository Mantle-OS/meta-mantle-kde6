# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2019-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Plasma Camera"
HOMEPAGE = "https://invent.kde.org/plasma-mobile/plasma-camera"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-3.0-or-later.txt;md5=1c76c4cc354acaac30ed4d5eefea7245"
PR = "r0"

inherit kde6-cmake

SRC_URI = "git://invent.kde.org/plasma-mobile/${BPN};nobranch=1;protocol=https"
SRCREV = "73de8e63ed969595cc70ce88686e3458aa997da0"
S = "${WORKDIR}/git"

DEPENDS:apppend = " \
    kcoreaddons6 \
    kirigami6 \
    libplasma6 \
    kconfig6 kconfig6-native \
"

RDEPENDS:${PN} += "qtmultimedia6-qmlplugins"
