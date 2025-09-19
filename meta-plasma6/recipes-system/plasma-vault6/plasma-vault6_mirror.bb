# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Plasma Vault"
SUMMARY = "Plasma applet and services for creating encrypted vaults"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-vault"
LICENSE = "LGPL-2.1-only & (GPL-2.0-only | GPL-3.0-only | LicenseRef-KDE-Accepted-GPL)"

inherit plasma6

DEPENDS:append = " \
    kconfig6 \
    kitemmodels6 \
    kservice6 \
    kio6 \
    networkmanager-qt6 \
    libplasma6 \
    libksysguard6 \
"
