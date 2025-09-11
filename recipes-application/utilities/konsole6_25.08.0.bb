# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2019-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Konsole"
HOMEPAGE = "https://konsole.kde.org/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=5c213a7de3f013310bd272cdb6eb7a24"

inherit cmake_kdeapp
inherit kf6-kcoreaddons kf6-kconfig kf6-kauth

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/konsole-25.08.0.tar.xz"
SRC_URI[sha256sum] = "c880bd2c829437a142abaf4f97ebc384c9f2c19f31cc23918c49100727927450"

DEPENDS += " \
    kio6 \
    knewstuff6 \
    knotifications6 \
    knotifyconfig6 \
    kparts6 \
    kpty6 \
    kxmlgui6 \
"

FILES_SOLIBSDEV = ""
FILES:${PN} += " \
    ${datadir}/knsrcfiles \
    ${datadir}/kio/servicemenus \
    ${datadir}/kglobalaccel/* \
    ${datadir}/zsh/* \
"
