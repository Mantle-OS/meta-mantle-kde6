# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Text completion helpers and widgets"
HOMEPAGE = "https://api.kde.org/frameworks/kcompletion/html/index.html"
LICENSE = "BSD-3-Clause & LGPL-2.0-or-later & LGPL-2.1-or-later"

inherit kf6

DEPENDS:append = " \
    kwidgetsaddons6 \
    kcodecs6 \
"

PACKAGECONFIG:append = " \
    kconfig \
"

BBCLASSEXTEND = "native nativesdk"
