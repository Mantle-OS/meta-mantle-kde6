# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Integration with su for elevated privileges"
HOMEPAGE = "https://api.kde.org/frameworks/kdesu/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"

inherit kf6

DEPENDS:append = " \
    kpty6 \
"

PACKAGECONFIG[use_sudo] = "-DKDESU_USE_SUDO_DEFAULT=ON ,-DKDESU_USE_SUDO_DEFAULT=OFF , "
PACKAGECONFIG[use_doas] = "-DKDESU_USE_DOAS_DEFAULT=ON, -DKDESU_USE_DOAS_DEFAULT=OFF, "


PACKAGECONFIG[x11] = ", , virtual/libx11"
PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    ki18n \
"

PACKAGECONFIG:append:class-target = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
"
