# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

SUMMARY = "Qt wrapper for NetworkManager API"
HOMEPAGE = "https://api.kde.org/frameworks/networkmanager-qt/html/index.html"
SECTION = "libs"
LICENSE = "BSD-3-Clause AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"
inherit kf6
DEPENDS += " \
    qtbase6 \
    qtdeclarative6 \
    networkmanager \
    glib-2.0 \
"

RDEPENDS:${PN} += " networkmanager"

