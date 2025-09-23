SUMMARY="Corrosion"
DESCRIPTION="Corrosion, formerly known as cmake-cargo, is a tool for integrating Rust into an existing CMake project."
HOMEPAGE="https://corrosion-rs.github.io/corrosion/"
SECTION="dev"
LICENSE="MIT"
LIC_FILES_CHKSUM="file://README.md;md5=62807de31d464746b9728dac1bd6c291"

SRC_URI = "\
    git://github.com/corrosion-rs/corrosion.git;branch=stable/v0.5;protocol=https \
"
SRCREV="b1fab721655c5c4b1b08a083d3cd29f163af75d0"
S="${WORKDIR}/git"

inherit kde6-cmake

DEPENDS:append = " cargo-native rust-native"

PACKAGECONFIG[test] = "-DCORROSION_BUILD_TESTS=ON, -DCORROSION_BUILD_TESTS=OFF, "
PACKAGECONFIG[install-only] = "-DCORROSION_INSTALL_ONLY=ON, -DCORROSION_INSTALL_ONLY=OFF, "
PACKAGECONFIG[dev-mode] = "-DCORROSION_DEV_MODE=ON, -DCORROSION_DEV_MODE=OFF, "

PACKAGECONFIG  = "install-only"

