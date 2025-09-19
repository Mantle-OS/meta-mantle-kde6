# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Framework for interacting with systemd-journald"
HOMEPAGE = ""
LICENSE = "MIT | LGPL-2.1-or-later"
LIC_FILES_CHKSUM = " \
    file://LICENSES/MIT.txt;md5=4dd71a82d66fd9e3ca0cc65b8be370c0 \
    file://LICENSES/LGPL-2.1-or-later.txt;md5=147a320ed8b16b036829a0c71d424153 \
"

inherit features_check
REQUIRED_DISTRO_FEATURES = "systemd"

inherit cmake_kdeapp
inherit kf6-kconfig

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/kjournald-25.08.0.tar.xz"
SRC_URI[sha256sum] = "5287b36efdc7b124c81c1c6a0523357d660b5d633b7edc139480123ac74616c6"

DEPENDS = " \
    qtbase6 \
    qtdeclarative6 \
    systemd \
    kcoreaddons6 \
    kcrash6 \
    kirigami6 \
    kirigami6-addons6 \
    kf6-kconfig6 \
"

RDEPENDS:${PN} += " \
    qtdeclarative6-qmlplugins \
"
