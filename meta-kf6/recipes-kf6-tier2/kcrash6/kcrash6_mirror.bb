# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2014 Johan Thelin <johan.thelin@pelagicore.com>
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Support for application crash analysis and bug report from apps"
HOMEPAGE = "https://api.kde.org/frameworks/kcrash/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.0-or-later"
REUSE_LICENSECHECK_WHITELIST = "CC0-1.0"

inherit kf6

DEPENDS:append = " kwindowsystem6"

PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, virtual/libx11"

# "Enables raising signals to kernel core patterns (iff the pattern is a process). 
# You may wish to not install drkonqi if this can cause a UI conflict."
PACKAGECONFIG[pattern_raise] = "-DKCRASH_CORE_PATTERN_RAISE=ON, -DKCRASH_CORE_PATTERN_RAISE=OFF, "

PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, virtual/libx11"
PACKAGECONFIG:append = " \
    kcoreaddons \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
"
