DESCRIPTION = "PulseAudio Qt Bindings - libpulse bindings for Qt"
HOMEPAGE = "https://invent.kde.org/libraries/pulseaudio-qt"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-only.txt;md5=fabba2a3bfeb22a6483d44e9ae824d3f"
PR = "r0"

inherit kde6-ecm

SRC_URI = "git://invent.kde.org/libraries/pulseaudio-qt;nobranch=1;protocol=https"
SRCREV = "7ec54e778fcaefadb4c62da142e536d1d7346803"

DEPENDS:append = " \
    pulseaudio \
    qtdeclarative6 \
"

RDEPENDS:${PN} += " \
    pulseaudio-module-gsettings \
"
