# SPDX-FileCopyrightText: 2018-2019 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2023 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

inherit kf6

EXTRA_OECMAKE:append = " \
    -DQT_MAJOR_VERSION=6 \
    -DBUILD_QT5=OFF \
"

DEPENDS:append = " kpackage-native"
