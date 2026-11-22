SUMMARY = "Application crash handling framework for KDE and Qt applications"
DESCRIPTION = "KCrash provides a framework for handling application crashes \
and fatal errors in KDE and Qt applications. It installs crash handlers \
that detect abnormal process termination and can launch external crash \
reporting tools such as DrKonqi. The framework supports collecting crash \
information, initiating debugging and bug reporting workflows, and \
integrating with desktop environments to help diagnose application failures."

HOMEPAGE = "https://api.kde.org/frameworks/kcrash/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.0-or-later"
REUSE_LICENSECHECK_WHITELIST = "CC0-1.0"

inherit kf6

DEPENDS:append = " \
    kwindowsystem6 \
"

# TODO "Enables raising signals to kernel core patterns (iff the pattern is a process).
# You may wish to not install drkonqi if this can cause a UI conflict."
PACKAGECONFIG[pattern_raise] = "-DKCRASH_CORE_PATTERN_RAISE=ON, -DKCRASH_CORE_PATTERN_RAISE=OFF, "


PACKAGECONFIG[x11] = "-DWITH_X11=ON, -DWITH_X11=OFF, virtual/libx11"
PACKAGECONFIG:append = " \
    kcoreaddons \
"


PACKAGECONFIG:append:class-target = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'kde_x11', 'x11', '', d)} \
"


BBCLASSEXTEND = "native nativesdk"
