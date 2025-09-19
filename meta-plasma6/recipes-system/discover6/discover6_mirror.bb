# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Discover"
HOMEPAGE = ""
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"

inherit plasma6

DEPENDS = " \
    attica6 \
    karchive6 \
    kconfig6 \
    kcoreaddons6 \
    kcrash6 \
    kdbusaddons6 \
    kdeclarative6 \
    kidletime6 \
    kio6 \
    kirigami6 \
    kitemmodels6 \
    knewstuff6 \
    knotifications6 \
    kxmlgui6 \
    purpose6 \
    qtdeclarative6 \
    qtwebview6 \
"
