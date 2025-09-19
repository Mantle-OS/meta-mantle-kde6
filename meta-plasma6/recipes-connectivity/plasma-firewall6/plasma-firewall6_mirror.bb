# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Plasma Firewall"
SUMMARY = "Control Panel for your system firewall"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-firewall"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=93e64b4a83c7e441e48bbdeeea05c977"

inherit plasma6

DEPENDS:append = " \
    kconfig6 \
    kcoreaddons6 \
    kcmutils6 \
    ki18n6 \
    kauth6 \
    qtbase6 \
    qtdeclarative6 \
"

RDEPENDS:${PN} += " \
    firewalld \
"

## FIXME this is crazy "kde this way or no way" lol
EXTRA_OECMAKE += " -DBUILD_UFW_BACKEND=OFF"

INSANE_SKIP:${PN} = "dev-so"
FILES:${PN} += " \
    ${libdir}/libkcm_firewall_core.so \
"
FILES_SOLIBSDEV = ""
