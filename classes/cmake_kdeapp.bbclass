# SPDX-FileCopyrightText: 2019 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

inherit kf6 gettext

EXTRA_OECMAKE:append = " \
    -DQT_MAJOR_VERSION=6 \
"

DEPENDS:append = " \
    gettext-native \
    kpackage-native \
"
