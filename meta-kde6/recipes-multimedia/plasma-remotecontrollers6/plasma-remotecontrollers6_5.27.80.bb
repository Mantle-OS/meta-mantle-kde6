# SPDX-FileCopyrightText: 2022 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Plasma Remotecontrollers"
HOMEPAGE = "https://invent.kde.org/plasma-bigscreen/plasma-remotecontrollers"
LICENSE = "GPL-2.0-only | GPL-3.0-only | LicenseRef-KDE-Accepted-GPL"

inherit plasma6

SRC_URI = "git://invent.kde.org/plasma-bigscreen/plasma-remotecontrollers;nobranch=1;protocol=https"
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
