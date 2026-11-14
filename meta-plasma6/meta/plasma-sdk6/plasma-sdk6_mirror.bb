# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Marc Ferland <marc.ferland@sonatest.com>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Plasma-SDK"
HOMEPAGE = ""
LICENSE = "BSD-2-Clause AND GPL-2.0-only"

inherit plasma6

PACKAGECONFIG:append = " \
    kdoctools \
"

DEPENDS:append = " \
    karchive6 \
    kcompletion6 \
    kconfigwidgets6 \
    kdbusaddons6 \
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

PACKAGES =+ "${PN}-zsh-completions"

FILES:${PN} += " \
    ${datadir}/kpackage \
    ${datadir}/plasma/shells \
"

FILES:${PN}-zsh-completions += " \
    ${datadir}/zsh \
"
