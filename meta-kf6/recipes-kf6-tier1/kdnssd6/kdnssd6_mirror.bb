# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2014 Johan Thelin <johan.thelin@pelagicore.com>
# SPDX-FileCopyrightText: 2016 Sébastien Taylor <sebastien@au-zone.com>
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Abstraction to system DNSSD features"
HOMEPAGE = "https://api.kde.org/frameworks/kdnssd/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.0-or-later & BSD-3-Clause"

inherit kf6 features_check
REQUIRED_DISTRO_FEATURES += " zeroconf"

DEPENDS += " avahi"
