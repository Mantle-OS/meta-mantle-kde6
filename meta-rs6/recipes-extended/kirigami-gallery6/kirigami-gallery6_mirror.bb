# SPDX-FileCopyrightText: 2019-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Kirigami Gallery"
HOMEPAGE = "https://www.kde.org/products/kirigami/"
SECTION = "graphics"
LICENSE = "LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL-2;md5=5f30f0716dfdd0d91eb439ebec522ec2"

inherit rs6

DEPENDS:append = " \
    qtsvg6 \
    qtdeclarative6 \
    \
    kirigami6 \
    kitemmodels6 \
"

PACKAGECONFIG[qml-profile] = "-DENABLE_QML_PROFILING=ON, -DENABLE_QML_PROFILING=OFF, "
PACKAGECONFIG:append = " \
    qml-profile \
    kconfig \
    ki18n \
"

FILES:${PN} += " \
    ${datadir}/metainfo \
"
