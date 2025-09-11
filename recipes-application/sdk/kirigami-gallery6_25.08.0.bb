# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2019-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Kirigami Gallery"
HOMEPAGE = "https://www.kde.org/products/kirigami/"
LICENSE = "LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL-2;md5=5f30f0716dfdd0d91eb439ebec522ec2"

inherit cmake_kdeapp

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/kirigami-gallery-25.08.0.tar.xz"
SRC_URI[sha256sum] = "ba89fb934ded45afbd2ed7b4a5b7aff2d2c80eca931168417d6e04951a83c1aa"

DEPENDS += " \
    kirigami6 \
    kitemmodels6 \
"

