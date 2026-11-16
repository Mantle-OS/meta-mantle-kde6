SUMMARY = "KPeople VCard Backend"
DESCRIPTION = "Expose VCard contacts to KPeople"
HOMEPAGE = "https://invent.kde.org/pim/kpeoplevcard"
SECTION = "libs"
LICENSE = "BSD-3-Clause AND LGPL-2.1-or-later"
LIC_FILES_CHKSUM = " \
    file://LICENSES/LGPL-2.1-or-later.txt;md5=147a320ed8b16b036829a0c71d424153 \
"

PV = "0.2"
PR = "r0"

inherit kde6-ecm

SRC_URI = "git://invent.kde.org/pim/kpeoplevcard;nobranch=1;protocol=https"
SRCREV = "d4acaa3038f6b030d0d6390e628c39e9f8d87903"

DEPENDS:append = " \
    kpeople6 \
    kcontacts6 \
"

PACKAGECONFIG:append  = " \
    kconfig \
    kcoreaddons \
    ki18n \
"
