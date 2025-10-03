# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020-2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Milou"
HOMEPAGE = ""
LICENSE = "(LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL) & (GPL-2.0-only | GPL-3.0-only | LicenseRef-KDE-Accepted-GPL) & GPL-2.0-or-later"

inherit plasma6

DEPENDS:append = " \
    qtdeclarative6 \
    \
    krunner6 \
    ksvg6 \
    libplasma6 \
"

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/plasma \
"
 
