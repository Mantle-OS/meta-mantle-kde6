SUMMARY = "Spell checking framework for KDE and Qt applications"
DESCRIPTION = "Sonnet provides a spell checking framework for KDE and Qt \
applications, supporting multiple spelling backends including Aspell, \
Hunspell, and Hspell. It offers automatic language detection, dictionary \
management, and interactive spell checking through Qt widgets and QML \
components. The framework enables applications to integrate multilingual \
spell checking and text correction capabilities."
HOMEPAGE = "https://api.kde.org/frameworks/sonnet/html/index.html"
SECTION = "libs"

LICENSE = "BSD-3-Clause AND LGPL-2.0-or-later AND LGPL-2.1-or-later"

inherit kf6

DEPENDS:append:class-target = " \
    ${PN}-native \
    aspell \
    hunspell hunspell-dictionaries \
    hspell \
"

PACKAGECONFIG[widgets] = "-DSONNET_USE_WIDGETS=ON, -DSONNET_USE_WIDGETS=OFF, "
PACKAGECONFIG[qml] = "-DSONNET_USE_QML=ON, -DSONNET_USE_QML=OFF, qtdeclarative6"
PACKAGECONFIG[no-backend] = "-DSONNET_NO_BACKENDS=ON, -DSONNET_NO_BACKENDS=OFF, "

PACKAGECONFIG:append = " widgets qml"

PACKAGECONFIG:remove:class-native = "widgets qml"
PACKAGECONFIG:append:class-native = " no-backend"

EXTRA_OECMAKE:append:class-target = " -DPARSETRIGRAMS_EXECUTABLE=${STAGING_DIR_NATIVE}${bindir}/parsetrigrams6"
BBCLASSEXTEND = "native nativesdk"
