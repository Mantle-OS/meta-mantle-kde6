SUMMARY = "Application action and content sharing framework for KDE and Qt applications"
DESCRIPTION = "Purpose provides a framework for discovering and executing \
application actions based on specific tasks or content types. It enables \
KDE and Qt applications to share files, images, URLs, and other data \
through extensible plugins and services. The framework supports \
integration with online services, local applications, and system \
components through reusable actions and QML interfaces."

HOMEPAGE = "https://api.kde.org/frameworks/purpose/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.1-or-later"

inherit kf6 gettext

DEPENDS:append = " \
    qtdeclarative6 \
    kio6 \
    kirigami6 \
    knotifications6 \
    kservice6 \
"

PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "

PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    ki18n \
    kauth \
    dbus \
"
