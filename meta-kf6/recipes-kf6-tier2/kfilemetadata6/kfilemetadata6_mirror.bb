# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2019 Hannah Kiekens <hannahkiekens@gmail.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "A file metadata and text extraction library"
HOMEPAGE = "https://api.kde.org/frameworks/kfilemetadata/html/index.html"
SECTION = "text"
LICENSE = "BSD-3-Clause AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"

inherit kf6 features_check

REQUIRED_DISTRO_FEATURES += "xattr"

DEPENDS:append = " \
    karchive6 \
    kcodecs6 \
    libkexiv2-kf6 \
    ffmpeg \
    poppler \
    taglib \
    ebook-tools \
    catdoc \
"

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    ki18n \
"
