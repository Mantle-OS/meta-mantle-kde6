# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Barcode abstraction layer providing uniform access to generation of barcodes"
HOMEPAGE = "https://api.kde.org/frameworks/prison/html/index.html"
LICENSE = "BSD-3-Clause AND MIT"

inherit kf6
REUSE_LICENSECHECK_WHITELIST = "CC0-1.0"

DEPENDS:append = " \
    qrencode \
    libdmtx-kf6 \
    zxing-cpp-kf6 \
"

PACKAGECONFIG[qml] = "-DWITH_QUICK=ON, -DWITH_QUICK=OFF, qtdeclarative6"
PACKAGECONFIG[multimedia] = "-DWITH_MULTIMEDIA=ON,-DWITH_MULTIMEDIA=OFF , qtmultimedia6"

PACKAGECONFIG:append = " qml multimedia"
PACKAGECONFIG:remove:class-native = " qml multimedia"
BBCLASSEXTEND = "native nativesdk"
