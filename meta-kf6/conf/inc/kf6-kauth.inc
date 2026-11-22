inherit features_check
REQUIRED_DISTRO_FEATURES = "polkit"
DEPENDS:append:class-target = " \
    kauth6 \
    kauth6-native \
    polkit-qt-1 \
"

EXTRA_OECMAKE:append = " \
    -DKF6_HOST_TOOLING=${STAGING_LIBDIR_NATIVE}/cmake \
"
