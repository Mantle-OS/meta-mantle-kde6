# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT
LICENSE = "LGPL-2.1-only"
SECTION = "x11"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1"
PR = "r0"

inherit kde6-ecm gtk-icon-cache

SRC_URI = "https://download.kde.org/stable/oxygen-icons/oxygen-icons-6.1.0.tar.xz"
SRC_URI[sha256sum] = "16ca971079c5067c4507cabf1b619dc87dd6b326fd5c2dd9f5d43810f2174d68"
S = "${WORKDIR}/oxygen-icons-6.1.0"

DEPENDS:append = " \
    hicolor-icon-theme \
"
