# SPDX-FileCopyrightText: 2024 Marc Ferland <marc.ferland@sonatest.com>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Dolphin"
HOMEPAGE = "https://apps.kde.org/dolphin/"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=5c213a7de3f013310bd272cdb6eb7a24"

DEPENDS += " \
    kbookmarks6 \
    kcodecs6 \
    kcompletion \
    kconfig6 \
    kcrash \
    kdbusaddons6 \
    kguiaddons6 \
    ki18n6 \
    kiconthemes6 \
    kio6 \
    knewstuff6 \
    knotifications \
    kparts6 \
    ktextwidgets6 \
    kuserfeedback \
    kwindowsystem6 \
    phonon \
    solid6 \
"

inherit cmake_kdeapp
inherit kf6-kcmutils
inherit kf6-kconfig
inherit kf6-kcoreaddons
inherit mime-xdg
inherit systemd

SYSTEMD_SERVICE:${PN} = "plasma-dolphin.service"

SYSTEMD_AUTO_ENABLE = "disable"

FILES:${PN} += " \
    ${datadir}/knsrcfiles \
    ${datadir}/kglobalaccel \
    ${datadir}/zsh \
"
