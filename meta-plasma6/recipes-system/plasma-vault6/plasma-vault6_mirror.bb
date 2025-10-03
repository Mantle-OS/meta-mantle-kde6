# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Plasma Vault"
SUMMARY = "Plasma applet and services for creating encrypted vaults"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-vault"
LICENSE = "LGPL-2.1-only & (GPL-2.0-only | GPL-3.0-only | LicenseRef-KDE-Accepted-GPL)"

inherit plasma6

#FIXME https://nuetzlich.net/gocryptfs/
DEPENDS:append = " \
    qtdeclarative6 \
    \
    kconfigwidgets6 \
    kdbusaddons6 \
    kitemmodels6 \
    kio6 \
    kservice6 \
    kwidgetsaddons6 \
    \
    libplasma6 \
    plasma-activities6 \
    networkmanager-qt6 \
    libksysguard6 \    
"

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/plasma \
"
