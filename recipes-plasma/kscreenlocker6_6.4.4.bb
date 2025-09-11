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
PR = "r0"

inherit cmake_plasma

SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/kscreenlocker-6.4.4.tar.xz"
SRC_URI[sha256sum] = "5cc1dd23be325f1ddcf005556f0ac14077789524aa0b3e1e83b97ff77d4932a8"

DEPENDS = " \
    qtdeclarative6 \
    \
    kcrash6 \
    kglobalaccel6 \
    kidletime6 \
    knotifications6 \
    ksvg6 \
    kwindowsystem6 \
    kxmlgui6 \
    solid6 \
    \
    libplasma6 \
    libkscreen6 \
    layer-shell-qt6-kde6 \
    \
    libpam \
    libxcb \
    wayland-native \
"

SRC_URI:append = " \
    file://kde.pam \
    file://kscreenlockerrc \
    file://kscreensaverrc \
"


do_install:append() {
    # compatibility workaround for Yocto versions older than Styhead
    [ -z ${UNPACKDIR+x} ] && UNPACKDIR=${WORKDIR}

    install -d 0755 ${D}${sysconfdir}/pam.d
    install -m 0644 ${UNPACKDIR}/kde.pam ${D}${sysconfdir}/pam.d/kde
    install -d 0755 ${D}{sysconfdir}/xdg
    install -m 0644 ${UNPACKDIR}/kscreenlockerrc ${D}{sysconfdir}/xdg/
    install -m 0644 ${UNPACKDIR}/kscreensaverrc ${D}{sysconfdir}/xdg/
}

FILES:${PN} += " \
    ${datadir}/ksmserver/screenlocker/org.kde.passworddialog/*.desktop \
    ${datadir}/kpackage/kcms/kcm_screenlocker \
    ${libdir}/plugins/plasma/kcms/systemsettings/kcm_screenlocker.so \
"

RDEPENDS:${PN} += " \
    perl \
    qqc2-breeze6-style6 \
"
