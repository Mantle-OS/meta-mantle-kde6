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
    kconfig \ 
    kcoreaddons \
    ki18n \
"

DEPENDS:append = " \
    qtmultimedia6 \
    \
    kcmutils6 kcmutils6-native \
    knewstuff6 \
    kdbusaddons6 \
    kbookmarks6 \
    kio6 \
    kparts6 \
    solid6 \
    kiconthemes6 \
    kcompletion6 \
    ktextwidgets6 \
    knotifications6 \
    kcrash6 \
    kwindowsystem6 \
    kcodecs6 \
    kguiaddons6 \
    kcolorscheme6 \
    \
    kuserfeedback6 \
    kdoctools6 kdoctools6-native \
    packagekit-qt6 \
    kfilemetadata6 \
    baloo6 \
    flatpak \
"

SYSTEMD_SERVICE:${PN} = "plasma-dolphin.service"
SYSTEMD_AUTO_ENABLE = "disable"

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/kconf_update \
    ${datadir}/config.kcfg \
    ${datadir}/dolphin \
    ${datadir}/zsh \
    ${datadir}/knsrcfiles \
    ${datadir}/kglobalaccel \
    ${libdir}/kconf_update_bin \
"
