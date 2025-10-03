# SPDX-FileCopyrightText: 2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KDE Connect"
HOMEPAGE = "https://community.kde.org/KDEConnect"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=fed54355545ffd980b814dab4a3b312c"

inherit rs6

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \  
    kcmutils \
"

DEPENDS:append = " \
    qtdeclarative6 \
    qtmultimedia6 \
    qtwayland6 qtwayland6-native \
    qtconnectivity6 \
    \
    qca-kf6 \
    libfakekey \
    kconfigwidgets6 \
    kdbusaddons6 \
    kiconthemes6 \
    knotifications6 \
    kio6 \
    kirigami6 kirigami6-addons \
    kpeople6 \
    kwayland6 \
    pulseaudio-qt6 \
    qqc2-desktop-style6 \
    kstatusnotifieritem6 \
"

EXTRA_OECMAKE += "-DEXPERIMENTALAPP_ENABLED=ON"
EXTRA_OECMAKE += "-DSMSAPP_ENABLED=OFF"
# workaround for missing variable, which is only used for Windows builds
EXTRA_OECMAKE += "-DCMAKE_SYSTEM_VERSION=0.0.0"

RDEPENDS:${PN} += " \
    kpeople6 \
"
