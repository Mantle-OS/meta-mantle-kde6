# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Classes to interact with KColorScheme"
HOMEPAGE = "https://api.kde.org/frameworks/kcolorscheme/html/index.html"
LICENSE = "BSD-2-Clause & LGPL-2.0-or-later & LGPL-2.1-only & LGPL-3.0-only"

inherit kf6

DEPENDS:append = " \
    kguiaddons6 \
"

PACKAGECONFIG:append = " \
    ki18n \
    kconfig \
"

BBCLASSEXTEND = "native nativesdk"
