SUMMARY = "Extensible background service daemon for KDE applications"
DESCRIPTION = "KDED provides a modular background service daemon for KDE \
applications and desktop environments. It manages dynamically loaded \
service modules that provide session-level functionality, including \
configuration monitoring, hardware integration, and system event handling. \
The framework supports automatic module loading, D-Bus communication, \
and centralized management of background services used throughout KDE Plasma."

HOMEPAGE = "https://api.kde.org/frameworks/kded/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.0-only"

inherit kf6

DEPENDS:append = " \
    kcrash6 \
    kdbusaddons6 \
    kservice6 \
"

PACKAGECONFIG:append = " \
    kauth \
    kcoreaddons \
    kconfig \
    kdoctools \
"

## FIXME VIA PATCH  IF STILL NEEDED
do_configure:prepend() {
    sed -i \
        "s#KCONF_UPDATE_EXE=\".*\"#KCONF_UPDATE_EXE=\"${libexecdir}/kf6/kconf_update\"#g" \
        ${S}/src/CMakeLists.txt
}

BBCLASSEXTEND = "native nativesdk"
