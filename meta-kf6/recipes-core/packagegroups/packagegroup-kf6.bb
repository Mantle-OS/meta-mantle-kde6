# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
SUMMARY = "All meta-kf6 packages"
DESCRIPTION = "This packagegroup is pimarily meant for release testing."
SECTION = "x11"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
PV = "${KDE6_KF6_VERSION}"

# TODO Add gaurds for wayland vs x11.

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup features_check

REQUIRED_DISTRO_FEATURES = "wayland x11"

PACKAGES = " \
    ${PN} \
    ${PN}-support \
    ${PN}-tier0 \
    ${PN}-tier1 \
    ${PN}-tier2 \
    ${PN}-tier3 \
    ${PN}-tier4 \
"
 
RDEPENDS:${PN} = " \
    ${PN}-support \
    ${PN}-tier0 \
"
 
SUMMARY:${PN}-support = "support recipes for kf6"
RRECOMMENDS:${PN}-support = " \
    libdmtx-kf6 \
    libkexiv2-kf6 \
    oxygen-icons-kf6 \
    phonon-backend-vlc-kf6 \
    phonon-kf6 \
    plasma-wayland-protocols-kf6 \
    polkit-qt6-1 \
    qca-kf6 \
    zxing-cpp-kf6 \
"

SUMMARY:${PN}-tier0 = "Kde6 Framework Tier 0"
DESCRIPTION:${PN}-tier0 = "Extra CMake modules"
RRECOMMENDS:${PN}-tier0 = "\
    ecm-kf6 \
"

SUMMARY:${PN}-tier1 = "Kde6 Framework Tier 1"
DESCRIPTION:${PN}-tier1 = "Tier 1 frameworks depend only on Qt (and possibly a small number of other third-party libraries), so can easily be used by any Qt-based project."
RRECOMMENDS:${PN}-tier1 = " \
    attica6 \
    bluez-qt6 \
    breeze-icons6 \
    karchive6 \
    kcalendarcore6 \
    kcodecs6 \
    kconfig6 \
    kcoreaddons6 \
    kdbusaddons6 \
    kdnssd6 \
    kglobalaccel6 \
    kguiaddons6 \
    kholidays6 \
    ki18n6 \
    kidletime6 \
    kirigami6 \
    kitemmodels6 \
    kitemviews6 \
    kplotting6 \
    kquickcharts6 \
    ktexttemplate6 \
    kuserfeedback6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
    modemmanager-qt6 \
    networkmanager-qt6 \
    prison6 \
    solid6 \
    sonnet6 \
    syntax-highlighting6 \
    threadweaver6 \
"

SUMMARY:${PN}-tier2 = "Kde6 Framework Tier 2"
DESCRIPTION:${PN}-tier2 =  "Tier 2 frameworks additionally depend on tier 1 frameworks, but still have easily manageable dependencies."
RRECOMMENDS:${PN}-tier2 = "\
    kauth6 \
    kcolorscheme6 \
    kcompletion6 \
    kcontacts6 \
    kcrash6 \
    kdoctools6 \
    kfilemetadata6 \
    kimageformats6 \
    kjobwidgets6 \
    knotifications6 \
    kpackage6 \
    kpty6 \
    kstatusnotifieritem6 \
    kunitconversion6 \
    syndication6 \
"

SUMMARY:${PN}-tier3 = "Kde6 Framework Tier 3"
DESCRIPTION:${PN}-tier3 =  "Tier 3 frameworks are generally more powerful, comprehensive packages, and consequently have more complex dependencies."
RRECOMMENDS:${PN}-tier3 = "\
    baloo6 \
    kbookmarks6 \
    kcmutils6 \
    kconfigwidgets6 \
    kdav6 \
    kdeclarative6 \
    kded6 \
    kdesu6 \
    kiconthemes6 \
    kio6 \
    knewstuff6 \
    knotifyconfig6 \
    kparts6 \
    kpeople6 \
    krunner6 \
    kservice6 \
    ksvg6 \
    ktexteditor6 \
    ktextwidgets6 \
    kwallet6 \
    kxmlgui6 \
    purpose6 \
    qqc2-desktop-style6 \
"

SUMMARY:${PN}-tier4 = "Kde6 Framework Tier 4"
DESCRIPTION:${PN}-tier4 = "Tier 4 frameworks can be mostly ignored by application programmers; this tier consists of plugins acting behind the scenes to provide additional functionality or platform integration to existing frameworks (including Qt)."
RRECOMMENDS:${PN}-tier4 = "\
    frameworkintegration6 \
"
