# SPDX-FileCopyrightText: 2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Plasma for the Desktop"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-desktop"
LICENSE = "(LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL) & (GPL-2.0-only | GPL-3.0-only | LicenseRef-KDE-Accepted-GPL)"

inherit plasma6

## missing ? 
## KAccounts6 AccountsQt6

DEPENDS:append = " \
    qtwayland6 qtwayland6-native \
    qt5compat6 \
    qtsvg6 \
    qtdeclarative6 \
    \
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
    \
    plasma5support6 \
    libplasma6 \
    plasma-activities6 \
    plasma-activities-stats6 \
    kirigami6 \
    qqc2-desktop-style6 \
    \
    kitemmodels6 \
    ksysguard6 \
    kded6 \
    \
    kworkspace6 \
    baloo6 \
    libcanberra \
    virtual/libx11 \
"

PACKAGECONFIG[mouse-kwin-wayland] = "-DBUILD_KCM_MOUSE_KWIN_WAYLAND=ON, -DBUILD_KCM_MOUSE_KWIN_WAYLAND=OFF, "
PACKAGECONFIG[mouse-kwin-x11] = "-DBUILD_KCM_MOUSE_X11=ON, -DBUILD_KCM_MOUSE_X11=OFF, "
PACKAGECONFIG[touch-kwin-wayland] = "-DBUILD_KCM_TOUCHPAD_KWIN_WAYLAND=ON, -DBUILD_KCM_TOUCHPAD_KWIN_WAYLAND=OFF, "
PACKAGECONFIG[touch-kwin-x11] = "-DBUILD_KCM_TOUCHPAD_X11=ON, -DBUILD_KCM_TOUCHPAD_X11=OFF, "
PACKAGECONFIG[kcm-tablet] = "-DBUILD_KCM_TABLET=ON, -DBUILD_KCM_TABLET=OFF, "

#FIXME handle MACHINE and DISATRO features here after the depoends has been taken care of.
PACKAGECONFIG:append = " \
   mouse-kwin-wayland touch-kwin-wayland \
   mouse-kwin-x11 touch-kwin-x11 \
   kcm-tablet \
"

RDEPENDS:${PN} += " \
    gawk \
    kded6 \
    plasma-nano6 \
    kirigami6 \
    qqc2-desktop-style6 \
"
