# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018-2019 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020-2022 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Advanced embeddable text editor"
HOMEPAGE = "https://api.kde.org/frameworks/ktexteditor/html/index.html"
SECTION = "libs"
LICENSE = "BSD-2-Clause AND LGPL-2.0-only AND MIT"

inherit kf6

DEPENDS:append = " \
    qtdeclarative6 \
    qtspeech6 \
    \
    karchive6 \
    kguiaddons6 \
    kio6 \
    kparts6 \
    sonnet6 \
    syntax-highlighting6 \
    kcolorscheme6 \
"
PACKAGECONFIG[pch] = "-DENABLE_PCH=ON, -DENABLE_PCH=OFF"
PACKAGECONFIG[enable-kauth] = "-DENABLE_KAUTH=ON, -DENABLE_KAUTH=OFF, "

PACKAGECONFIG:append = " \
    kcoreaddons \
    kauth enable-kauth \
    kconfig \
    ki18n \
"

FILES:${PN} += " \
    ${datadir}/polkit-1 \
"

FILES:${PN}-docs += " \
    ${datadir}/katepart5/script/README.md \
"
