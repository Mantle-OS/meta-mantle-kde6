SUMMARY = "Desktop integration framework for KDE and Qt applications"
DESCRIPTION = "FrameworkIntegration provides plugins and utilities for \
integrating KDE Frameworks with Qt applications and desktop environments. \
It supports native file dialogs, application styling, color schemes, \
icon themes, and notification handling to provide a consistent user \
experience. The framework also provides integration with package \
management services for installing additional application resources \
and components."

HOMEPAGE = "https://api.kde.org/frameworks/frameworkintegration/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.0-only"

inherit kf6

DEPENDS:append = " \
    kconfigwidgets6 \
    kcolorscheme6 \
    kiconthemes6 \
    knotifications6 \
    kwidgetsaddons6 \
"

PACKAGECONFIG[kpackage] = " -DBUILD_KPACKAGE_INSTALL_HANDLERS=ON, -DBUILD_KPACKAGE_INSTALL_HANDLERS=OFF, \
    knewstuff6 \
    kpackage6 kpackage6-native \
    appstream \
    packagekit \
    packagekit-qt6 \
"

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
    ki18n \
    kpackage \
"

FILES:${PN} += " \
    ${datadir}/knotifications6 \
"
