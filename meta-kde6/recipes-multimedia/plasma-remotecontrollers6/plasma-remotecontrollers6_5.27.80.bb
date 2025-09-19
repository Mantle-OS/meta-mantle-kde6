# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2022 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Plasma Remotecontrollers"
HOMEPAGE = "https://invent.kde.org/plasma-bigscreen/plasma-remotecontrollers"
LICENSE = "GPL-2.0-only | GPL-3.0-only | LicenseRef-KDE-Accepted-GPL"
LIC_FILES_CHKSUM = " \
    file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977 \
    file://LICENSES/GPL-3.0-only.txt;md5=1c76c4cc354acaac30ed4d5eefea7245 \
    file://LICENSES/LicenseRef-KDE-Accepted-GPL.txt;md5=b4c280013bbbadfbe92219498dc5228c \
"

inherit plasma6

SRC_URI = "git://invent.kde.org/plasma-bigscreen/${BPN};nobranch=1;protocol=https"
SRCREV = "16d347453db2d03f158994b5c1dd2de0eea15772"
S = "${WORKDIR}/git"

PACKAGECONFIG:append = " \
    kcoreaddons \
"

DEPENDS:append = " \
    kirigami6 \
    solid6 \
    kdeclarative6 \
    knotifications6 \
    kpackage6 \
    kwindowsystem6 \
    plasma-workspace6 \
    qtwayland6 qtwayland6-native \
"
