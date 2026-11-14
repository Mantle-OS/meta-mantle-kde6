# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

SUMMARY = "ZXing-C++"
HOMEPAGE = "https://github.com/zxing-cpp/zxing-cpp"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fa818a259cbed7ce8bc2a22d35a464fc"

inherit kde6-cmake

SRC_URI = " \
    gitsm://github.com/zxing-cpp/zxing-cpp.git;nobranch=1;protocol=https \
"
SRCREV = "d6068bcebeb8fd9f0d35a99b00d202be86a14dbe"

EXTRA_OECMAKE += " \
    -DZXING_DEPENDENCIES=LOCAL \
    -DZINT_UNINSTALL=ON \
"

DEPENDS:append = " \
    git-native \
"

## zxing-cpp
PACKAGECONFIG[writer] = "-DZXING_WRITERS=ON, ZXING_WRITERS=OFF"
PACKAGECONFIG[reader] = "-DZXING_READERS=ON, -DZXING_READERS=OFF, "
PACKAGECONFIG[bundle-zint] = "-DZXING_USE_BUNDLED_ZINT=ON, -DZXING_USE_BUNDLED_ZINT=OFF, "
PACKAGECONFIG[c-api] = "-DZXING_C_API=ON, -DZXING_C_API=OFF, libstb"
PACKAGECONFIG[experimental] = "-DZXING_EXPERIMENTAL_API=ON, -DZXING_EXPERIMENTAL_API=OFF, "
PACKAGECONFIG[examples] = "-DZXING_EXAMPLES=ON, -DZXING_EXAMPLES=OFF, qtmultimedia6 opencv"
PACKAGECONFIG[blackbox-tests] = "-DZXING_BLACKBOX_TESTS=ON, -DZXING_BLACKBOX_TESTS=OFF, "
PACKAGECONFIG[unit-tests] = "-DZXING_UNIT_TESTS=ON, -DZXING_UNIT_TESTS=OFF"
PACKAGECONFIG[py-bindings] = "-DZXING_PYTHON_MODULE=ON, -DZXING_PYTHON_MODULE=OFF, python3-native"

#zint 
PACKAGECONFIG[zint-png] = "-DZINT_USE_PNG=ON, -DZINT_USE_PNG=OFF, libpng"
PACKAGECONFIG[zint-qt] = "-DZINT_USE_QT=ON, -DZINT_USE_QT=OFF, " 
PACKAGECONFIG[zint-qt5] = "-DZINT_QT6=OFF, , "
PACKAGECONFIG[zint-qt6] = "-DZINT_QT6=ON, , qtsvg6"
PACKAGECONFIG[zint-shared] = "-DZINT_SHARED=ON -DZINT_STATIC=OFF, -DZINT_SHARED=OFF -DZINT_STATIC=ON, "
PACKAGECONFIG[zint-tests] = "-DZINT_TEST=ON, -DZINT_TEST=OFF, "
PACKAGECONFIG[zint-frontend] = "-DZINT_FRONTEND=ON, -DZINT_FRONTEND=OFF, "

PACKAGECONFIG:append = " \
    writer reader bundle-zint c-api \
    zint-png zint-qt zint-qt6 zint-shared zint-frontend \
"
