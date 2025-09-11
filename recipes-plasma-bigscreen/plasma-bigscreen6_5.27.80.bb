# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2022 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Plasma Bigscreen"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-bigscreen"
LICENSE = "(LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL) & (GPL-2.0-only | GPL-3.0-only | LicenseRef-KDE-Accepted-GPL)"
LIC_FILES_CHKSUM = " \
    file://LICENSES/LGPL-2.1-only.txt;md5=fabba2a3bfeb22a6483d44e9ae824d3f \
    file://LICENSES/LGPL-3.0-only.txt;md5=c51d3eef3be114124d11349ca0d7e117 \
    file://LICENSES/LicenseRef-KDE-Accepted-GPL.txt;md5=b4c280013bbbadfbe92219498dc5228c \
"
PR = "r0"

inherit cmake_plasma

SRCREV = "a18482c36e055b9e32fa07e0b6cadd490c257c39"
SRC_URI = "git://invent.kde.org/plasma/${BPN};nobranch=1;protocol=https"
S = "${WORKDIR}/git"

DEPENDS = " \
    plasma-activities6 \
    plasma-activities6-stats6 \
    plasma-workspace6 \
    kirigami6 \
    kdeclarative6 \
    knotifications6 \
    kio6 \
    kwayland6 \
    kwindowsystem6 \
    qtmultimedia6 \
"

RDEPENDS:${PN} += " \
    mycroft \
"


FILES:${PN} += " \
    ${datadir}/plasma/shells/org.kde.plasma.mycroft.bigscreen/* \
    ${datadir}/plasma/look-and-feel/org.kde.plasma.mycroft.bigscreen/* \
    ${datadir}/plasma/plasmoids/org.kde.mycroft.bigscreen.homescreen/* \
    ${datadir}/kpackage/genericqml/org.kde.plasma.settings/* \
    ${datadir}/kpackage/kcms/kcm_mediacenter_kdeconnect/* \
    ${datadir}/kpackage/kcms/kcm_mediacenter_bigscreen_settings/* \
    ${datadir}/kpackage/kcms/kcm_mediacenter_audiodevice/* \
    ${datadir}/kpackage/kcms/kcm_mediacenter_wifi/* \
    ${datadir}/wayland-sessions/*.desktop \
    ${datadir}/xsessions/ \
    ${libdir}/qt6/qml/org/kde/mycroft/* \
"
