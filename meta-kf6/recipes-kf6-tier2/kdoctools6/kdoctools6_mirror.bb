# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

SUMMARY = "Documentation generation from docbook"
HOMEPAGE = "https://api.kde.org/frameworks/kdoctools/html/index.html"
LICENSE = "LGPL-2.1-only"

inherit kf6
inherit gettext
inherit perlnative

DEPENDS:append = " \
    karchive6 \
    ki18n6 \
    libxslt \
    libxml2 libxml2-native \
    docbook-xml-dtd4 \
    docbook-xsl-stylesheets \
    liburi-perl-native \
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

do_compile:append() {
    sed -i 's:${RECIPE_SYSROOT}::g' ${B}/src/customization/xsl/all-l10n.xml
}

BBCLASSEXTEND = "native nativesdk"
