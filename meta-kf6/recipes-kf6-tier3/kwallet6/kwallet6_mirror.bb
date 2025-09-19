# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Secure and unified container for user passwords"
HOMEPAGE = "https://api.kde.org/frameworks/kwallet/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.1-or-later"

inherit kf6

DEPENDS:append = " \
    libsecret \
    libgcrypt \
    gpgme \
    qca-kf6 qca-kf6-native \
    \
    kcolorscheme6 \
    kcrash6 \
    kconfigwidgets6 \
    kdbusaddons6 \
    knotifications6 \
    kservice6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
"

PACKAGECONFIG[secretd] = "-DBUILD_KSECRETD=ON, -DBUILD_KSECRETD=OFF, "
PACKAGECONFIG[walletd] = "-DBUILD_KWALLETD=ON, -DBUILD_KWALLETD=OFF, "
PACKAGECONFIG[query] = "-DBUILD_KWALLET_QUERY=ON, -DBUILD_KWALLET_QUERY=OFF, "
PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, virtual/libx11" 

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
    ki18n \
    kdoctools \
    secretd \
    walletd \
    query \
"

PACKAGECONFIG:append:class-target = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
"

EXTRA_OECMAKE:append = " \
    -DOE_QCA_PATH_HOST_ROOT=${STAGING_DIR_HOST} \
"

FILES:${PN} += " \
    ${datadir}/knotifications6 \
    ${datadir}/xdg-desktop-portal/portals/* \
"
