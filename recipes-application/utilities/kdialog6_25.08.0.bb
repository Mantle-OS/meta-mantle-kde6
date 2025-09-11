# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2019-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KDialog"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881"

inherit cmake_kdeapp
inherit kf6-kcoreaddons kf6-kconfig kf6-kauth

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/kdialog-25.08.0.tar.xz"
SRC_URI[sha256sum] = "7772cdfe2acccfda420c627a8b1f4cf35acc1906f5155fc2166ee482e10755a3"

DEPENDS += " \
    ktextwidgets6 \
    kio6 \
    knotifications6 \
    kguiaddons6 \
    kiconthemes6 \
    kwindowsystem6 \
"

