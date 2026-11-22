SUMMARY = "Framework for managing data packages in KDE and Qt applications"
DESCRIPTION = "KPackage provides a framework for loading, installing, \
and managing packages containing non-binary application resources. \
It supports structured packages such as Plasma widgets, themes, \
wallpapers, and other extensible application components. The framework \
provides package metadata handling, validation, resource discovery, \
and installation utilities, allowing applications to distribute and \
manage data-driven extensions independently of compiled plugins."

HOMEPAGE = "https://api.kde.org/frameworks/kpackage/html/index.html"
SECTION = "libs"

LICENSE = "GPL-2.0-or-later AND LGPL-2.0-or-later"

inherit kf6

DEPENDS:append = " karchive6"

DEPENDS:append:class-target = " ${PN}-native" 
DEPENDS:append:class-nativesdk = " ${PN}-native"

PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "
PACKAGECONFIG:append = " \
    kcoreaddons \
    ki18n \
    kdoctools \
    dbus \
" 

BBCLASSEXTEND = "native nativesdk"
