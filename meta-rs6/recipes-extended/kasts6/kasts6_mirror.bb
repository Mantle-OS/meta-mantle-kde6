# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Kasts"
DESCRIPTION = "Kasts is a convergent podcast application."
HOMEPAGE = "https://invent.kde.org/plasma-mobile/kasts"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-3.0-or-later.txt;md5=8da5784ab1c72e63ac74971f88658166"

inherit rs6

SRC_URI:append = " \
    file://0001-so-version-for-kmediasession.patch \
"

PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    ki18n \
"

DEPENDS:append = " \
    qtdeclarative6 \
    qtsvg6 \
    qtmultimedia6 \
    \
    kirigami6 kirigami6-addons \
    syndication6 \
    threadweaver6 \
    kcolorscheme6 \
    kiconthemes6 \
    \
    kdbusaddons6 \
    breeze-icons6 \
    kwindowsystem6 \
    \
    taglib \
    qtkeychain6 \
    kcrash6 \
    vlc \
"

PACKAGECONFIG[example-player] = "-DBUILD_EXAMPLE_PLAYER=ON, -DBUILD_EXAMPLE_PLAYER=OFF, "
PACKAGECONFIG[gstreamer] = "-DBUILD_GSTREAMER_BACKEND=ON, -DBUILD_GSTREAMER_BACKEND=OFF, gstreamer1.0"

PACKAGECONFIG:append = " \
    gstreamer \
"

FILES:${PN} += " \
    ${datadir}/metainfo \
"
