# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "KScreen"
HOMEPAGE = ""
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=9e2385fe012386d34dcc5c9863070881"
PR = "r0"

inherit cmake_plasma
inherit kf6-kcmutils

SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/kscreen-6.4.4.tar.xz"
SRC_URI[sha256sum] = "5890d24614ac6f93cc97964f9ca8b1bef95ec14508408dc87f3904d03b3af876"
DEPENDS = " \
    kconfigwidgets6 \
    kdbusaddons6 \
    kglobalaccel6 \
    kwidgetsaddons6 \
    kxmlgui6 \
    layer-shell-qt6-kde6 \
    libkscreen6 \
    libplasma6 \
    qtdeclarative6 \
    qtsensors6 \
    qtwayland6-native \
"


FILES:${PN} += " \
    ${datadir}/kded_kscreen \
    ${datadir}/kcm_kscreen \
    ${datadir}/kpackage \
    ${datadir}/kglobalaccel \
    ${datadir}/plasma/plasmoids/org.kde.kscreen \
    ${libdir}/plugins/plasma/kcms/systemsettings/ \
"
