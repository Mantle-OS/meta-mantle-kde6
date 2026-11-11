# SPDX-FileCopyrightText: 2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Plasma for the Desktop"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-desktop"
LICENSE = "(LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL) & (GPL-2.0-only | GPL-3.0-only | LicenseRef-KDE-Accepted-GPL)"

inherit plasma6

DEPENDS:append = " \
    qtwayland6 qtwayland6-native \
    qt5compat6 \
    qtsvg6 \
    qtdeclarative6 \
"

DEPENDS:append = " \
    kcrash6 \
    kcmutils6 kcmutils6-native \
    knewstuff6 \
    kio6 \
    knotifications6 \
    knotifyconfig6 \
    attica6 \
    krunner6 \
    kglobalaccel6 \
    kguiaddons6 \
    kdbusaddons6 \
    kwidgetsaddons6 \
    kcodecs6 \
    sonnet6 \
    kpackage6 kpackage6-native \
    kiconthemes6 \
    kxmlgui6 \
    ksvg6 \
"

DEPENDS:append = " \
    plasma5support6 \
    libplasma6 \
    plasma-activities6 \
    plasma-activities-stats6 \
    kirigami6 kirigami6-addons \
    qqc2-desktop-style6 \
    kpipewire6 \
    \
    libksysguard6 \
    kitemmodels6 \
    kded6 \
    \
    plasma-workspace6 \
    baloo6 \
    libcanberra \
"

# find_package(XdgUserDir)
# find_package(FontNotoColorEmoji)

# gamepad
PACKAGECONFIG[udev] = ", , udev "
PACKAGECONFIG[gamepad] = ", , libsdl2 "
PACKAGECONFIG[kimpanel] = ", , ibus scim glib-2.0"
PACKAGECONFIG[sddm-theme] = " -DINSTALL_SDDM_THEME=ON, -DINSTALL_SDDM_THEME=OFF, "
PACKAGECONFIG[packagekit] = " , , packagekit-qt6"
PACKAGECONFIG[noto] = ", , noto-color-emoji-unhinted"

PACKAGECONFIG[wayland] = " \
    -DBUILD_KCM_MOUSE_KWIN_WAYLAND=ON  -DBUILD_KCM_TOUCHPAD_KWIN_WAYLAND=ON , \
    -DBUILD_KCM_MOUSE_KWIN_WAYLAND=OFF -DBUILD_KCM_TOUCHPAD_KWIN_WAYLAND=OFF \
"

PACKAGECONFIG[x11] = " \
    -DBUILD_KCM_MOUSE_X11=ON  -DBUILD_KCM_TOUCHPAD_X11=ON, \
    -DBUILD_KCM_MOUSE_X11=OFF -DBUILD_KCM_TOUCHPAD_X11=OFF, \
    virtual/libx11 \
    libxi \
    libxcb \
    libxkbcommon \
    xcb-util \
    xcb-util-image \
    xcb-util-keysyms \
    libxft \
    libxkbfile \
    xorgproto \
    libice \
    libsm \
    libxau \
    libxcursor \
    libxfixes \
    libxrender \
    libxtst \
    libxrandr \
    xcb-util-cursor \
    xcb-util-renderutil \
    libinput \
    xf86-input-libinput \
    xserver-xorg \
"

PACKAGECONFIG[tablet] = "-DBUILD_KCM_TABLET=ON, -DBUILD_KCM_TABLET=OFF, libwacom"

PACKAGECONFIG:append = " \
    udev \
    sddm-theme \
    packagekit \
    noto \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '',  d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '',  d)} \
"

RDEPENDS:${PN} += " \
    gawk \
    kded6 \
    plasma-nano6 \
    kirigami6 \
    qqc2-desktop-style6 \
"

FILES:${PN} += " \
    ${datadir}/kcm_recentFiles \
    ${datadir}/kcmmouse \
    ${datadir}/config.kcfg \
    ${datadir}/kglobalaccel \
    ${datadir}/metainfo \
    ${datadir}/kcmkeys \
    ${datadir}/solid \
    ${datadir}/polkit-1 \
    ${datadir}/knsrcfiles \
    ${datadir}/plasma \
    ${datadir}/knotifications6 \
    ${datadir}/kcmsolidactions \
"

PACKAGES =+ " \
    ${PN}-sddm-theme \
"

FILES:${PN}-sddm-theme = " \
    ${datadir}/sddm/themes/breeze \
"

RDEPENDS:${PN}-sddm-theme = " \
    sddm-qt6 \
"

INSANE_SKIP:${PN}-plugins += "dev-so"






