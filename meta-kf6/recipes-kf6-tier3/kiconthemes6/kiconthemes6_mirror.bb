SUMMARY = "Icon theme integration framework for KDE and Qt applications"
DESCRIPTION = "KIconThemes provides a framework for managing and using icon \
themes in KDE and Qt applications. It supports icon theme discovery, \
icon loading, theme selection, and integration with the FreeDesktop \
icon theme specification. The framework includes icon selection dialogs, \
themed icon rendering, and Qt Quick integration, enabling applications \
to maintain consistent icon appearance across desktop environments."

HOMEPAGE = "https://api.kde.org/frameworks/kiconthemes/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.1-only"

inherit kf6 gtk-icon-cache

DEPENDS:append = " \
    breeze-icons6 \
    karchive6 \
    kcolorscheme6 \
    kwidgetsaddons6 \
    qtsvg6 \
"
PACKAGECONFIG[qml] = "-DKICONTHEMES_USE_QTQUICK=ON, -DKICONTHEMES_USE_QTQUICK=OFF, qtdeclarative6"
PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "

PACKAGECONFIG:append = " \
    kconfig \
    ki18n \
    qml \
    dbus \
"

BBCLASSEXTEND = "native nativesdk"
