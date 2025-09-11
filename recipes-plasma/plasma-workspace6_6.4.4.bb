# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2019 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020-2021 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Plasma Workspace"
HOMEPAGE = ""
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=9e2385fe012386d34dcc5c9863070881"
PR = "r0"

inherit cmake_plasma
inherit mime-xdg systemd features_check

REQUIRED_DISTRO_FEATURES = "wayland"

SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/plasma-workspace-6.4.4.tar.xz"
SRC_URI[sha256sum] = "de53b4eef688b59b7c56090485d41e7f8be3d3b99f1cf1358a7d3f4da9eebcb2"

DEPENDS = " \
    qtdeclarative6 \
    qtwayland6-native \
    qtpositioning6 \
    qtlocation6 \
    \
    kcoreaddons6 \
    kcrash6 \
    kdbusaddons6 \
    kholidays6 \
    kidletime6 \
    kitemmodels6 \
    knewstuff6 \
    knotifications6 \
    kpackage6 \
    kpeople6 \
    kquickcharts6 \
    krunner6 \
    kstatusnotifieritem6 \
    ksvg6 \
    ktexteditor6 \
    kunitconversion6 \
    kuserfeedback6 \
    kwallet6 \
    kwallet6 \
    networkmanager-qt6 \
    plasma5support6 \
    prison6 \
    solid6 \
    \
    baloo6 \
    breeze6 \
    kded6 \
    kdesu6 \
    kpipewire6 \
    kscreenlocker6 \
    kwayland6 \
    kwin6 \
    libkscreen6 \
    libksysguard6 \
    libplasma6 \
    plasma-activities6 \
    plasma-activities6-stats6 \
    \
    knotifyconfig6 \
    libqalculate6 \
    libxft \
    phonon \
    qcoro6 \
"

EXTRA_OECMAKE += " -DBUILD_xembed-sni-proxy=OFF -DINSTALL_SDDM_WAYLAND_SESSION=ON"

FILES_SOLIBSDEV = ""
FILES:${PN} += " \
    ${datadir}/desktop-directories/*.directory \
    ${datadir}/kcontrol \
    ${datadir}/kdisplay \
    ${datadir}/kfontinst \
    ${datadir}/kglobalaccel \
    ${datadir}/kio/servicemenus \
    ${datadir}/kio_desktop \
    ${datadir}/konqsidebartng \
    ${datadir}/kpackage \
    ${datadir}/krunner \
    ${datadir}/kservices6 \
    ${datadir}/ksmserver/themes \
    ${datadir}/ksplash/Themes \
    ${datadir}/kstyle/themes/*.themerc \
    ${datadir}/kxmlgui5/kfontview/* \
    ${datadir}/plasma/avatars \
    ${datadir}/plasma/look-and-feel \
    ${datadir}/plasma/nightcolor \
    ${datadir}/plasma/plasmoids \
    ${datadir}/plasma/services/*.operations \
    ${datadir}/plasma/shareprovider \
    ${datadir}/plasma/wallpapers \
    ${datadir}/plasma/weather \
    ${datadir}/plasma5support/services/* \
    ${datadir}/sddm/themes \
    ${datadir}/solid/actions/*.desktop \
    ${datadir}/timezonefiles \
    ${datadir}/wayland-sessions/*.desktop \
    ${datadir}/xdg-desktop-portal/* \
    ${datadir}/xsessions/*.desktop \
    ${datadir}/zsh/site-functions/* \
    ${libdir}/libkrdb.so \
    ${libdir}/libkworkspace6.so \
    ${libdir}/qt6/plugins/kf6/krunner/kcms/kcm_krunner_kill.so \
    ${libdir}/qt6/plugins/plasma/kcms/systemsettings/*.so \
    ${libdir}/qt6/plugins/plasma/kcms/systemsettings_qwidgets/kcm_fontinst.so \
    ${libdir}/qt6/plugins/plasmacalendarplugins \
    ${libdir}/qt6/qml/org/kde \
"

FILES:${PN}-dev += " \
    ${libdir}/libbatterycontrol.so \
    ${libdir}/libcolorcorrect.so \
    ${libdir}/libkfontinst.so \
    ${libdir}/libkfontinstui.so \
    ${libdir}/libklipper.so \
    ${libdir}/libkmpris.so \
    ${libdir}/libkworkspace5.so \
    ${libdir}/libnotificationmanager.so \
    ${libdir}/libplasma-geolocation-interface.so \
    ${libdir}/libtaskmanager.so \
    ${libdir}/libweather_ion.so \
"

INSANE_SKIP:${PN} = "dev-so"

RDEPENDS:${PN} += " \
    kquickcharts6 \
    milou6 \
    ocean-sound-theme6 \
    xprop \
    xset \
    qttools6-tools \
"

do_install:append() {
    sed -i 's:${RECIPE_SYSROOT_NATIVE}::g' ${D}${systemd_user_unitdir}/plasma-ksplash-ready.service
    sed -i 's:${RECIPE_SYSROOT_NATIVE}::g' ${D}${systemd_user_unitdir}/plasma-kcminit-phase1.service
    sed -i 's:${RECIPE_SYSROOT_NATIVE}::g' ${D}${systemd_user_unitdir}/plasma-restoresession.service
    sed -i 's:${RECIPE_SYSROOT_NATIVE}::g' ${D}${datadir}/kconf_update/migrate-calendar-to-plugin-id.py
}
