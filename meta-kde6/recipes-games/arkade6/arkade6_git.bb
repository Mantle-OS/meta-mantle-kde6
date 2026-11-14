# SPDX-FileCopyrightText: Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Collection of Arcade games developed in Kirigami"
HOMEPAGE = ""
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-3.0-or-later.txt;md5=1c76c4cc354acaac30ed4d5eefea7245"

inherit kde6-ecm

SRC_URI = "git://invent.kde.org/games/arkade;nobranch=1;protocol=https"
SRCREV = "8b127277206d3c7b1d25cb19ec0dbe3581063318"

DEPENDS:append = " \
    kdeclarative6 \
    kiconthemes6 \
    kirigami6 \
    \
    kpackage6-native \
    ki18n6 ki18n6-native \
    \
    qtsvg6 \
    qtdeclarative6 \
"

RDEPENDS:${PN} += " \
    qtdeclarative6-qmlplugins \
"

FILES:${PN} += "\
    ${datadir}/game/packages/org.kde.arkade.fourinline \
    ${datadir}/game/packages/org.kde.arkade.samegame \
    ${datadir}/game/packages/org.kde.arkade.blocktennis \
    ${datadir}/metainfo \
"

EXTRA_OECMAKE:append:class-target = " -DKF6_HOST_TOOLING=${STAGING_LIBDIR_NATIVE}/cmake"
EXTRA_OECMAKE:append:class-nativesdk = " -DKF6_HOST_TOOLING=${STAGING_LIBDIR_NATIVE}/cmake"
