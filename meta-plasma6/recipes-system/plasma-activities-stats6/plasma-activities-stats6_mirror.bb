# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "A library for accessing the usage data collected by the activities system"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-activities-stats"
LICENSE = "LGPL-2.0-or-later AND (GPL-2.0-only OR GPL-3.0-only OR LicenseRef-KDE-Accepted-GPL) AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-only.txt;md5=fabba2a3bfeb22a6483d44e9ae824d3f"

inherit plasma6

DEPENDS:append = " \
    plasma-activities6 \
    boost \
"
