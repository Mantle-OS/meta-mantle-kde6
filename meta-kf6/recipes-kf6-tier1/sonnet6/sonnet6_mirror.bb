# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020-2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Hardware integration and detection"
HOMEPAGE = "https://api.kde.org/frameworks/solid/html/index.html"
LICENSE = "BSD-3-Clause & LGPL-2.0-or-later & LGPL-2.1-or-later"

inherit kf6

# for parsetrigrams6
DEPENDS:append:class-target = " \
    ${PN}-native \
    aspell \
    hunspell hunspell-dictionaries \
    hspell \
"

PACKAGECONFIG[widgets] = "-DSONNET_USE_WIDGETS=ON, -DSONNET_USE_WIDGETS=OFF, "
PACKAGECONFIG[qml] = "-DSONNET_USE_QML=ON, -DSONNET_USE_QML=OFF, qtdeclarative6"
PACKAGECONFIG[no-backend] = "-DSONNET_NO_BACKENDS=ON, -DSONNET_NO_BACKENDS=OFF, "

PACKAGECONFIG:append = " widgets qml"

PACKAGECONFIG:remove:class-native = "widgets qml"
PACKAGECONFIG:append:class-native = " no-backend"

EXTRA_OECMAKE:append:class-target = " -DPARSETRIGRAMS_EXECUTABLE=${STAGING_DIR_NATIVE}${bindir}/parsetrigrams6"
BBCLASSEXTEND = "native nativesdk"
