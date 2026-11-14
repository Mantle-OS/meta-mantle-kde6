# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2014 Johan Thelin <johan.thelin@pelagicore.com>
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Pty abstraction"
HOMEPAGE = "https://api.kde.org/frameworks/kpty/html/index.html"
SECTION = "libs"
LICENSE = "BSD-3-Clause AND LGPL-2.0-or-later"

inherit kf6

PACKAGECONFIG[utempter] = " , -DCMAKE_DISABLE_FIND_PACKAGE_UTEMPTER=ON, libutempter"
PACKAGECONFIG:append = " \
    kcoreaddons \
    ki18n \
"

