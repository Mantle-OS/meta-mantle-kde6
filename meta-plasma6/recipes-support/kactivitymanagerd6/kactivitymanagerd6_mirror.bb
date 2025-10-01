# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2019 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KActivity Manager Daemon"
HOMEPAGE = ""
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"

inherit plasma6

SRC_URI:append = " \
    file://0001-add-so-version-and-install-path-plugin.patch \
"

DEPENDS:append = " \
    boost \
    \
    kdbusaddons6 \
    kcrash6 \
    kio6 \
    \
    kxmlgui6 \
    kglobalaccel6 \
"

EXTRA_OECMAKE:append = " -DPROJECT_VERSION=${PV} "

FILES:${PN} += " \
   ${datadir}/krunner \
"

RDEPENDS:${PN} += " \
    bash \
"
