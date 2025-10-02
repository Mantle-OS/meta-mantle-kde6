# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Plasma Nano"
SUMMARY = "A minimal plasma shell package intended for embedded devices"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-nano"
LICENSE = "MIT & LGPL-2.0-or-later & GPL-2.0-or-later"

inherit plasma6

DEPENDS:append = " \
    qtdeclarative6 \
    qtsvg6 \
    \
    kwindowsystem6 \
    kservice6 \
    kitemmodels6 \
    libplasma6 \
    kwayland6 \
    kpackage6 kpackage6-native \
"

FILES:${PN} += " \
    ${datadir}/plasma \
"
