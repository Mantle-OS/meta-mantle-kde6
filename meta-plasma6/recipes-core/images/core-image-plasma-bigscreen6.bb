# SPDX-FileCopyrightText: 2022 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

SUMMARY = "A basic KDE Plasma Bigscreen image"

IMAGE_FEATURES += "splash hwcodecs"

LICENSE = "MIT"

inherit features_check
inherit core-image

REQUIRED_DISTRO_FEATURES += "wayland"

IMAGE_INSTALL += " \
    ${CORE_IMAGE_BASE_INSTALL} \
    wayland \
    dbus \
    sddm6 \
    sddm-config-plasma-bigscreen6 \
    packagegroup-plasma-bigscreen-workspace6 \
"

SYSTEMD_DEFAULT_TARGET="graphical.target"
