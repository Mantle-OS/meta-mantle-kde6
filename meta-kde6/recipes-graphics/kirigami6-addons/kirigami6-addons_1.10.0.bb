SUMMARY = "Kirgami6 addons"
HOMEPAGE = ""
SECTION = "libs"
LICENSE = "BSD-2-Clause AND LGPL-2.1-or-later"

PV = "1.10.0"
PR = "r0"  

inherit kde6-ecm 

SRC_URI = "https://download.kde.org/stable/kirigami-addons/kirigami-addons-1.16.0.tar.xz"
SRC_URI[sha256sum] = "124b01267acf603c3afd9fb9599a33fee7a28d65756890163e9593a3932084dd"
S = "${UNPACKDIR}/kirigami-addons-1.16.0"

DEPENDS:append = " \
    qtdeclarative6 \
    \
    kconfig6 kconfig6-native\
    ki18n6 ki18n6-native gettext-native \
    kcoreaddons6 kcoreaddons6-native \
    \
    kirigami6 \
    kguiaddons6 \
    kcolorscheme6 \
    kcrash6 \
    kiconthemes6 \
    kglobalaccel6 \
"
EXTRA_OECMAKE:append:class-target = " \
    -DKF6_HOST_TOOLING=${STAGING_LIBDIR_NATIVE}/cmake \
"
EXTRA_OECMAKE:append:class-nativesdk = " \
    -DKF6_HOST_TOOLING=${STAGING_LIBDIR_NATIVE}/cmake \
"
#BBCLASSEXTEND = "native"
