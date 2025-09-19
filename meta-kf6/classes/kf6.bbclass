inherit ${@bb.utils.contains('PACKAGECONFIG','ki18n', ' gettext  kde6-ecm kf6-checksums', ' kde6-ecm kf6-checksums',d)}

inherit kde6-ecm 
inherit kf6-checksums
inherit kf6-packageconfig

KF6_MODULE ?= "${BPN}"
PV = "${KDE6_KF6_VERSION}"
PR = "r0"

# get the real name use the map if not in map remove the appended "6"
def kf6_real_name(d, name=None):
    override_map = {
        "oxygen-icons-kf6": "oxygen-icons",
        "phonon-backend-vlc-kf6": "phonon-backend-vlc",
        "libkexiv2-kf6": "libkexiv2",
        "phonon-kf6": "phonon",
        "plasma-wayland-protocols-kf6": "plasma-wayland-protocols",
        "qca-kf6": "qca",
        "zxing-cpp-kf6": "zxing-cpp",
        "libdmtx-kf6": "libdmtx",
        "ecm-kf6": "extra-cmake-modules",
    }

    bpn = name or d.getVar("BPN") or ""
    if bpn in override_map:
        return override_map[bpn]
    if bpn.endswith("6"):
        return bpn[:-1]
    return bpn

SRC_URI ?= "${KDE6_KF6_MIRROR}/${@kf6_real_name(d)}-${KDE6_KF6_VERSION}.tar.xz;name=${BPN}"
S = "${WORKDIR}/${@kf6_real_name(d)}-${KDE6_KF6_VERSION}"

# remove self
PACKAGECONFIG:remove = "${@kf6_real_name(d)}"

EXTRA_OECMAKE:append:class-target = " \
    -DKF6_HOST_TOOLING=${STAGING_LIBDIR_NATIVE}/cmake \
"
EXTRA_OECMAKE:append:class-nativesdk = " \
    -DKF6_HOST_TOOLING=${STAGING_LIBDIR_NATIVE}/cmake \
"
