inherit kde6-ecm

inherit kf6-packageconfig

inherit plasma6-checksums
PLASMA6_MODULE ?= "${BPN}"

PV = "${KDE6_PLASMA_VERSION}"
PR = "r0"

def plasma_real_name(d, name=None):
    override_map = {
    }
    bpn = name or d.getVar("BPN") or ""
    if bpn in override_map:
        return override_map[bpn]
    if bpn.endswith("6"):
        return bpn[:-1]
    return bpn

SRC_URI ?= "${KDE6_PLASMA_MIRROR}/${@plasma_real_name(d)}-${PV}.tar.xz;name=${BPN}"

DEPENDS:append = " \
    kpackage-native \
    kcmutils \
"

PACKAGECONFIG:append = " \
    ki18n \
    kcoreaddons \
    kconfig \
    kauth \
    kdoctools \
"
