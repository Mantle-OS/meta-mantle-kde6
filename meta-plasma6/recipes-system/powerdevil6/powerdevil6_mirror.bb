# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Powerdevil"
HOMEPAGE = ""
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=5c213a7de3f013310bd272cdb6eb7a24"

inherit plasma6

DEPENDS = " \
    plasma-activities6 \
    kidletime6 \
    kconfig6 \
    kdbusaddons6 \
    solid6 \
    kglobalaccel6 \
    kio6 \
    knotifyconfig6 \
    libkscreen6 \
    kwayland6 \
    kitemmodels6 \
    plasma-workspace6 \
    bluez-qt6 \
    ddcutil6 \
    qtwayland6-native \
"
