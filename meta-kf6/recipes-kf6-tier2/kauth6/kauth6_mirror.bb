SUMMARY = "Privilege escalation and authorization framework for KDE and Qt applications"
DESCRIPTION = "KAuth provides a framework for executing privileged \
operations securely from KDE and Qt applications. It separates \
unprivileged application code from privileged helper processes and \
integrates with system authorization mechanisms such as PolicyKit. \
The framework supports authorization checks, asynchronous execution, \
and policy generation for applications requiring administrative access."

HOMEPAGE = "https://api.kde.org/frameworks/kauth/html/index.html"
BUGTRACKER = "https://bugs.kde.org/"
SECTION = "libs"

LICENSE = "BSD-3-Clause AND LGPL-2.0-or-later AND LGPL-2.1-or-later"

inherit kf6 features_check
REQUIRED_DISTRO_FEATURES:class-target = "polkit"

DEPENDS:append = " \
    kcoreaddons6 \
    kwindowsystem6 \
"

DEPENDS:append:class-target = " \
    ${PN}-native \
    polkit-qt6-1 \
"

DEPENDS:append:class-nativesdk = " ${PN}-native"

PACKAGECONFIG[codegen-only] = " -DKAUTH_BUILD_CODEGENERATOR_ONLY=ON, -DKAUTH_BUILD_CODEGENERATOR_ONLY=OFF, "

PACKAGECONFIG:append:class-native  = " codegen-only"
PACKAGECONFIG:append:class-nativesdk  = " codegen-only"

EXTRA_OECMAKE:append = " -DKAUTH_BACKEND_NAME=POLKITQT6-1"
EXTRA_OECMAKE:append:class-native = " \
    -DKAUTH_POLICY_FILES_INSTALL_DIR=${datadir}/polkit-1/actions \
"

BBCLASSEXTEND = "native nativesdk"
