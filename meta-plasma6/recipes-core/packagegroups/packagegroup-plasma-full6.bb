# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "KDE Plasma Mobile"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit packagegroup

RDEPENDS:${PN} = " \
    bluedevil6 \
    breeze6 \
    discover6 \
    plasma-activities6 \
    plasma-activities-stats6 \
    kactivitymanagerd6 \
    kde6-cli-tools-kde6 \
    kdecoration6 \
    kglobalacceld6 \
    kpipewire6 \
    kscreenlocker6 \
    kwayland6 \
    kwin6 \
    layer-shell-qt6-kde6 \
    libkscreen6 \
    libksysguard6 \
    milou6 \
    libplasma6 \
    plasma-integration6 \
    plasma-mobile6 \
    plasma-nano6 \
    plasma-nm6 \
    plasma-pa6 \
    plasma-systemmonitor6 \
    plasma-workspace6 \
    plasma5support6 \
    polkit-kde6-agent-1-kde6 \
    powerdevil6 \
    systemsettings6 \
    xdg-desktop-portal-kde6 \
"
