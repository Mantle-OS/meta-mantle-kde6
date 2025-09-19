# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2019-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Konsole"
HOMEPAGE = "https://konsole.kde.org/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=5c213a7de3f013310bd272cdb6eb7a24"

inherit rs6

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
"

DEPENDS:append = " \
    kio6 \
    knewstuff6 \
    knotifications6 \
    knotifyconfig6 \
    kparts6 \
    kpty6 \
    kxmlgui6 \
"

FILES_SOLIBSDEV = ""
