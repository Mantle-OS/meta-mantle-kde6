inherit mime mime-xdg
inherit kde6-cmake 
inherit kde6-licenses

DEPENDS:append = " \
    ecm-kf6 \
"

DEPENDS:append:class-nativesdk = " \
    ecm-kf6-native \
"
