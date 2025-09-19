DEPENDS:append = " kcoreaddons6 kcoreaddons6-native"
EXTRA_OECMAKE:append = " \
    -DKF6_HOST_TOOLING=${STAGING_LIBDIR_NATIVE}/cmake \
    -DCMAKE_SYSROOT=${RECIPE_SYSROOT} \
"
