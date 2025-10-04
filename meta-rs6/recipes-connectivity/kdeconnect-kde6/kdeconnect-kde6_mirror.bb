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
    \
    kirigami6 kirigami6-addons \
    kpackage6 \
    libfakekey \
    \
    wayland wayland-native \
    wayland-protocols \
    libxkbcommon \
    openssl \
    \
    kdbusaddons6 \
    kiconthemes6 \
    knotifications6 \
    kio6 \
    kcolorscheme6 \
    kservice6 \
    solid6 \
    kpeople6 \
    kwindowsystem6 \
    kguiaddons6 \
    kcrash6 \
    kitemmodels6 \
    kstatusnotifieritem6 \
    \
    qqc2-desktop-style6 \
    modemmanager-qt6 \
"

PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, virtual/libx11 libfakekey libxtst"
PACKAGECONFIG[pulseaudio] = "-DWITH_PULSEAUDIO=ON, -DWITH_PULSEAUDIO=OFF, pulseaudio-qt6"
PACKAGECONFIG[bluetooth] = "-DBLUETOOTH_ENABLED=ON, -DBLUETOOTH_ENABLED=OFF, qtconnectivity6"
PACKAGECONFIG[zeroconf] = "-DMDNS_ENABLED=ON, -DMDNS_ENABLED=OFF, avahi avahi-libnss-mdns"
PACKAGECONFIG[loopback] = "-DLOOPBACK_ENABLED=ON, -DLOOPBACK_ENABLED=OFF, "

PACKAHECONFIG:append = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'pulseaudio', d)} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'zeroconf', d)} \
"

FILES:${PN} += " \
    ${datadir}/contractor \
    ${datadir}/metainfo \
    ${datadir}/kdeconnect \
    ${datadir}/zsh \
    ${datadir}/knotifications6 \
    ${datadir}/Thunar \
    ${datadir}/plasma \
    ${datadir}/nautilus-python \
    ${datadir}/deepin \
"
