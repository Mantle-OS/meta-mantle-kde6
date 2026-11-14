# SPDX-FileCopyrightText: 2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Ocean Sound Theme"
HOMEPAGE = "https://invent.kde.org/plasma/ocean-sound-theme"
LICENSE = "BSD-2-Clause AND CC-BY-SA-4.0"

inherit plasma6

FILES:${PN} += " \
    ${datadir}/sounds \
"
