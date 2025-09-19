# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2019 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Bluedevil"
HOMEPAGE = ""
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"

inherit plasma6

DEPENDS:append = " \
    bluez-qt6 \
    kcoreaddons6 \
    kdbusaddons6 \
    kded6 \
    kiconthemes6 \
    kio6 \
    knotifications \
    ksvg6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
    libplasma6 \
    qtdeclarative6 \
    shared-mime-info shared-mime-info-native \
"
