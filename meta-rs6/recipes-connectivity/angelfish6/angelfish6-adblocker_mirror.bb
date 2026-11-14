# SPDX-FileCopyrightText: 2025 Joseph Mills <josephjamesmills@gmail.com>
#
# SPDX-License-Identifier: MIT
SUMMARY = "adblocker for angelfish(rust)"
SECTION = "libs"
LICENSE = "(GPL-2.0-only OR GPL-2.0-or-later OR GPL-3.0-only OR LicenseRef-KDE-Accepted-GPL) AND (LGPL-2.0-only OR LGPL-2.0-or-later OR LGPL-2.1-only OR LGPL-3.0-or-later OR LicenseRef-KDE-Accepted-LGPL)"

include angelfish-adblock-rs.inc
inherit kde6-licenses

SRC_URI:append = " \
    ${KDE6_RS_MIRROR}/angelfish-${KDE6_RS_VERSION}.tar.xz;name=angelfish-rs \
    file://0001-add-shared-rust-c-lib-and-also-rlib-to-crate-type.patch \
"
SRC_URI[angelfish-rs.sha256sum] = "0926325a19f2cc72e4a54fdacec5eb7cb15044e6129d28d1a959a706ad1c2d7c"
S = "${WORKDIR}/angelfish-${KDE6_RS_VERSION}"

CARGO_MANIFEST_PATH = "${S}/Cargo.toml"
CARGO_INSTALL_LIBRARIES = "1"

export CARGO_PROFILE_RELEASE_PANIC = "unwind"
RUSTFLAGS:append = " -C panic=unwind"


cargo_do_install:append() {

    install -d ${D}${libdir}/pkgconfig
    install -d ${D}${includedir}/angelfish-adblock
    
    install -m0644 ${B}/target/${CARGO_TARGET_SUBDIR}/libangelfish_adblock.a ${D}${libdir}/
    install -m0644 ${B}/target/${CARGO_TARGET_SUBDIR}/libangelfish_adblock.so ${D}${libdir}/
    install -m0644 \
        ${B}/target/${RUST_TARGET_SYS}/cxxbridge/angelfish-adblock/src/adblock.rs.h \
        ${D}${includedir}/angelfish-adblock

    cat > ${D}${libdir}/pkgconfig/angelfish-adblocker.pc <<'EOF'
prefix=${prefix}
exec_prefix=${exec_prefix}
libdir=${libdir}
includedir=${includedir}
Name: angelfish-adblock
Description: AngelFish Adblock (Rust)
Version: ${PV}
Libs: -L${libdir}
Cflags: -I${includedir}/angelfish-adblock
EOF
}


PACKAGE_BEFORE_PN:append = " ${PN}-rlib"

FILES_SOLIBSDEV = ""    

FILES:${PN}-rlib = " \
    ${libdir}/rustlib \
"

FILES:${PN} = " \
    ${libdir}/libangelfish_adblock.so \
    ${libdir}/pkgconfig/angelfish-adblocker.pc \
"

FILES:{PN}-staticdev = ""

BBCLASSEXTEND = "native"
