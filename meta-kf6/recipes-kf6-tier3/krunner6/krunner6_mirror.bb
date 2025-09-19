# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018-2019 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
#
SUMMARY = "Parallelized query system"
HOMEPAGE = "https://api.kde.org/frameworks/krunner/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.0-or-later & LGPL-2.1-only"

inherit kf6

DEPENDS:append = " \
    kitemmodels6 \
    kwindowsystem6 \
"

PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    ki18n \
"
