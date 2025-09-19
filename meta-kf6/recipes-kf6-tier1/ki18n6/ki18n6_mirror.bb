# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2014 Johan Thelin <johan.thelin@pelagicore.com>
# SPDX-FileCopyrightText: 2016 Sébastien Taylor <sebastien@au-zone.com>
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020-2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "KDE Advanced internationalization framework"
HOMEPAGE = "https://api.kde.org/frameworks/ki18n/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.0-or-later & (LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL) & BSD-3-Clause"

inherit kde6-ecm kf6-checksums
inherit gettext

DEPENDS:append:class-target = " ${PN}-native"
DEPENDS:append:class-nativesdk = " ${PN}-native"

SRC_URI = " \
    ${KDE6_MIRROR}/stable/frameworks/${KDE6_KF6_VERSION_DIR}/ki18n-${KDE6_KF6_VERSION}.tar.xz;name=${BPN} \
    file://0001-error-when-no-py-native-fix.patch \
"
S = "${WORKDIR}/ki18n-${KDE6_KF6_VERSION}"

DEPENDS:append = " \
    virtual/libintl \
    iso-codes \
"

DEPENDS:append:class-native = " python3-native"

PACKAGECONFIG[qml] = "-DBUILD_WITH_QML=ON, -DBUILD_WITH_QML=OFF, qtdeclarative6"
PACKAGECONFIG:append:class-target = " qml"

RDEPENDS:${PN} += " python3-core"
RDEPENDS:${PN}:class-target += " iso-codes"
