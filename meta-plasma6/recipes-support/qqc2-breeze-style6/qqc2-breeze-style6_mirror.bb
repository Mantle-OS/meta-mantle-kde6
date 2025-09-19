# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2023 Maxime Roussin-Bélanger <maxime.roussinbelanger@gmail.com>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Breeze inspired QtQuickControls 2 Style"
HOMEPAGE = "https://invent.kde.org/plasma/qqc2-breeze-style"
LICENSE = "LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-3.0-only.txt;md5=852ecadc0ac7e6f4d7144d5544a3815b"

inherit plasma6

DEPENDS = " \
    qtdeclarative6 \
    kirigami6 \
    kquickcharts6 \
"
