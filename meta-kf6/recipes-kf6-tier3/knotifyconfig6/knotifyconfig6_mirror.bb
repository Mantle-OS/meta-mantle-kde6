SUMMARY = "Notification configuration framework for KDE and Qt applications"
DESCRIPTION = "KNotifyConfig provides a framework for configuring application \
notifications in KDE and Qt applications. It offers reusable widgets \
and dialogs for managing notification events, sounds, and visual alerts. \
The framework integrates with KNotifications and KDE's configuration \
system, allowing users to customize notification behavior for individual \
applications and events."

HOMEPAGE = "https://api.kde.org/frameworks/knotifyconfig/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.0-only"

inherit kf6 features_check
ANY_OF_DISTRO_FEATURES = "alsa pulseaudio"

DEPENDS:append = " \
    qtmultimedia6 \
    phonon-kf6 \
    \
    kcompletion6 \
    kio6 \
"

PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON, -DUSE_DBUS=OFF, "

PACKAGECONFIG[canberra] = ",, libcanberra"

PACKAGECONFIG[tests] = "-DBUILD_TESTING=true, -DBUILD_TESTING=false, \
    kxmlgui6 \
    kwidgetsaddons6 \
    knotifications6 \
"

PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    kauth \
    dbus \
    canberra \
"
