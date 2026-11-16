DESCRIPTION = "Plasma Mobile Settings"
HOMEPAGE = "https://invent.kde.org/plasma-mobile/plasma-settings"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881"

inherit plasma6

SRC_URI = "git://invent.kde.org/plasma-mobile/plasma-settings.git;nobranch=1;protocol=https"
SRCREV = "8f759edb8ad8c75ccc95c8021e98876de21d2de0"

DEPENDS:append = " \
    kitemmodels6 \
    modemmanager-qt6 \
    networkmanager-qt6 \
    kirigami6-addons \
    libplasma6 \
"
