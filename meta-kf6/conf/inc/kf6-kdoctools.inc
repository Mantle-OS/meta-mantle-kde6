do_compile:prepend() {
    export XDG_DATA_DIRS=${STAGING_DATADIR}:$XDG_DATA_DIRS
}

DEPENDS:append = " \
    kdoctools6 kdoctools6-native \
    libxslt-native \
    karchive-native \
"

EXTRA_OECMAKE:append = " -DKF6_HOST_TOOLING=${STAGING_LIBDIR_NATIVE}/cmake"
