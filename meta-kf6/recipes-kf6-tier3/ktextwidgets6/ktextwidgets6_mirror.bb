# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Advanced text editing widgets"
HOMEPAGE = "https://api.kde.org/frameworks/ktextwidgets/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.1-or-later & (LGPL-2.0-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL)"

inherit kf6

DEPENDS:append = " \
    kcompletion6 \
    kconfigwidgets6 \
    kservice6 \
    kwidgetsaddons6 \
    sonnet6 \
"

PACKAGECONFIG[text-to-speech] = "-DWITH_TEXT_TO_SPEECH=ON, -DWITH_TEXT_TO_SPEECH=OFF, qtspeech6"
PACKAGECONFIG:append = " \
    kcoreaddons \
    kauth \
    kconfig \
    ki18n \
    text-to-speech \
"

