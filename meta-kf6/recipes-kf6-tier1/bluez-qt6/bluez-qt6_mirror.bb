# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2019 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Qt wrapper for BlueZ 5 DBus API"
HOMEPAGE = "https://api.kde.org/frameworks/bluez-qt/html/index.html"
LICENSE = "LGPL-2.1-or-later & (LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL)"

inherit kf6 features_check
REQUIRED_DISTRO_FEATURES = "bluetooth"

PACKAGECONFIG:append = " qml"
PACKAGECONFIG[qml] =  " , ,qtdeclarative6"

RDEPENDS:${PN} += " \
    bluez5 \
    bluez5-obex \
"
