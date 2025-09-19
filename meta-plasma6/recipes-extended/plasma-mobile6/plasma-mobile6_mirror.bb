# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2019 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020-2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Plasma shell for mobile devices"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-mobile"
LICENSE = "(LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL) & (GPL-2.0-only | GPL-3.0-only | LicenseRef-KDE-Accepted-GPL)"
LIC_FILES_CHKSUM = " \
    file://LICENSES/GPL-2.0-only.txt;md5=9e2385fe012386d34dcc5c9863070881 \
    file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881 \
    file://LICENSES/GPL-3.0-only.txt;md5=49fc03046e56a282c0c743b5d3a55b7c \
    file://LICENSES/LGPL-2.1-only.txt;md5=147a320ed8b16b036829a0c71d424153 \
    file://LICENSES/LGPL-3.0-only.txt;md5=1a523aee720780ffc0705c5bf17a6421 \
    file://LICENSES/LicenseRef-KDE-Accepted-GPL.txt;md5=b4c280013bbbadfbe92219498dc5228c \
"
PR = "r0"

inherit plasma6

DEPENDS += " \
    kdbusaddons6 \
    kglobalaccel6 \
    kio6 \
    kirigami6-addons6 \
    kjobwidgets6 \
    knotifications6 \
    kpackage6 \
    kwayland6 \
    libplasma6 \
    modemmanager-qt6 \
    networkmanager-qt6 \
    plasma-workspace6 \
    qcoro6 \
    qtwayland6 qtwayland6-native \
"

RDEPENDS:${PN} += " \
    breeze6 \
    kded6 \
    kirigami6 \
    kirigami6-addons6 \
    plasma-nano6 \
    qqc2-breeze6-style6 \
    kde6-cli-tools-kde6 \
"

do_compile:append() {
    sed -i -e 's,${S}/components/mobileshellstate/,,g' ${B}/components/mobileshellstate/mobileadaptor.h
}
