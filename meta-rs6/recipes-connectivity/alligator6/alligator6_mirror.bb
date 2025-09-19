# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Kirigami-based RSS reader"
HOMEPAGE = "https://invent.kde.org/plasma-mobile/alligator"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-3.0-only.txt;md5=1c76c4cc354acaac30ed4d5eefea7245"

inherit rs6

PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    ki18n \
"

DEPENDS:append = " \
    qtdeclarative6 \
    syndication6 \
    kirigami6 kirigami6-addons6 \
    libplasma6 \
"
