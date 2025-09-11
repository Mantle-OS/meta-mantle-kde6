# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "KMime"
DESCRIPTION = "Library to assist handling MIME data"
HOMEPAGE = "https://invent.kde.org/pim/kmime"
LICENSE = "MIT & BSD-3-Clause & LGPL-2.0-only"
LIC_FILES_CHKSUM = " \
    file://LICENSES/LGPL-2.0-only.txt;md5=6d2d9952d88b50a51a5c73dc431d06c7 \
"

inherit cmake_kdeapp

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/kmime-25.08.0.tar.xz"
SRC_URI[sha256sum] = "846e6ecd519d99a282f5079c9c153fbbc3d9e6f8698e5a9b31f8dccfe988d1f3"

DEPENDS += " \
    qtbase6 \
    kcodecs6 \
"

