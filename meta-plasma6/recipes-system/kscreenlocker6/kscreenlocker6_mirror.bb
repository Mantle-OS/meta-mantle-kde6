# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020-2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KScreenLocker"
HOMEPAGE = "https://invent.kde.org/plasma/kscreenlocker"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit plasma6
inherit features_check 

REQUIRED_DISTRO_FEATURES = "x11 systemd"

SRC_URI:append = " \
    file://kde.pam \
    file://kscreenlockerrc \
    file://kscreensaverrc \
"

DEPENDS:append = " \
    qtdeclarative6 \
    \
    kcrash6 \
    kcmutils6 kcmutils6-native \
    kio6 \
    kglobalaccel6 \
    kidletime6 \
    knotifications6 \
    solid6 \
    kwindowsystem6 \
    kxmlgui6 \
    ksvg6 \
    \
    libplasma6 \
    \
    virtual/libx11 \
    libxcb \
    xcb-util-keysyms \
    libxtst \
    xinput \
    xinput-calibrator \
    \
    layer-shell-qt6 \
    libkscreen6 \
    \
    libpam \
    wayland wayland-native \
    systemd consolekit \
"

do_install:append() {
    install -d 0755 ${D}${sysconfdir}/pam.d
    install -m 0644 ${WORKDIR}/kde.pam ${D}${sysconfdir}/pam.d/kde
    install -d 0755 ${D}{sysconfdir}/xdg
    install -m 0644 ${WORKDIR}/kscreenlockerrc ${D}{sysconfdir}/xdg/
    install -m 0644 ${WORKDIR}/kscreensaverrc ${D}{sysconfdir}/xdg/
}

RDEPENDS:${PN} += " \
    perl \
    qqc2-breeze-style6 \
"

FILES:${PN} += " \
    ${datadir}/ksmserver \
    ${datadir}/knotifications6 \
"
 
