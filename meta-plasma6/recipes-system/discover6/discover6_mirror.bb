# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Discover"
HOMEPAGE = ""
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"

inherit plasma6


#FIXME libmarkdown 

DEPENDS:append = " \
    qtdeclarative6 \
    qtwebview6 \
    \
    kirigami6 kirigami6-addons \
    kcrash6 \
    kdbusaddons6 \
    karchive6 \
    kxmlgui6 \
    kio6 \
    kcmutils6 kcmutils6-native \
    kidletime6 \
    knotifications6 \
    purpose6 \
    kstatusnotifieritem6 \
    kiconthemes6 \
    kuserfeedback6 \    
    \
    packagekit-qt6 \
    appstream \
    attica6 \
    knewstuff6 \
    flatpak \
    fwupd \
    ostree \
    qcoro6 \
    kitemmodels6 \
"

PACKAGECONFIG[notify] = "-DWITH_NOTIFIER=ON, -DWITH_NOTIFIER=OFF, "
PACKAGECONFIG:append = " \
    notify \
"

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/kxmlgui6 \
    ${datadir}/knotifications6 \
    ${datadir}/libdiscover \
    ${libdir}/plasma-discover/libDiscoverCommon.so \
    ${libdir}/plasma-discover/libDiscoverNotifiers.so \
"

