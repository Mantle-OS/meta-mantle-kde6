# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018-2019 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Support for downloading application assets from the network"
HOMEPAGE = "https://api.kde.org/frameworks/knewstuff/html/index.html"
SECTION = "libs"
REUSE_LICENSECHECK_WHITELIST = "CC0-1.0"
LICENSE = "LGPL-2.1-only"

inherit kf6

DEPENDS:append = " \
    qtdeclarative6 \
    \
    karchive6 \
    kpackage6 kpackage6-native \ 
    kwidgetsaddons6 \
    attica6 \
    kirigami6 \
    syndication6 \
"

PACKAGECONFIG:append = " \
    kauth \
    kcoreaddons \
    kconfig  \
    ki18n \
"

EXTRA_OECMAKE:append = " -DBUILD_DESIGNERPLUGIN=OFF"
