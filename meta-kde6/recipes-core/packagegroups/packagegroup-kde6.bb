SUMMARY = "Misc packages for the KDE Desktop or Mobile"
DESCRIPTION = "These recipes are not in release support kf6 nor plasma so they live here. \
 browser, education, games, graphics, multimedia and system recipes are in the base package \
 while packagegroup-kde6-support holds all the supporint packages.\
"
SECTION = "x11"
LICENSE = "MIT"

PV = ""

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup features_check

REQUIRED_DISTRO_FEATURES = "wayland x11"

PACKAGES = " \
    ${PN} \
    ${PN}-support \
"
 
RRECOMMENDS:${PN} = " \
    gcompris6 \
    washipad6 \
    plasma-wayland-protocols-kf6 \
    plasma-remotecontrollers6 \
    plasma-bigscreen6 \
    plank-player6 \
    aura-browser6 \
    arkade6 \
    plasma-mobile-settings6 \
"

RDEPENDS:${PN} += " \
    ${PN}-support \
"
 
SUMMARY:${PN}-support = "support recipes for kde6"
DESCRIPTION:${PN}-support = "These packages are meant to support other parts of KDE6 like plasma and KF6"
RRECOMMENDS:${PN}-support = " \
    qtkeychain6 \
    sddm-qt6 \
    kpeoplevcard6 \
    zxing-cpp-kf6 \
    pulseaudio-qt6 \
    kcolorpicker6 \
    chewing-capi \
    libchewing6 \
    phonon-backend-vlc-kf6 \
    polkit-qt6-1 \
    ddcutil6 \
    qcoro6 \
    oxygen-icons-kf6 \
    libphonenumber-cpp \
    qca-kf6 \
    libpinyin6 \
    futuresql6 \
    kquickimageeditor6 \
    libqalculate6 \
    libdmtx-kf6 \
    heaptrack6 \
    phonon-kf6 \
    corrosion \
    qmltermwidget6 \
    simplelogin6 \
    qml-box2d6 \
    maliit-keyboard-qt6 \
    maliit-framework-qt6 \
    kimageannotator6  \
"
