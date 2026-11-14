# SPDX-FileCopyrightText: 2014 Johan Thelin <johan.thelin@pelagicore.com>
# SPDX-FileCopyrightText: 2016 Sébastien Taylor <sebastien@au-zone.com>
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Kde text tools"
LICENSE = "BSD-3-Clause AND LGPL-2.0-only AND LGPL-2.0-or-later AND MIT"
inherit kf6

DEPENDS:append = " gperf-native"

BBCLASSEXTEND = "native nativesdk"
