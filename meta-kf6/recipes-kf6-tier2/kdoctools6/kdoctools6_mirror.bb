SUMMARY = "DocBook documentation processing tools for KDE and Qt applications"
DESCRIPTION = "KDocTools provides tools and libraries for generating, \
processing, and displaying application documentation written in DocBook \
XML. It includes utilities such as meinproc6 for transforming DocBook \
documents into HTML and other formats, along with XML validation and \
localization support. The framework integrates with KDE's documentation \
infrastructure to provide consistent, multilingual application manuals \
and help content."

HOMEPAGE = "https://api.kde.org/frameworks/kdoctools/html/index.html"
SECTION = "devel"

LICENSE = "LGPL-2.1-only"

inherit kf6 gettext perlnative

## TODO TRACE X11 IF NEED BE
DEPENDS:append = " \
    liburi-perl-native \
    karchive6 \
    ki18n6 \
    libxslt \
    libxml2 \
    libxml2-native \
    docbook-xml-dtd4 \
    docbook-xsl-stylesheets \
    docbook-xsl-stylesheets-native \
"

DEPENDS:append:class-target = " ${PN}-native"
DEPENDS:append:class-nativesdk = " ${PN}-native"

EXTRA_OECMAKE:class-native = " \
    -DINSTALL_INTERNAL_TOOLS=ON \
    -DRELOCATABLE_DOCBOOK_FILES=ON \
"

EXTRA_OECMAKE:class-target = " \
    -DMEINPROC6_EXECUTABLE=${STAGING_DIR_NATIVE}/${bindir}/meinproc6 \
    -DDOCBOOKL10NHELPER_EXECUTABLE=${STAGING_DIR_NATIVE}/${bindir}/docbookl10nhelper \
    -DCHECKXML6_EXECUTABLE=${STAGING_DIR_NATIVE}/${bindir}/checkXML6 \
    -DRELOCATABLE_DOCBOOK_FILES=ON \
"

# TODO split recipe for native and target to avoid stripping warnings for applications
sysroot_stage_all:append:class-native () {
    install -d ${SYSROOT_DESTDIR}${bindir}
    install -m 0755 ${B}/bin/docbookl10nhelper ${SYSROOT_DESTDIR}${bindir}
}

sysroot_stage_all:append:class-target () {
    install -d ${SYSROOT_DESTDIR}${bindir}
    install -m 0755 ${STAGING_BINDIR_NATIVE}/checkXML6 ${SYSROOT_DESTDIR}${bindir}
    install -m 0755 ${STAGING_BINDIR_NATIVE}/meinproc6 ${SYSROOT_DESTDIR}${bindir}
}

## TODO PATCH THIS
do_compile:append() {
    sed -i 's:${RECIPE_SYSROOT}::g' ${B}/src/customization/xsl/all-l10n.xml
}

BBCLASSEXTEND = "native nativesdk"
