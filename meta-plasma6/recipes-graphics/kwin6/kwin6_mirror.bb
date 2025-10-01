# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020-2022 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KWin"
HOMEPAGE = ""
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"

inherit plasma6

DEPENDS:class-target = " \
    ${PN}-native \
    \
    qt5compat6 \
    qtdeclarative6 \
    qtwayland6 qtwayland6-native \
    qtsensors6 \
    qtsvg6 \
    \
    kcolorscheme6 \
    kcrash6 \
    kdbusaddons6 \
    kglobalaccel6 \
    kguiaddons6 \
    kidletime6 \
    kpackage6 kpackage6-native \
    kservice6 \
    ksvg6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
    \
    kwayland6 \
    plasma-activities6 \
    kdoctools6 \
    kirigami6 \
    libplasma6 \
    kdecoration6 \
    virtual/egl \
    libepoxy \
    wayland wayland-native \
    plasma-wayland-protocols-kf6 \
    libxkbcommon \
    libcanberra \
    libinput \
    libxcvt \
    libdrm \
    lcms \
    freetype \
    fontconfig \
    hwdata \
    systemd \
    libdisplay-info \
    pipewire \
"

DEPENDS:class-native = " \
    qtbase6-native \
    ecm-kf6-native \
"

## FIXME ADD Aurorae
PACKAGECONFIG[decorations] = "-DKWIN_BUILD_DECORATIONS=ON, -DKWIN_BUILD_DECORATIONS=OFF, breeze6 " 

PACKAGECONFIG[kcms] = "-DBUILD_KCMS=ON, -DBUILD_KCMS=OFF, \
    kdeclarative6 \
    kcmutils6 kcmutils6-native \
    knewstuff6 \
    kservice6 \
    kxmlgui6 \   
"

PACKAGECONFIG[notifications] = "-DKWIN_BUILD_NOTIFICATIONS=ON, -DKWIN_BUILD_NOTIFICATIONS=OFF, knotifications6"
PACKAGECONFIG[screen-locker] = "-DKWIN_BUILD_SCREENLOCKER=ON, -DKWIN_BUILD_SCREENLOCKER=OFF, kscreenlocker6"
PACKAGECONFIG[tarbox] = "-DKWIN_BUILD_TABBOX=ON, -DKWIN_BUILD_TABBOX=OFF, "
PACKAGECONFIG[x11] = "-DKWIN_BUILD_X11=ON, -DKWIN_BUILD_X11=OFF, \
    libxcb \
    libxcomposite \
    xcb-util-cursor \
    xserver-xorg \
    xcb-util-keysyms \
    libxcb \
    libxrandr \
    libxrender \
    libxres \
    xcb-util-renderutil \
    libxext \
    xcb-util-wm \
    libxfixes \
    libxinerama \
    virtual/libx11 \
    xwayland \
    geis \
    xinput \
"

PACKAGECONFIG[global-shortcuts] = "-DKWIN_BUILD_GLOBALSHORTCUTS=ON, -DKWIN_BUILD_GLOBALSHORTCUTS=OFF, kglobalacceld6"
PACKAGECONFIG[runners] = "-DKWIN_BUILD_RUNNERS=ON, -DKWIN_BUILD_RUNNERS=OFF, "

PACKAGECONFIG:append:class-target = " \
    decorations \
    kcms \
    notifications \
    screen-locker \
    tarbox \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
    global-shortcuts \
    runners \
"


SRC_URI:append = " \
    file://kwinrc \
"

SRC_URI:append:class-native = " \
    file://0001-Add-install-operation-for-native-build.patch \
"

OECMAKE_SOURCEPATH:class-native = "${S}/src/wayland/tools"
EXTRA_OECMAKE:append:class-target = " \
    -DQTWAYLANDSCANNER_KDE_EXECUTABLE=${STAGING_DIR_NATIVE}/${bindir}/qtwaylandscanner_kde \
    -DBUILD_TESTING=OFF \
"
do_install:append() {
    # dirty workaround for aurora crash on Wayland
    rm -rf ${D}/${libdir}/plugins/org.kde.kdecoration2/

    install -d ${D}/${sysconfdir}/xdg
    install -m 644 ${WORKDIR}/kwinrc ${D}/${sysconfdir}/xdg/kwinrc
}

FILES_SOLIBSDEV = ""

RDEPENDS:${PN}:append:class-target = " \
    qtwayland6-plugins \
    xwayland \
    perl \
"

RRECOMMENDS:${PN}:append:class-target = " \
    maliit-keyboard-qt6 \
"

FILES:${PN} += " \
    ${libdir}/kconf_update_bin \
    ${datadir}/krunner \
    ${datadir}/kwin-wayland \
    ${datadir}/kconf_update \
    ${datadir}/config.kcfg \
    ${datadir}/knotifications6 \
    ${datadir}/knsrcfiles \
"

BBCLASSEXTEND = "native"
