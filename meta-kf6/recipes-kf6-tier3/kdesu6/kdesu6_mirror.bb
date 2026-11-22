SUMMARY = "Privilege escalation framework for KDE and Qt applications"
DESCRIPTION = "KDESu provides a framework for executing commands with \
elevated privileges through traditional Unix authentication utilities. \
It supports su, sudo, and doas for launching processes under different \
user identities, including root. The framework provides reusable APIs \
for password authentication, process execution, and communication with \
privileged helper processes."

HOMEPAGE = "https://api.kde.org/frameworks/kdesu/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.1-only"

inherit kf6

DEPENDS:append = " kpty6"

PACKAGECONFIG[use_sudo] = "-DKDESU_USE_SUDO_DEFAULT=ON ,-DKDESU_USE_SUDO_DEFAULT=OFF , "
PACKAGECONFIG[use_doas] = "-DKDESU_USE_DOAS_DEFAULT=ON, -DKDESU_USE_DOAS_DEFAULT=OFF, "
PACKAGECONFIG[x11] = ",,virtual/libx11"

PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    ki18n \
"

PACKAGECONFIG:append:class-target = " \
    use_sudo \
    ${@bb.utils.contains('DISTRO_FEATURES', 'kde_x11', 'x11', '', d)} \
"

RDEPENDS:${PN}:append:class-target = " \
    ${@bb.utils.contains('PACKAGECONFIG', 'use_sudo', 'sudo', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'use_doas', 'doas', '', d)} \
"
