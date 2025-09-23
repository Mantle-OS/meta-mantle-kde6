# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "KPeople VCard Backend"
DESCRIPTION = "Expose VCard contacts to KPeople"
HOMEPAGE = "https://invent.kde.org/pim/kpeoplevcard"
SECTION = "libs"
LICENSE = "LGPL-2.1-or-later & BSD-3-Clause"
LIC_FILES_CHKSUM = " \
    file://LICENSES/LGPL-2.1-or-later.txt;md5=147a320ed8b16b036829a0c71d424153 \
"

PV = "0.2"
PR = "r0"

inherit kde6-ecm

SRC_URI = "git://invent.kde.org/pim/kpeoplevcard;nobranch=1;protocol=https"
SRCREV = "13db8a52125ec8abd830b3ffcd02691880e15c38"
S = "${WORKDIR}/git"

DEPENDS:append = " \
    kpeople6 \
    kcontacts6 \
"

PACKAGECONFIG:append  = " \
    kconfig \
    kcoreaddons \
    ki18n \
"
