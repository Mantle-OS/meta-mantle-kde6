SUMMARY = "Desktop-integrated Qt Quick Controls style for KDE applications"
DESCRIPTION = "QQC2 Desktop Style provides a Qt Quick Controls style that \
integrates QML applications with the native desktop appearance. It uses \
Qt Widgets styling to provide consistent controls, colors, typography, \
and visual behavior across KDE and Qt applications. The framework \
supports desktop theme integration and allows Qt Quick applications \
to maintain a consistent appearance alongside traditional Qt Widgets \
applications."

HOMEPAGE = "https://api.kde.org/frameworks/qqc2-desktop-style/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-3.0-only"

inherit kf6

DEPENDS:append = " \
    qtdeclarative6 \
    kirigami6 \
    kiconthemes6 \
    kconfigwidgets6 \
    kcolorscheme6 \
"

PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "

## FIXME VERIFY NEW NO x11
PACKAGECONFIG[x11] = " , , virtual/libx11"

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
    dbus \
"

PACKAGECONFIG:append:class-target = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'kde_x11', 'x11', '', d)} \
"
