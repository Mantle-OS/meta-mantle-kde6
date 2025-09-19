# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Advanced plugin and service introspection"
HOMEPAGE = "https://api.kde.org/frameworks/kservice/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"

inherit kf6

DEPENDS:append = " \
    kdbusaddons6 \
"

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    ki18n \
    kdoctools \
"
