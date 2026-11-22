SUMMARY = "Internationalization and localization framework for KDE and Qt applications"
DESCRIPTION = "KI18n provides an advanced internationalization framework \
for KDE and Qt applications, extending GNU gettext with support for \
context-aware translations, plural forms, and localized messages. \
It includes translation scripting, language-specific formatting, \
and QML integration to support multilingual user interfaces. \
The framework enables applications to deliver accurate and flexible \
localization across different languages and regions."

HOMEPAGE = "https://api.kde.org/frameworks/ki18n/html/index.html"
SECTION = "libs"

LICENSE = "BSD-3-Clause AND LGPL-2.0-or-later AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"

inherit kde6-ecm kf6-checksums gettext

DEPENDS:append:class-target = " ${PN}-native"
DEPENDS:append:class-nativesdk = " ${PN}-native"

SRC_URI = " \
    ${KDE6_MIRROR}/stable/frameworks/${KDE6_KF6_VERSION_DIR}/ki18n-${KDE6_KF6_VERSION}.tar.xz;name=${BPN} \
    file://0001-error-when-no-py-native-fix.patch \
"
S = "${UNPACKDIR}/ki18n-${KDE6_KF6_VERSION}"

DEPENDS:append = " \
    virtual/libintl \
    iso-codes \
"

DEPENDS:append:class-native = " python3-native"

PACKAGECONFIG[qml] = "-DBUILD_WITH_QML=ON, -DBUILD_WITH_QML=OFF, qtdeclarative6"
PACKAGECONFIG:append:class-target = " qml"

RDEPENDS:${PN} += " python3-core"
RDEPENDS:${PN}:class-target += " iso-codes"

BBCLASSEXTEND = "native nativesdk"
