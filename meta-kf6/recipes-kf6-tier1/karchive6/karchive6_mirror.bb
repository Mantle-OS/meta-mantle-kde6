# SPDX-FileCopyrightText: 2014 Johan Thelin <johan.thelin@pelagicore.com>
# SPDX-FileCopyrightText: 2014 Erik Botö <erik.boto@pelagicore.com>
# SPDX-FileCopyrightText: 2016 Sébastien Taylor <sebastien@au-zone.com>
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "File compression"
HOMEPAGE = "https://api.kde.org/frameworks/karchive/html/index.html"
LICENSE = "BSD-2-Clause AND LGPL-2.0-or-later"

inherit kf6
REUSE_LICENSECHECK_WHITELIST = "LGPL-2.0 LGPL-3.0 LicenseRef-KDE-Accepted-LGPL"

PACKAGECONFIG[bzip] = "-DWITH_BZIP2=ON,-DWITH_BZIP2=OFF, bzip2"
PACKAGECONFIG[lzma] = "-DWITH_LIBLZMA=ON,-DWITH_LIBLZMA=OFF, xz"
PACKAGECONFIG[openssl] = "-DWITH_OPENSSL=ON, -DWITH_OPENSSL=OFF, openssl"
PACKAGECONFIG[zstd] = "-DWITH_LIBZSTD=ON,-DWITH_LIBZSTD=OFF, zstd"
PACKAGECONFIG:append = " \
    bzip \
    lzma \
    openssl \
    zstd \
"

BBCLASSEXTEND = "native nativesdk"
