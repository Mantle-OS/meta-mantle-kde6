SUMMARY = "Polkit-qt6-1 PolicyKit API with Qt-styled API"
DESCRIPTION = "Polkit-qt-1 provides a Qt-style API for interacting with \
the PolicyKit authorization framework. It enables Qt6 applications to \
check authorizations, manage authentication requests and implement \
authentication agents for privileged system operations."
HOMEPAGE = "https://api.kde.org/polkit-qt-1/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.0-or-later AND BSD-3-Clause AND GPL-2.0-or-later"
LIC_FILES_CHKSUM = " \
    file://LICENSES/LGPL-2.0-or-later.txt;md5=da48810c4ddf8e49efa031294a26b98c \
    file://LICENSES/BSD-3-Clause.txt;md5=954f4d71a37096249f837652a7f586c0 \
    file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881 \
"

inherit kde6-cmake gobject-introspection pkgconfig features_check
REQUIRED_DISTRO_FEATURES = "polkit"

SRC_URI = "git://invent.kde.org/libraries/polkit-qt-1.git;branch=master;protocol=https"
SRCREV = "6acb9630fb130aa2ebc4f51462996779211b693b"

DEPENDS:append = " \
    polkit \
    glib-2.0 \
"

PACKAGECONFIG[qt6] = "-DBUILD_WITH_QT6=ON,  -DBUILD_WITH_QT6=OFF, qtbase6"
PACKAGECONFIG[examples] = "-DBUILD_EXAMPLES=ON,  -DBUILD_EXAMPLES=OFF, "
PACKAGECONFIG[tests] = "-DBUILD_TEST=ON, -DBUILD_TEST=OFF, "

PACKAGECONFIG:append = " \
    qt6 \
"
