# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Marc Ferland <marc.ferland@sonatest.com>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Dolphin"
HOMEPAGE = "https://apps.kde.org/dolphin/"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=5c213a7de3f013310bd272cdb6eb7a24"

inherit rs6
inherit systemd

PACKAGECONFIG:append = " \
    kconfig 
    kcoreaddons \
    ki18n \
"

DEPENDS:append = " \
    kcmutils6 \
    kbookmarks6 \
    kcodecs6 \
    kcompletion6 \
    kcrash6 \
    kdbusaddons6 \
    kguiaddons6 \
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

SYSTEMD_SERVICE:${PN} = "plasma-dolphin.service"
SYSTEMD_AUTO_ENABLE = "disable"
