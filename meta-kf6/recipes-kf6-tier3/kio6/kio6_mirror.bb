SUMMARY = "Resource and network access framework for KDE and Qt applications"
DESCRIPTION = "KIO provides a framework for accessing and managing local \
and remote resources through a unified API. It supports asynchronous \
file operations, network protocols, URL handling, MIME type integration, \
and extensible worker processes for accessing different resource types. \
The framework provides reusable components for file dialogs, file \
management, resource browsing, and network-transparent I/O in KDE and \
Qt applications."

HOMEPAGE = "https://api.kde.org/frameworks/kio/html/index.html"
SECTION = "libs"

LICENSE = "BSD-2-Clause AND BSD-3-Clause AND LGPL-2.0-only AND MIT AND (GPL-2.0-only OR GPL-3.0-only OR LicenseRef-KDE-Accepted-GPL) AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"
REUSE_LICENSECHECK_WHITELIST = "Qt-Commercial-exception-1.0 CC0-1.0"

inherit kf6 features_check
REQUIRED_DISTRO_FEATURES = "polkit"
ANY_OF_DISTRO_FEATURES = "wayland kde_x11"

SRC_URI:append = " \
    file://0001-SwitcherooControl-is-runtime-and-not-buildtime.patch \
"

DEPENDS:append = " \
    acl \
    karchive6 \
    kservice6 \
    solid6 \
    kcrash6 \
    kguiaddons6 \
    kbookmarks6 \
    \
    kcolorscheme6 \
    kcompletion6 \
    kiconthemes6 \
    kitemviews6 \
    kjobwidgets6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
    kded6 \
    kwallet6 \
    \
    switcheroo-control \
    polkit-qt6-1 \
"

PACKAGECONFIG[full] = "-DKIOCORE_ONLY=OFF, -DKIOCORE_ONLY=ON, "
PACKAGECONFIG[webdavtest] = "-DUSE_FTPD_WSGIDAV_UNITTEST=ON, -DUSE_FTPD_WSGIDAV_UNITTEST=OFF, "
PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, kdbusaddons6"
PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, virtual/libx11"
PACKAGECONFIG[wayland] = "-DWITH_WAYLAND=ON, -DWITH_WAYLAND=OFF, qtwayland6 qtwayland6-native"

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    ki18n \
    kdoctools \
    kauth \
    dbus \
    full \
"
## TODO LOOK AT THIS IN NEW STACK
PACKAGECONFIG:append:class-target = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'kde_x11', 'x11', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)} \
"

EXTRA_OECMAKE:append = " -DBUILD_DESIGNERPLUGIN=OFF"

RDEPENDS:${PN} += " \
    ca-certificates \
    switcheroo-control \
"

PACKAGES =+ " ${PN}-private"
SUMMARY:${PN}-private = "Private URI filtering library for KDE KIO"
DESCRIPTION:${PN}-private = "Provides the internal URI filtering engine \
used by KIO to process and interpret user-entered addresses. This \
library implements private KIO functionality and is not intended \
to provide a stable public API for external applications."
FILES:${PN}-private = " \
    ${libdir}/libkuriikwsfiltereng_private.so \
" 

