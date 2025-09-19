# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "PulseAudio Qt Bindings - libpulse bindings for Qt"
HOMEPAGE = "https://invent.kde.org/libraries/pulseaudio-qt"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-only.txt;md5=fabba2a3bfeb22a6483d44e9ae824d3f"
PR = "r0"

inherit kde6-ecm kde6-packaging

SRC_URI = "git://invent.kde.org/libraries/pulseaudio-qt;nobranch=1;protocol=https"
SRCREV = "cdde86de70e1ea540ead2577e4320d452dad94b9"
S = "${WORKDIR}/git"

DEPENDS:append = " \
    pulseaudio \
"

RDEPENDS:${PN} += " \
    pulseaudio-module-gsettings \
"

#     -DQT_MAJOR_VERSION=6
#     -DBUILD_WITH_QT6=ON
EXTRA_OECMAKE:append = " \
    -DBUILD_TESTING=OFF \
"
