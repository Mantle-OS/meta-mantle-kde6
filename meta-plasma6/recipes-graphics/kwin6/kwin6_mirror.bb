# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020-2022 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "KWin"
HOMEPAGE = ""
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"

inherit plasma6

DEPENDS:class-target = " \
    kcmutils6 \
    breeze6 \
    kcompletion6 \
    kconfigwidgets6 \
    kcrash6 \
    kdeclarative6 \
    kdecoration6 \
    kglobalaccel6 \
    kglobalacceld6 \
    kidletime6 \
    knewstuff6 \
    knotifications6 \
    kpackage6 kpackage6-native \
    krunner6 \
    kscreenlocker6 \
    kservice6 \
    ktextwidgets6 \
    kwayland6 \
    kwin6-native \
    kwindowsystem6 \
    kxmlgui6 \
    libplasma6 \
    libdisplay-info6 \
    \
    fontconfig \
    hwdata \
    lcms \
    libinput \
    libxcvt \
    pipewire \
    wayland \
    wayland-protocols \
    libxcb xcb-util-cursor \
    \
    qtdeclarative6 \
    qtsensors6 \
    qtwayland6-native \
"

DEPENDS:class-native = " \
    qtbase6-native \
    ecm-kf6-native \
"

SRC_URI:append = " \
    file://kwinrc \
"

SRC_URI:append:class-native = " \
    file://0001-Add-install-operation-for-native-build.patch \
"

OECMAKE_SOURCEPATH:class-native = "${S}/src/wayland/tools"
EXTRA_OECMAKE:append:class-target = " \
    -DQTWAYLANDSCANNER_KDE_EXECUTABLE=${STAGING_DIR_NATIVE}/${bindir}/qtwaylandscanner_kde \
    -DBUILD_TESTING=OFF \
"


PACKAGECONFIG:append = " \
    kauth \
    kconfig \
    kdoctools \
"
# TODO skip inherit of cmake_plasma for now because it leads to build errors for native package
do_install:append() {
    # dirty workaround for aurora crash on Wayland
    rm -rf ${D}/${libdir}/plugins/org.kde.kdecoration2/

    install -d ${D}/${sysconfdir}/xdg
    install -m 644 ${WORKDIR}/kwinrc ${D}/${sysconfdir}/xdg/kwinrc
}

FILES_SOLIBSDEV = ""

RDEPENDS:${PN}:append:class-target = " \
    qtwayland6-plugins \
    xwayland \
    perl \
"

RRECOMMENDS:${PN}:append:class-target = " \
    maliit-keyboard-qt6 \
"

BBCLASSEXTEND = "native"
