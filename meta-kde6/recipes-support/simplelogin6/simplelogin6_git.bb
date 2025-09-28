# SPDX-FileCopyrightText: 2019 Volker Krause <vkrause@kde.org>
# SPDX-License-Identifier: MIT
DESCRIPTION = "super simple sans display display desktop manager mockup mitigating mir"
HOMEPAGE = "https://github.com/bhush9/simplelogin"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4641e94ec96f98fabc56ff9cc48be14b"

inherit kde6-cmake
inherit features_check
REQUIRED_DISTRO_FEATURES = "systemd"
inherit systemd

SRC_URI = " \
    git://invent.kde.org/bshah/simplelogin.git;nobranch=1;protocol=https \
    file://0001-qt6.patch \
"
SRCREV = "ffa841ec9766e4428f2f960b966bcc5b4e7e4982"
S = "${WORKDIR}/git"

DEPENDS:append = " \
    libpam \
"

SYSTEMD_SERVICE:${PN} = "${BPN}.service"


## note not a fan of this has memory leaks and needs as facelift.
