# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Syntax Highlighting"
HOMEPAGE = "https://api.kde.org/frameworks/syntax-highlighting/html/index.html"
SECTION = "libs"
LICENSE = "MIT & LGPL-2.0-or-later"
REUSE_LICENSECHECK_WHITELIST = "LGPL-2.1-or-later GPL-2.0-only"

inherit kf6 perlnative

DEPENDS:append:class-target = " ${BPN}-native"
DEPENDS:append:class-nativesdk = " ${BPN}-native"

DEPENDS:append = " \
    xerces-c \
    qtdeclarative6 \
"

PACKAGECONFIG[gui] = "-DKSYNTAXHIGHLIGHTING_USE_GUI=ON, -DKSYNTAXHIGHLIGHTING_USE_GUI=OFF, "
PACKAGECONFIG[qrc] = "-DQRC_SYNTAX=ON, -DQRC_SYNTAX=OFF, "
PACKAGECONFIG[no-standardpaths] = "-DNO_STANDARD_PATHS=ON, -DNO_STANDARD_PATHS=OFF, "

PACKAGECONFIG:append = " gui qrc"
PACKAGECONFIG:remove:class-native = " gui"

EXTRA_OECMAKE:class-target = " -DKATEHIGHLIGHTINGINDEXER_EXECUTABLE=${STAGING_DIR_NATIVE}${bindir}/katehighlightingindexer"

sysroot_stage_all:append:class-native () {
    install -d ${SYSROOT_DESTDIR}/${bindir}
    cp ${B}/bin/katehighlightingindexer ${SYSROOT_DESTDIR}/${bindir}
}
