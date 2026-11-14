# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Plasma PA"
HOMEPAGE = ""
LICENSE = "BSD-3-Clause AND GPL-2.0-only"

inherit plasma6

#FIXME ?? CanberraPulse ??
DEPENDS:append = " \
    kdeclarative6 \
    kglobalaccel6 \
    kstatusnotifieritem6 \
    kcmutils6 kcmutils6-native\
    ksvg6 \
    kdbusaddons6 \
    kirigami6 kirigami6-addons \
    kitemmodels6 \
    \
    libplasma6 \
    pulseaudio-qt6 pulseaudio \
    libcanberra \
    sound-theme-freedesktop \
    \
    qtdeclarative6 \
"

RDEPENDS:${PN} += " \
    libcanberra-alsa \
    libcanberra-pulse \
    perl \
"
