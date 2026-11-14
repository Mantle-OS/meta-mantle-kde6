# SPDX-FileCopyrightText: 2022 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Qalculate! library and CLI"
HOMEPAGE = "https://qalculate.github.io/"
SECTION = "libs"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=570a9b3749dd0463a1778803b12a6dce"

inherit autotools
inherit gettext
inherit pkgconfig

SRC_URI = " \
    git://github.com/Qalculate/libqalculate.git;branch=master;protocol=https \
"

SRCREV = "97db5e178dbbeae3caf546bac8a1ca27746ff8c1"

DEPENDS:append = " \
    gmp \
    mpfr \
    libxml2 \
    intltool-native \
"

PACKAGECONFIG[textport] = "--enable-textport, --disable-textport, "
PACKAGECONFIG[defs2doc] = "--enable-defs2doc, --disable-defs2doc,  doxygen-native"
PACKAGECONFIG[tests] = "--enable-tests, --disable-tests, "
PACKAGECONFIG[unittests] = " --enable-unittests,  --disable-unittests, "

PACKAGECONFIG[compiled-definitions] = "--enable-compiled-definitions, --disable-compiled-definitions, "
PACKAGECONFIG[disable-insecure] = "--enable-insecure, --disable-insecure, "

PACKAGECONFIG[curl] = "--with-libcurl, --without-libcurl, curl"
PACKAGECONFIG[icu] = "--with-icu, --without-icu, icu"
PACKAGECONFIG[gnuplot] = "--with-gnuplot-call, --without-gnuplot_call, gnuplot"
PACKAGECONFIG[readline] = "--with-readline, --without-readline, readline ncurses "

PACKAGECONFIG:append ??= " \
    textport \
    compiled-definitions \
    curl \
    icu \
    gnuplot \
    readline \
"

FILES:${PN} += " \
    ${datadir}/qalculate \
"
