# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "User feedback framework"
HOMEPAGE = "https://api.kde.org/frameworks/kuserfeedback/html/index.html"
SECTION = "libs"
LICENSE = "BSD-3-Clause AND MIT"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=61be4d272e55cc2609d58596cf084908"

inherit kf6

DEPENDS:append = " \
    flex-native \
    bison-native \
    qtsvg6 \
    qtdeclarative6 \
    qtcharts6 \
    qttools6-native \
"
