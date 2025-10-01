# vorbris .....

PACKAGECONFIG:append:class-native = " alsa"
PACKAGECONFIG:remove:class-native = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'alsa pulseaudio', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'gtk gtk3', '', d)} \
"

PACKAGECONFIG:append:class-nativesdk = " alsa"
PACKAGECONFIG:remove:class-native-sdk = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'alsa pulseaudio', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'gtk gtk3', '', d)} \
"
BBCLASSEXTEND = "native nativesdk"
