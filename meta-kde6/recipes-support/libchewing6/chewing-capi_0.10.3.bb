# SPDX-FileCopyrightText: 2025 Joseph Mills <josephjamesmills@gmail.com>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Capi for chewing rust"

include libchewing6-rs.inc

SRC_URI:append = " \
    file://0001-make-rust-so-object-chewing-capi.patch \
"

CARGO_BUILD_FLAGS:append = " --locked -p chewing_capi -p chewing-cli"
CARGO_MANIFEST_PATH = "${S}/Cargo.toml"
CARGO_INSTALL_LIBRARIES = "1"

export CARGO_PROFILE_RELEASE_PANIC = "unwind"
RUSTFLAGS:append = " -C panic=unwind"

DEPENDS:append = " ncurses"
PACKAGECONFIG[sqlite] = "--features sqlite, ,sqlite3 pkgconfig"
PACKAGECONFIG:append = " \
    sqlite \
"

cargo_do_install:append() {        
    install -d ${D}${libdir}/pkgconfig
    install -d ${D}${bindir}
    install -d ${D}${includedir}/chewing-capi
    
    install -m0644 \
        ${B}/target/${CARGO_TARGET_SUBDIR}/libchewing_capi.a \
        ${D}${libdir}/
    
    install -m0755 \
        ${B}/target/${CARGO_TARGET_SUBDIR}/chewing-cli \
        ${D}${bindir}
     
    install -m0644 ${S}/capi/include/*.h ${D}${includedir}/chewing-capi/
    
    cat > ${D}${libdir}/pkgconfig/chewing_capi.pc <<'EOF'
prefix=${prefix}
exec_prefix=${exec_prefix}
libdir=${libdir}
includedir=${includedir}
Name: chewing_capi
Description: C ABI for libchewing (Rust)
Version: ${PV}
Libs: -L${libdir} -lchewing_capi
Cflags: -I${includedir}/chewing-capi
EOF
}


PACKAGE_BEFORE_PN:append = " chewing-tools ${PN}-rlib"

FILES_SOLIBSDEV = ""    
FILES:chewing-tools = " \
    ${bindir}/chewing-cli \
"

FILES:${PN}-rlib = " \
    ${libdir}/rustlib \
"

FILES:${PN} = " \
    ${libdir}/libchewing_capi.so \
    ${libdir}/pkgconfig/chewing_capi.pc \
"

FILES:{PN}-staticdev = " \
    ${libdir}/libchewing_capi.a \
"

BBCLASSEXTEND = "native"
