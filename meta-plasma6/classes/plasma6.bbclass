inherit kde6-ecm

inherit kf6-packageconfig

inherit plasma6-checksums
PLASMA6_MODULE ?= "${BPN}"

PV = "${KDE6_PLASMA_VERSION}"
PR = "r0"

def plasma_real_name(d, name=None):
    override_map = {
        'kde6-cli-tools-kde6' : 'kde-cli-tools',
    }
    bpn = name or d.getVar("BPN") or ""
    if bpn in override_map:
        return override_map[bpn]
    if bpn.endswith("6"):
        return bpn[:-1]
    return bpn

SRC_URI = "${KDE6_PLASMA_MIRROR}/${@plasma_real_name(d)}-${PV}.tar.xz;name=${BPN}"
S = "${WORKDIR}/${@plasma_real_name(d)}-${PV}" 

DEPENDS:append = " \
    kpackage6-native \
    kcmutils6 \
"

EXTRA_OECMAKE:append:class-target = " -DKF6_HOST_TOOLING=${STAGING_LIBDIR_NATIVE}/cmake"
EXTRA_OECMAKE:append:class-nativesdk = " -DKF6_HOST_TOOLING=${STAGING_LIBDIR_NATIVE}/cmake"

PACKAGECONFIG:append = " \
    ki18n \
    kcoreaddons \
    kconfig \
    kauth \
    kdoctools \
"
