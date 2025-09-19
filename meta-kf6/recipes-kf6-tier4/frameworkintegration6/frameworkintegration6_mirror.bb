# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Workspace and cross-framework integration plugins"
HOMEPAGE = "https://api.kde.org/frameworks/frameworkintegration/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.0-only"

inherit kf6

DEPENDS:append = " \
    kconfigwidgets6 \
    kcolorscheme6 \
    kiconthemes6 \
    knotifications6 \
    kwidgetsaddons6 \
"

PACKAGECONFIG[kpackage] = "\
    -DBUILD_KPACKAGE_INSTALL_HANDLERS=ON, \
    -DBUILD_KPACKAGE_INSTALL_HANDLERS=OFF, \
        knewstuff6 \
        kpackage6 kpackage6-native \
        appstream \
        packagekit \
        packagekit-qt6 \
"

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
    ki18n \
    kpackage \
"

FILES:${PN} += " \
    ${datadir}/knotifications6 \
"
