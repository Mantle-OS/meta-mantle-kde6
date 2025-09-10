# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Plasma NM"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-nm"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=9e2385fe012386d34dcc5c9863070881"
PR = "r0"

DEPENDS = " \
    qtdeclarative6 \
    qttools6 \
    \
    kcompletion \
    kconfigwidgets6 \
    kcoreaddons6 \
    kdbusaddons6 \
    kiconthemes6 \
    kio6 \
    knotifications \
    kservice6 \
    ksvg6 \
    kwallet6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
    modemmanager-qt6 \
    networkmanager-qt6 \
    prison \
    solid6 \
    \
    libplasma6 \
    \
    qca \
    qcoro6 \
"

inherit cmake_plasma
inherit mime-xdg

EXTRA_OECMAKE += " \
    -DOE_QCA_PATH_HOST_ROOT=${STAGING_DIR_HOST} \
    -DBUILD_MOBILE=ON \
"

FILES_SOLIBSDEV = ""
FILES:${PN} += " \
    ${libdir}/*.so \
    ${libdir}/plugins/plasma/network/vpn/*.so \
    ${libdir}/plugins/plasma/kcms/systemsettings_qwidgets/kcm_networkmanagement.so \
    ${libdir}/qml/org/kde/plasma/networkmanagement \
    ${datadir}/plasma/plasmoids/org.kde.plasma.networkmanagement \
    ${datadir}/kcm_networkmanagement/qml/*.qml \
    ${datadir}/kpackage \
"
