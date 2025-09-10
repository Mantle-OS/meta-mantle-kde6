# SPDX-FileCopyrightText: 2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

REQUIRED_DISTRO_FEATURES = "wayland"

RDEPENDS:${PN} += " \
    bluedevil6 \
    breeze6 \
    kactivitymanagerd6 \
    kde6-cli-tools-kde6 \
    kdecoration6 \
    kscreenlocker6 \
    kwin6 \
    libkscreen6 \
    libksysguard6 \
    milou6 \
    plasma-disks6 \
    plasma-firewall6 \
    plasma-integration6 \
    plasma-nano6 \
    plasma-nm6 \
    plasma-pa6 \
    plasma-workspace6 \
    polkit-kde6-agent-1-kde6 \
    powerdevil6 \
    ${@bb.utils.contains('DISTRO_FEATURES', 'desktop-portal', 'xdg-desktop-portal-kde6', '', d)} \
"
