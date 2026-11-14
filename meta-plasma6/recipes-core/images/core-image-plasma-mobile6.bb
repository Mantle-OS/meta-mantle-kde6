# SPDX-FileCopyrightText: 2019 Alistair Francis <alistair.francis@wdc.com>
#
# SPDX-License-Identifier: MIT

SUMMARY = "A basic KDE Plasma Mobile image"

IMAGE_FEATURES += "splash hwcodecs"

LICENSE = "MIT"

require recipes-core/images/mantle-image-core.bb

inherit features_check

REQUIRED_DISTRO_FEATURES = "wayland"

IMAGE_FEATURES:append = " \
    splash \
    hwcodecs \
    ssh-server-dropbear \
"

IMAGE_INSTALL:append = " \
    ${CORE_IMAGE_BASE_INSTALL} \
    wayland dbus \
    sddm-qt6 \
    packagegroup-plasma-mobile6 \
    ttf-roboto \
    ttf-noto-emoji-regular \
"

SYSTEMD_DEFAULT_TARGET = "graphical.target"
inherit core-image
#    sddm-config-plasma-mobile6 
