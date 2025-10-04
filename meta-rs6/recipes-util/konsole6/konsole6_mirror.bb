# SPDX-FileCopyrightText: 2019-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Konsole"
HOMEPAGE = "https://konsole.kde.org/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=5c213a7de3f013310bd272cdb6eb7a24"

inherit rs6

DEPENDS:append = " \
    qtmultimedia6 \
    \
    kbookmarks6 \
    kconfigwidgets6 \
    kcrash6 \
    kguiaddons6 \
    kiconthemes6 \
    kio6 \
    knewstuff6 \
    knotifications6 \
    knotifyconfig6 \
    kparts6 \
    kservice6 \
    ktextwidgets6 \
    kwidgetsaddons6 \
    kxmlgui6 \
    \
    kpty6 \
    icu \
    kdoctools6 kdoctools6-native \
    \
    zlib \
"

PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, virtual/libx11"
PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, kglobalaccel6 kdbusaddons6"
PACKAGECONFIG[enable-ssh] = "-DENABLE_PLUGIN_SSHMANAGER=ON, -DENABLE_PLUGIN_SSHMANAGER=OFF, " 
PACKAGECONFIG[quick-commands] = "-DENABLE_PLUGIN_QUICKCOMMANDS=ON, -DENABLE_PLUGIN_QUICKCOMMANDS=OFF, "
PACKAGECONFIG[install-icons] = "-DINSTALL_ICONS=ON, -DINSTALL_ICONS=OFF, "

PACKAGECONFIG[remove-sendtext] = "-DREMOVE_SENDTEXT_RUNCOMMAND_DBUS_METHODS=ON, -DREMOVE_SENDTEXT_RUNCOMMAND_DBUS_METHODS=OFF, "
PACKAGECONFIG[uni-char-width] = "-DKONSOLE_BUILD_UNI2CHARACTERWIDTH=ON, -DKONSOLE_BUILD_UNI2CHARACTERWIDTH=OFF, "

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
    ki18n \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
    dbus \
    enable-ssh \
    quick-commands \
    install-icons \
"

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/zsh \
    ${datadir}/knotifications6 \
    ${datadir}/kio \
    ${datadir}/kglobalaccel \
"
