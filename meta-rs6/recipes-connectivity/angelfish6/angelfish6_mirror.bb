# SPDX-FileCopyrightText: 2024 Maxime Roussin-Bélanger <maxime.roussinbelanger@gmail.com>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Angelfish is a modern mobile webbrowser."
HOMEPAGE = "https://apps.kde.org/angelfish/"
SECTION = "network"
LICENSE = "(LGPL-2.0-only | LGPL-2.0-or-later | LGPL-2.1-only | LGPL-3.0-or-later | LicenseRef-KDE-Accepted-LGPL) & (GPL-2.0-only | GPL-2.0-or-later | GPL-3.0-only | LicenseRef-KDE-Accepted-GPL)"

inherit rs6

PACKAGECONFIG:append = " \
    kdoctools \
    kconfig \
    ki18n \
    kcoreaddons \
"

DEPENDS:append = " \
    qtwebengine6 \
    qtdeclarative6 \
    qtsvg6 \
    \
    kirigami6 kirigami6-addons \
    purpose6 \
    kdbusaddons6 \
    kwindowsystem6 \
    knotifications6 \
    kcrash6 \
    qqc2-desktop-style6 \
    futuresql6 \
    qcoro6 \
"

PACKAGECONFIG[adblock] = ", , angelfish6-adblocker"
PACKAGECONFIG:append = " adblock"

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/knotifications6 \
    ${datadir}/config.kcfg \
"
