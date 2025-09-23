# SPDX-FileCopyrightText: 2019 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2019-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2023-2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "QML based Display Manager"
HOMEPAGE = "https://github.com/sddm/sddm"
LICENSE = "GPL-2.0-only & CC-BY-3.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=4641e94ec96f98fabc56ff9cc48be14b \
    file://LICENSE.CC-BY-3.0;md5=36b7342d5619a3a0d3b581e89803ec25 \
"

inherit kde6-cmake 
inherit pkgconfig
inherit systemd
inherit useradd
inherit features_check

ANY_OF_DISTRO_FEATURES = "wayland x11"

SRC_URI = " \
    git://github.com/sddm/sddm;branch=develop;protocol=https \
    file://sddm.conf.plasma-desktop \
    file://sddm.conf.plasma-mobile \
    file://sddm.conf.plasma-bigscreen \
    file://sddm.pam \
    file://sddm-autologin.pam \
    file://sddm-greeter.pam \
"

SRCREV = "4ec29a8bba033d475f197693fac6cb0c383a1da2"
S = "${WORKDIR}/git"

DEPENDS:append = " \
    libpam \
    libxau \
    libxcb \
    libxkbcommon \
"

PACKAGECONFIG[man] = "-DBUILD_MAN_PAGES=ON, -DBUILD_MAN_PAGES=OFF, git-native "
PACKAGECONFIG[journald] = "-DENABLE_JOURNALD=ON, -DENABLE_JOURNALD, systemd"
PACKAGECONFIG[systemd] = "-DNO_SYSTEMD=OFF, -DNO_SYSTEMD=ON, systemd"
# PACKAGECONFIG[elogin] = "-DUSE_ELOGIND=ON, -DUSE_ELOGIND=OFF, "  needs elogind
PACKAGECONFIG[qt6] = "-DBUILD_WITH_QT6=ON -DQML_INSTALL_DIR=${QT6_INSTALL_QMLDIR} , \
                      -DBUILD_WITH_QT6=OFF, \
                      qtdeclarative6 qttools6 qttools6-native"
PACKAGECONFIG[install-pam-config] = "-DINSTALL_PAM_CONFIGURATION=ON, -DINSTALL_PAM_CONFIGURATION=OFF, "

PACKAGECONFIG:append = " \
    journald \
    systemd \
    install-pam-config \
    qt6 \
"


EXTRA_OECMAKE += " \
    -DLOGIN_DEFS_PATH=${STAGING_DIR_HOST}${sysconfdir}/login.defs \
    -DSYSTEMD_SYSTEM_UNIT_DIR=${systemd_unitdir}/system \
"

do_configure:append() {
    # fix sysroot path
    sed -i 's:${STAGING_DIR_HOST}.*${libdir}:${libdir}:g' ${B}/src/common/Constants.h
}

do_install:append() {
    install -d ${D}/${sysconfdir}/sddm.conf.d

    # autologin configurations
    install -m 644 ${WORKDIR}/sddm.conf.plasma-desktop ${D}/${sysconfdir}/sddm.conf.d/00-plasma-desktop.conf
    install -m 644 ${WORKDIR}/sddm.conf.plasma-mobile ${D}/${sysconfdir}/sddm.conf.d/01-plasma-mobile.conf
    install -m 644 ${WORKDIR}/sddm.conf.plasma-bigscreen ${D}/${sysconfdir}/sddm.conf.d/02-plasma-bigscreen.conf

    install -d ${D}${sysconfdir}/pam.d
    install -m 644 ${WORKDIR}/sddm.pam ${D}${sysconfdir}/pam.d/sddm
    install -m 644 ${WORKDIR}/sddm-autologin.pam ${D}${sysconfdir}/pam.d/sddm-autologin
    install -m 644 ${WORKDIR}/sddm-greeter.pam ${D}${sysconfdir}/pam.d/sddm-greeter

    install -d ${D}${localstatedir}/lib/sddm
    chown -R sddm:sddm ${D}${localstatedir}/lib/sddm
    chmod 0750 ${D}${localstatedir}/lib/sddm
}

PACKAGES =+ " \
    ${PN}-config-plasma-mobile6 \
    ${PN}-config-plasma-bigscreen6 \
    ${PN}-config-plasma-desktop6 \
"

FILES:${PN} += " \
    ${libdir}/qt6/qml/* \
    ${libdir}/sysusers.d/* \
    ${libdir}/tmpfiles.d/* \
    ${datadir}/dbus-1/system.d/* \
"

FILES:${PN}-config-plasma-desktop = " \
    ${sysconfdir}/sddm.conf.d/00-plasma-desktop.conf \
"

FILES:${PN}-config-plasma-mobile = " \
    ${sysconfdir}/sddm.conf.d/01-plasma-mobile.conf \
"

FILES:${PN}-config-plasma-bigscreen = " \
    ${sysconfdir}/sddm.conf.d/02-plasma-bigscreen.conf \
"

SYSTEMD_SERVICE:${PN} = "ssdm.service"
USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "--system --home ${localstatedir}/lib/sddm --shell /bin/false --user-group --groups video sddm"

RDEPENDS:${PN} += "\
    bash \
    xcb-util \
    qtbase6-plugins \
    qtdeclarative6-plugins \
    qtdeclarative6-qmlplugins \
"

RRECOMMENDS:${PN} += " \
    qtvirtualkeyboard6-plugins \
    qtvirtualkeyboard6-qmlplugins \
"
