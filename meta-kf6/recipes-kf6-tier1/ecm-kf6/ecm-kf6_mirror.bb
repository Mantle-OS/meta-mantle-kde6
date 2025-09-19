# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Extra CMake modules"
HOMEPAGE = "https://api.kde.org/frameworks/extra-cmake-modules/html/index.html"
LICENSE = "BSD-2-Clause & BSD-3-Clause & MIT"

inherit qt6-cmake kde6-licenses kf6-checksums

SRC_URI = "${KDE6_MIRROR}/stable/frameworks/${KDE6_KF6_VERSION_DIR}/extra-cmake-modules-${KDE6_KF6_VERSION}.tar.xz;name=ecm-kf6"
S = "${WORKDIR}/extra-cmake-modules-${KDE6_KF6_VERSION}"
PV = "${KDE6_KF6_VERSION}"

DEPENDS:append = " cmake-native qtdeclarative6 qtbase6 " 

RDEPENDS:${PN} += " python3-core"

CLEANBROKEN = "1"

PACKAGECONFIG:append = " docs"
PACKAGECONFIG[docs] = "-DBUILD_DOC=ON, -DBUILD_DOC=OFF, qttools6 python3-sphinx-native"

FILES:${PN} += " \
    ${datadir}/ECM \
"

EXTRA_OECMAKE += " \
    -DBUILD_WITH_QT6=ON \
    -DQT_MAJOR_VERSION=6 \
"


# EXTRA_OECMAKE:class-target += " -DQT_HOST_PATH=${STAGGING_NATIVE_DIR}/DONES/THIUS........"

BBCLASSEXTEND = "native nativesdk"
