# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "A QtQuick module providing high-performance charts."
HOMEPAGE = "https://api.kde.org/frameworks/kquickcharts/html/index.html"
SECTION = "libs"
LICENSE = "MIT AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"

inherit kf6 features_check

REQUIRED_DISTRO_FEATURES = "opengl"

DEPENDS:append = " \
    qtdeclarative6 \
    qttools6-native \
    qtshadertools6-native \
"
