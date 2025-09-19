# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018-2020 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Baloo is a file indexing and searching framework"
HOMEPAGE = "https://api.kde.org/frameworks/baloo/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"

inherit kf6

SRC_URI:append:riscv64 = " \
    file://0001-we-cant-mmap-such-a-large-memory-on-riscv64.patch \
"

DEPENDS:append = " \
    libxml2 \
    qtdeclarative6 \
    lmdb \
    \
    kdbusaddons6 \
    kidletime6 \
    solid6 \
    kfilemetadata6 \
    kcrash6 \
    kio6 \
"

DEPENDS:class-target += " \
    libxml2-native \
    inotify-tools \
"

PACKAGECONFIG[experimental] = "-DBUILD_EXPERIMENTAL=ON, -DBUILD_EXPERIMENTAL=OFF, "
PACKAGECONFIG[indexer] = "-DBUILD_INDEXER_SERVICE=on, -DBUILD_INDEXER_SERVICE=OFF, "

PACKAGECONFIG:append = " \
    kauth \
    kconfig \
    kcoreaddons \
    ki18n \
    indexer \
"

RDEPENDS:${PN} += " \ 
    lmdb \
"
