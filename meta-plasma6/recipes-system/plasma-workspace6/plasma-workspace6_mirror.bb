# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2019 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020-2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Plasma Workspace"
HOMEPAGE = ""
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=9e2385fe012386d34dcc5c9863070881"

inherit plasma6
inherit systemd
inherit features_check

REQUIRED_DISTRO_FEATURES = "wayland"


## MISSIONG or else where maybe kwin ?
## find_package(KWinDBusInterface CONFIG REQUIRED)
# find_package(ScreenSaverDBusInterface CONFIG REQUIRED)
# ecm_optional_add_subdirectory(xembed-sni-proxy)

DEPENDS:append = " \
    pipewire \
    \
    qt5compat6 \
    qtdeclarative6 \
    qtwayland6 qtwayland6-native \
    qtpositioning6 \
    qtlocation6 \
    qtshadertools6 qtshadertools-native \
    qtsvg6 \
    \
    qcoro6 \
    \
    karchive6 \
    kcrash6 \
    kdbusaddons6 \
    kdeclarative6 \
    kglobalaccel6 \
    kguiaddons6 \
    kiconthemes6 \
    kitemmodels6 \
    kcmutils kcmutils6-native \
    kded6 \
    kio6 \
    knewstuff6 \
    knotifications6 \
    knotifyconfig6 \
    kpackage6 kpackage6-native \
    kparts6 \
    prison6 \
    krunner6 \
    kstatusnotifierttem6 \
    ksvg6 \
    ktexteditor6 \
    ktextwidgets6 \
    kunitconversion6 \
    kwallet6 \
    kcolorscheme6 \
    kdoctools kdoctools-native \
    \
    plasma5support6 \
    libplasma6 \
    kwayland6 \
    plasma-activities6 \
    plasma-activities-stats6 \
    libksysguard6 \
    kscreen6 \
    layer-shell-qt6 \
    kirigami6 \
    kirigamiaddons6 \
    networkmanager-qt6 \
    kquickcharts6 \
    kuserfeedback6 \
    baloo6 \
    libqalculate6 \
    kwin6 \
    kholidays6 \
    phonon-kf6 \
    libcanberra \
    breeze6 \
    zlib \
    fontconfig \
    packagekit-qt6 \
    polkit-qt6-1 \
    icu \
    libkexiv2-kf6 \    
"

PACKAGECONFIG[packagekit-offline] = "-DPACKAGEKIT_OFFLINE_UPDATES=ON, -DPACKAGEKIT_OFFLINE_UPDATES=OFF, "
PACKAGECONFIG[plasma-x11] = "-DPLASMA_X11_DEFAULT_SESSION=ON, -DPLASMA_X11_DEFAULT_SESSION=OFF, "
PACKAGECONFIG[sddm-wayland-session] = "-DINSTALL_SDDM_WAYLAND_SESSION=ON, -DINSTALL_SDDM_WAYLAND_SESSION=OFF, "
PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, \
    virtual/x11 \
    libice \
    libsm \
    libxau \
    libxcursor \
    libxfixes \
    libxft\    
    libxrender \
    libxtst \
    libxcb \
    xcb-util-cursor \
    libxrandr \
    xcb-util-image \
    xcb-util-renderutil \
"
PACKAGECONFIG[xembed-sni-proxy] = "-DBUILD_xembed-sni-proxy=ON, -DBUILD_xembed-sni-proxy=OFF,"

PACKAGECONFIG:apend = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11 plasma-x11', '',  d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'sddm-wayland-session', '',  d)} \
" 

FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} = "dev-so"

## FIXME  
do_install:append() {
    sed -i 's:${RECIPE_SYSROOT_NATIVE}::g' ${D}${systemd_user_unitdir}/plasma-ksplash-ready.service
    sed -i 's:${RECIPE_SYSROOT_NATIVE}::g' ${D}${systemd_user_unitdir}/plasma-kcminit-phase1.service
    sed -i 's:${RECIPE_SYSROOT_NATIVE}::g' ${D}${systemd_user_unitdir}/plasma-restoresession.service
    sed -i 's:${RECIPE_SYSROOT_NATIVE}::g' ${D}${datadir}/kconf_update/migrate-calendar-to-plugin-id.py
}
