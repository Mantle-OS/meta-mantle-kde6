SUMMARY = "Secure credential storage framework for KDE and Qt applications"
DESCRIPTION = "KWallet provides a secure framework for storing and managing \
passwords, authentication credentials, and other sensitive application \
data. It supports encrypted wallets, credential access through D-Bus, \
and integration with KDE applications and desktop services. The framework \
includes wallet management utilities, Secret Service API integration, \
and configurable authentication and encryption backends."
HOMEPAGE = "https://api.kde.org/frameworks/kwallet/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.1-or-later"

inherit kf6 features_check
ANY_OF_DISTRO_FEATURES = "kde_x11 wayland"

DEPENDS:append = " \
    libsecret \
    libgcrypt \
    gpgme \
    qca-kf6 \
    qca-kf6-native \
    \
    kcolorscheme6 \
    kcrash6 \
    kconfigwidgets6 \
    kdbusaddons6 \
    knotifications6 \
    kservice6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
"

PACKAGECONFIG[secretd] = "-DBUILD_KSECRETD=ON, -DBUILD_KSECRETD=OFF, "
PACKAGECONFIG[walletd] = "-DBUILD_KWALLETD=ON, -DBUILD_KWALLETD=OFF, "
PACKAGECONFIG[query] = "-DBUILD_KWALLET_QUERY=ON, -DBUILD_KWALLET_QUERY=OFF, "
PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, virtual/libx11" 

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
    ki18n \
    kdoctools \
    secretd \
    walletd \
    query \
"

PACKAGECONFIG:append:class-target = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'kde_x11', 'x11', '', d)} \
"

EXTRA_OECMAKE:append = " \
    -DOE_QCA_PATH_HOST_ROOT=${STAGING_DIR_HOST} \
"

FILES:${PN} += " \
    ${datadir}/knotifications6 \
    ${datadir}/xdg-desktop-portal/portals/* \
"
