# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020-2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Core components for the KDE's Activities System"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-activities"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"
#  LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-only.txt;md5=fabba2a3bfeb22a6483d44e9ae824d3f"

inherit plasma6

DEPENDS:append = " \
    kwindowsystem6 \
    boost \
    qtdeclarative6 \
"

