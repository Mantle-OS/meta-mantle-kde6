# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2020-2025 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "GCompris"
DESCRIPTION = "GCompris is a high quality educational software suite, including a large number of activities for children aged 2 to 10."
HOMEPAGE = "https://gcompris.net"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-3.0-only.txt;md5=49fc03046e56a282c0c743b5d3a55b7c"

inherit kde6-ecm
inherit kf6-packageconfig

SRC_URI = "git://invent.kde.org/education/gcompris;nobranch=1;protocol=https"
SRCREV = "0ac513480426c8ce7fce983505111e7a61b02b9e"

DEPENDS:append = " \
    openssl \
    libogg \
    p7zip-native \
    python3-native \
    \
    qtcharts6 \    
    qtmultimedia6 \
    qtsensors6 \    
    qtdeclarative6 \
    qtsvg6 \
    \
    kcrash6 \
    kio6 \
    qml-box2d6 \
    phonon-kf6 \
"

RDEPENDS:${PN} += " \
    qtdeclarative6-qmlplugins \
    qtmultimedia6-qmlplugins \
"

PACKAGECONFIG[icu] = "-DSHIP_SYSTEM_ICU=ON, -DSHIP_SYSTEM_ICU=OFF, icu"
PACKAGECONFIG[compile-docs] = "-DCOMPILE_DOC=ON, -DCOMPILE_DOC=OFF,"
PACKAGECONFIG[rcc] = "-DWITH_RCC=ON, -DWITH_RCC=OFF, "
PACKAGECONFIG[skip-translations] = "-DSKIP_TRANSLATIONS=ON, -DSKIP_TRANSLATIONS=OFF, "
PACKAGECONFIG[kiosk] = "-DWITH_KIOSK_MODE=ON, -DWITH_KIOSK_MODE=OFF, "


EXTRA_OECMAKE:append = " -DBUILD_STANDALONE=OFF "

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
    ki18n \
    rcc \
"

EXTRA_OECMAKE:append = " -DQML_BOX2D_MODULE=system -DQML_BOX2D_LIBRARY=${STAGING_DIR_TARGET}/usr/lib/Box2D/libqmlbox2d.so"

FILES:${PN} += " \
    ${datadir}/gcompris-qt/rcc \
    ${datadir}/gcompris-qt/translations \
    ${datadir}/metainfo \
"
