# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018-2019 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Document centric plugin system"
HOMEPAGE = "https://api.kde.org/frameworks/kparts/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.0-or-later AND LGPL-2.1-or-later"

inherit kf6

DEPENDS:append = " \
    kio6 \
    kjobwidgets6 \
    kservice6 \
    kwidgetsaddons6 \
    kxmlgui6 \
"

PACKAGECONFIG:append = " \
    kauth \
    kcoreaddons \
    kconfig \
    ki18n  \
"
