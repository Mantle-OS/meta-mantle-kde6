# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Plasma Vault"
SUMMARY = "Plasma applet and services for creating encrypted vaults"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-vault"
LICENSE = "LGPL-2.1-only & (GPL-2.0-only | GPL-3.0-only | LicenseRef-KDE-Accepted-GPL)"
PR = "r0"

inherit cmake_plasma
inherit reuse_license_checksums

SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/plasma-vault-6.4.4.tar.xz"
SRC_URI[sha256sum] = "2d843b1e37cb41b099c74142ad0d746942fdb447186d3e372ca6b7a3b051bc81"

DEPENDS = " \
    kconfig6 \
    kitemmodels6 \
    kservice6 \
    kio6 \
    networkmanager-qt6 \
    libplasma6 \
    libksysguard6 \
    qtbase6 \
"

FILES:${PN} += " \
    ${datadir}/plasma/plasmoids/org.kde.plasma.vault/* \
"
