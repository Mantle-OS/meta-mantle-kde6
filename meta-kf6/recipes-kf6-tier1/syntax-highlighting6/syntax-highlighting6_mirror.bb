SUMMARY = "Syntax highlighting framework for KDE and Qt applications"
DESCRIPTION = "KSyntaxHighlighting provides a syntax highlighting engine \
for source code and structured text in KDE and Qt applications. It supports \
a wide range of programming languages, markup formats, and configuration \
files through XML-based syntax definitions. The framework provides \
context-aware highlighting, automatic language detection, and integration \
with Qt text rendering components."
HOMEPAGE = "https://api.kde.org/frameworks/syntax-highlighting/html/index.html"
SECTION = "libs"
LICENSE = "LGPL-2.0-or-later AND MIT"
REUSE_LICENSECHECK_WHITELIST = "LGPL-2.1-or-later GPL-2.0-only"

inherit kf6 perlnative

DEPENDS:append:class-target = " ${BPN}-native"
DEPENDS:append:class-nativesdk = " ${BPN}-native"

DEPENDS:append = " \
    xerces-c \
    qtdeclarative6 \
"

PACKAGECONFIG[gui] = "-DKSYNTAXHIGHLIGHTING_USE_GUI=ON, -DKSYNTAXHIGHLIGHTING_USE_GUI=OFF, "
PACKAGECONFIG[qrc] = "-DQRC_SYNTAX=ON, -DQRC_SYNTAX=OFF, "
PACKAGECONFIG[no-standardpaths] = "-DNO_STANDARD_PATHS=ON, -DNO_STANDARD_PATHS=OFF, "

PACKAGECONFIG:append = " gui qrc"
PACKAGECONFIG:remove:class-native = " gui"

EXTRA_OECMAKE:class-target = " -DKATEHIGHLIGHTINGINDEXER_EXECUTABLE=${STAGING_DIR_NATIVE}${bindir}/katehighlightingindexer"

sysroot_stage_all:append:class-native () {
    install -d ${SYSROOT_DESTDIR}/${bindir}
    cp ${B}/bin/katehighlightingindexer ${SYSROOT_DESTDIR}/${bindir}
}
BBCLASSEXTEND = "native nativesdk"
