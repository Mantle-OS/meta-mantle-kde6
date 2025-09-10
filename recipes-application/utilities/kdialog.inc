# SPDX-FileCopyrightText: 2019-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "KDialog"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881"
PR = "r0"

DEPENDS += " \
    ktextwidgets6 \
    kio6 \
    knotifications \
    kguiaddons6 \
    kiconthemes6 \
    kwindowsystem6 \
    kdbusaddons\
"

inherit cmake_kdeapp
inherit kf6-kcoreaddons
inherit kf6-kconfig
inherit kf6-kauth
