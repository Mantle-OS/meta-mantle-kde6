# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020-2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Milou"
HOMEPAGE = ""
LICENSE = "GPL-2.0-or-later AND (GPL-2.0-only OR GPL-3.0-only OR LicenseRef-KDE-Accepted-GPL) AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"

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
 
