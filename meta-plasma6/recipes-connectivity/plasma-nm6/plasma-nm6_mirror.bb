# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Plasma NM"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-nm"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=9e2385fe012386d34dcc5c9863070881"

inherit plasma6

SRC_URI:append = " \
    file://0001-so-version-to-editor-and-internal.patch \
"

DEPENDS:append = " \
    qtdeclarative6 \
    \
    kcolorscheme6 \
    kcompletion6 \
    kdbusaddons6 \
    kio6 \
    networkmanager-qt6 \
    knotifications6 \
    kservice6 \
    solid6 \
    kwallet6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
    kcmutils6 kcmutils6-native \
    ksvg6 \
    modemmanager-qt6 \
    \
    libplasma6 \
    kirigami6 \
    qca-kf6 \
    prison6 \
    \
    networkmanager \
    mobile-broadband-provider-info \
    \
    qcoro6 \
    kconfigwidgets6 \
    kiconthemes6 \
    knotifications6 \
    kservice6 \
    ksvg6 \
    kwallet6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
    modemmanager-qt6 \
    prison6 \
    solid6 \
    \
    libplasma6 \
    \
    qca-kf6 \
    qcoro6 \
"

PACKAGECONFIG[open-connect] = "-DBUILD_OPENCONNECT=ON, -DBUILD_OPENCONNECT=OFF, networkmanager-openconnect openconnect qtwebengine6"
# FIXME This is nowhere in the code that I can find
PACKAGECONFIG[mobile] = "-DBUILD_MOBILE=ON, -DBUILD_MOBILE=OFF, "
PACKAGECONFIG:append = " \
    open-connect \
"

EXTRA_OECMAKE += " \
    -DOE_QCA_PATH_HOST_ROOT=${STAGING_DIR_HOST} \
"

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/kcm_networkmanagement \
    ${datadir}/knotifications6 \
    ${datadir}/plasma \
"
