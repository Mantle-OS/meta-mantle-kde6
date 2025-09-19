inherit kde6-ecm

inherit kf6-packageconfig

KDE6_RS_MODULE ?= "${BPN}"
inherit rs6-checksums

PV = "${KDE6_RS_VERSION}"
PR = "r0"

def kde6_rs_real_name(d, name=None):
    override_map = {
    }

    bpn = name or d.getVar("BPN") or ""
    if bpn in override_map:
        return override_map[bpn]
    if bpn.endswith("6"):
        return bpn[:-1]
    return bpn


SRC_URI ?= "${KDE6_RS_MIRROR}/${@kde6_rs_real_name(d)}-${KDE6_RS_VERSION}.tar.xz;name=${BPN}"
S = "${WORKDIR}/${@kde6_rs_real_name(d)}-${KDE6_RS_VERSION}"

DEPENDS:append = " \
    kpackage-native \
"

PACKAGECONFIG:append = " \
    ki18n \
    kdoctools \
"
