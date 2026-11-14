# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Kde Qt wrapper for ModemManager API"
HOMEPAGE = "https://api.kde.org/frameworks/modemmanager-qt/html/index.html"
SECTION = "libs"
LICENSE = "BSD-3-Clause AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"

inherit kf6 features_check
REQUIRED_DISTRO_FEATURES = "3g"

DEPENDS:append = " modemmanager"


RDEPENDS:${PN} += " modemmanager"
