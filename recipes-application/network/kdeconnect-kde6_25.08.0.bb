# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KDE Connect"
HOMEPAGE = "https://community.kde.org/KDEConnect"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=fed54355545ffd980b814dab4a3b312c"

DEPENDS += " \
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
    kirigami6 kirigami6-addons6 \
    kpeople6 \
    kwayland6 \
    pulseaudio-qt6-kde6 \
    qqc2-desktop-style6 \
    kstatusnotifieritem6 \
"

inherit cmake_kdeapp
inherit kf6-kcoreaddons kf6-kconfig kf6-kauth kf6-kcmutils

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/kdeconnect-kde-25.08.0.tar.xz"
SRC_URI[sha256sum] = "edc6d8d5bb3aab53208a229528ef36a5bc959bfaec17512b5621502d1d37d260"

EXTRA_OECMAKE += "-DEXPERIMENTALAPP_ENABLED=ON"
EXTRA_OECMAKE += "-DSMSAPP_ENABLED=OFF"
# workaround for missing variable, which is only used for Windows builds
EXTRA_OECMAKE += "-DCMAKE_SYSTEM_VERSION=0.0.0"

FILES:${PN} += " \
    ${datadir}/Thunar \
    ${datadir}/contractor \
    ${datadir}/kdeconnect \
    ${datadir}/deepin \
    ${datadir}/nautilus-python \
    ${datadir}/plasma \
    ${datadir}/zsh \
    ${libdir}/plugins/plasma/kcms/systemsettings_qwidgets/kcm_kdeconnect.so \
"

RDEPENDS:${PN} += " \
    kpeople6 \
"
