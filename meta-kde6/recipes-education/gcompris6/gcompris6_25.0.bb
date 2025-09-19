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
S = "${WORKDIR}/git"

DEPENDS:append = " \
    kcrash6 \
    kio6 \
    \
    qtcharts6 \
    qtmultimedia6 \
    qtsensors6 \
    qtdeclarative6 \
    \
    qml-box2d6 \
    \
    phonon \
"

RDEPENDS:${PN} += " \
    qtdeclarative6-qmlplugins \
    qtmultimedia6-qmlplugins \
"

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
    ki18n \
"

EXTRA_OECMAKE += "-DQML_BOX2D_MODULE=system -DQML_BOX2D_LIBRARY=${STAGING_DIR_TARGET}/usr/lib/Box2D/libqmlbox2d.so"

FILES:${PN} += " \
    ${datadir}/gcompris-qt/rcc \
    ${datadir}/gcompris-qt/translations \
"
