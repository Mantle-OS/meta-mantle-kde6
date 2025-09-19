# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2014 Johan Thelin <johan.thelin@pelagicore.com>
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020-2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Abstraction to system policy and authentication features"
HOMEPAGE = "https://api.kde.org/frameworks/kauth/html/index.html"
LICENSE = "LGPL-2.0-or-later & LGPL-2.1-or-later & BSD-3-Clause"

inherit kf6

SRC_URI:append = " file://0001-Fix-build-when-KAUTH_BUILD_CODEGENERATOR_ONLY-ON.patch"

DEPENDS:append = " \
    kcoreaddons6 \
    kwindowsystem6 \
"

DEPENDS:append:class-target = " ${PN}-native polkit-qt6-1"
DEPENDS:append:class-nativesdk = " ${PN}-native"

PACKAGECONFIG[codegen-only] = "-DKAUTH_BUILD_CODEGENERATOR_ONLY=ON, KAUTH_BUILD_CODEGENERATOR_ONLY=OFF, "
PACKAGECONFIG:append:class-native  = " codegen-only"
PACKAGECONFIG:append:class-nativesdk  = " codegen-only"

EXTRA_OECMAKE:append = " -DKAUTH_BACKEND_NAME=POLKITQT6-1"
EXTRA_OECMAKE:append:class-native = " \
    -DKAUTH_POLICY_FILES_INSTALL_DIR=${datadir}/polkit-1/actions \
"

