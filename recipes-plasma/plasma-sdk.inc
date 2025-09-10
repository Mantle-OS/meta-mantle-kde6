# SPDX-FileCopyrightText: 2024 Marc Ferland <marc.ferland@sonatest.com>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Plasma-SDK"
HOMEPAGE = ""
LICENSE = "BSD-2-Clause & GPL-2.0-only"
LIC_FILES_CHKSUM = " \
    file://LICENSES/BSD-2-Clause.txt;md5=63d6ee386b8aaba70b1bf15a79ca50f2 \
    file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977 \
    file://LICENSES/GPL-2.0-or-later.txt;md5=fed54355545ffd980b814dab4a3b312c \
    file://LICENSES/LGPL-2.0-only.txt;md5=6d2d9952d88b50a51a5c73dc431d06c7 \
    file://LICENSES/LGPL-2.0-or-later.txt;md5=6d2d9952d88b50a51a5c73dc431d06c7 \
"
PR = "r0"

DEPENDS += " \
    kcoreaddons6 \
    karchive6 \
    kcompletion \
    kconfig6 \
    kconfigwidgets6 \
    kdbusaddons6 \
    ki18n6 \
    kiconthemes6 \
    kitemmodels6 \
    kio6 \
    ktexteditor6 \
    kwidgetsaddons6 \
    ksvg6 \
    libplasma6 \
    plasma5support6 \
"

RDEPENDS:${PN} += " \
    kirigami6 \
"

inherit cmake_plasma
inherit kf6-kdoctools

PACKAGES =+ "${PN}-zsh-completions"

FILES:${PN} += " \
    ${datadir}/kpackage \
    ${datadir}/plasma/shells \
"

FILES:${PN}-zsh-completions += " \
    ${datadir}/zsh \
"
