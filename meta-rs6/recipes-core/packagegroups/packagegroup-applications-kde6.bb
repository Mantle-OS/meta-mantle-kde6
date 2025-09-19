# SPDX-FileCopyrightText: 2019 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "KDE Applications"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS:${PN} = " \
    blinken6 \
    gcompris6 \
    kanagram6 \
    khangman6 \
    kwordquiz6 \
    marble6 \
    \
    gwenview6 \
    okular6 \
    \
    dragon6 \
    elisa6 \
    \
    kdeconnect-kde6 \
    \
    itinerary6 \
    \
    alligator6 \
    kasts6 \
    kclock6 \
    koko6 \
    kongress6 \
    krecorder6 \
    ktrip6 \
    kweather6 \
    washipad6 \
    \
    kjournald6 \
    \
    kdialog6 \
    konsole6 \
    plasma-camera6 \
    plasma-settings6 \
"

# not ported to Qt6 yet
# kirigami-gallery
