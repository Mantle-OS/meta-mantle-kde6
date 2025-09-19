# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Welcome Center"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-welcome"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"

inherit plasma6

DEPENDS:append = " \
    kcmutils6 \
    kconfigwidgets6 \
    kdbusaddons6 \
    kio6 \
    kservice6 \
    kirigami6 kirigami6-addons6 \
    kjobwidgets6 \
    knewstuff6 \
    ksvg6 \
    kwindowsystem6 \
    libplasma6 \
    \    
    qtbase6 \
    qtdeclarative6 \
"

