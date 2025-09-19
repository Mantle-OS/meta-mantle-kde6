# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

SUMMARY = "Offers available actions for a specific purpose"
HOMEPAGE = "https://api.kde.org/frameworks/purpose/html/index.html"
LICENSE = "LGPL-2.1-or-later"

inherit kf6
inherit gettext

DEPENDS:append = " \
    qtdeclarative6 \
    \
    kio6 \
    kirigami6 \
    knotifications6 \
    kservice6 \
"

PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "
PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    ki18n \
    kauth \
    dbus \
"
