# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Powerdevil"
HOMEPAGE = ""
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=5c213a7de3f013310bd272cdb6eb7a24"

inherit plasma6

DEPENDS:append = " \
    qcoro6 \
    kcrash6 \
    kdbusaddons6 \
    kidletime6 \
    kitemmodels6 \
    kglobalaccel6 \
    kio6 \
    kirigami6 \
    kcmutils6 kcmutils6-native \
    knotifications6 \
    krunner6 \
    solid6 \
    kwindowsystem6 \
    kxmlgui6 \
    \
    libplasma6 \    
    plasma-activities6 \
    kscreen6 \
    plasma-workspace6 \
    \
    libxrandr \
    libxext \
    libxcb \
    \
    ddcutil6 \
    plasma-wayland-protocols-kf6 \
    wayland wayland-native \
    \
    qtwayland6 qtwayland6-native \
    libcap \
"


FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/knotifications6 \
    ${datadir}/polkit-1 \
    ${datadir}/plasma \
"
