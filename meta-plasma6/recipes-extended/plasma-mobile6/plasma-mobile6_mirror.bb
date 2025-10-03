# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2019 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020-2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Plasma shell for mobile devices"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-mobile"
LICENSE = "(LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL) & (GPL-2.0-only | GPL-3.0-only | LicenseRef-KDE-Accepted-GPL)"

inherit plasma6

# PlasmaOpenSettings
DEPENDS:append = " \
    qtsensors6 \
    qtdeclarative6 \
    qtwayland6 qtwayland6-native \
    \
    kglobalaccel6 \
    kio6 \
    kdbusaddons6 \
    kitemmodels6 \
    kservice6 \
    knotifications6 \
    modemmanager-qt6 \
    networkmanager-qt6 \
    kcmutils6 kcmutils6-native \
    kpackage6 \
    kjobwidgets6 \
    \
    libplasma6 \
    plasma-activities6 \
    kscreen6 \
    kwayland6 \
    kpipewire6 \
    qcoro6 \
    gobject-introspection libgudev \
    kirigami6-addons \
    libepoxy \
    libxcb \
    kwin6 \
    layer-shell-qt6 \
    plasma-workspace6 \
"

RDEPENDS:${PN} += " \
    breeze6 \
    kded6 \
    kirigami6 \
    kirigami6-addons \
    plasma-nano6 \
    qqc2-breeze-style6 \
    kde6-cli-tools-kde6 \
"

do_compile:append() {
    sed -i -e 's,${S}/components/mobileshellstate/,,g' ${B}/components/mobileshellstate/mobileadaptor.h
}

FILES:${PN} += " \
  ${datadir}/kwin \
  ${datadir}/metainfo \
  ${datadir}/knotifications6 \
  ${datadir}/wayland-sessions \
  ${datadir}/plasma \
  ${datadir}/plasma-mobile-apn-info \
  ${datadir}/kwin/effects \
  ${datadir}/kwin/scripts \
"
