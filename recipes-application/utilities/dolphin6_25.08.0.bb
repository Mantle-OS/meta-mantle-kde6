# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Marc Ferland <marc.ferland@sonatest.com>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Dolphin"
HOMEPAGE = "https://apps.kde.org/dolphin/"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=5c213a7de3f013310bd272cdb6eb7a24"

inherit cmake_kdeapp
inherit kf6-kcmutils kf6-kconfig kf6-kcoreaddons

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/dolphin-25.08.0.tar.xz"
SRC_URI[sha256sum] = "ddd5e5622594792490695999188c6215c3a0821787c71dfb57dbfdd08e1ce935"

DEPENDS += " \
    kbookmarks6 \
    kcodecs6 \
    kcompletion6 \
    kconfig6 \
    kcrash6 \
    kdbusaddons6 \
    kguiaddons6 \
    ki18n6 \
    kiconthemes6 \
    kio6 \
    knewstuff6 \
    knotifications6 \
    kparts6 \
    ktextwidgets6 \
    kuserfeedback6 \
    kwindowsystem6 \
    phonon \
    solid6 \
"

FILES:${PN} += " \
    ${datadir}/knsrcfiles \
    ${datadir}/kglobalaccel \
    ${datadir}/zsh \
"

## FIXME add sys5 as well
inherit systemd
SYSTEMD_SERVICE:${PN} = "plasma-dolphin.service"
SYSTEMD_AUTO_ENABLE = "disable"
