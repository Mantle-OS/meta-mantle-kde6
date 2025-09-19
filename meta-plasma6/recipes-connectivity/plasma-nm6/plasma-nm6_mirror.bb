# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Plasma NM"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-nm"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=9e2385fe012386d34dcc5c9863070881"

inherit plasma6

DEPENDS:append = " \
    qtdeclarative6 \
    qttools6 \
    \
    kcompletion6 \
    kconfigwidgets6 \
    kcoreaddons6 \
    kdbusaddons6 \
    kiconthemes6 \
    kio6 \
    knotifications6 \
    kservice6 \
    ksvg6 \
    kwallet6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
    modemmanager-qt6 \
    networkmanager-qt6 \
    prison6 \
    solid6 \
    \
    libplasma6 \
    \
    qca-kf6 \
    qcoro6 \
"

## FIXME
EXTRA_OECMAKE += " \
    -DOE_QCA_PATH_HOST_ROOT=${STAGING_DIR_HOST} \
    -DBUILD_MOBILE=ON \
"
