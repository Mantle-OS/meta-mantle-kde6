# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2019-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2024 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Okular"
HOMEPAGE = "https://okular.kde.org/"
LICENSE = "GPL-2.0-or-later & GPL-3.0-only"
LIC_FILES_CHKSUM = " \
    file://LICENSES/GPL-2.0-or-later.txt;md5=9e2385fe012386d34dcc5c9863070881 \
    file://LICENSES/GPL-3.0-or-later.txt;md5=49fc03046e56a282c0c743b5d3a55b7c \
"

inherit rs6

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
"

DEPENDS:append = " \
    libzip \
    poppler \
    \
    libkexiv2-kf6 \
    kirigami6 \
    kparts6 \
    threadweaver6 \
    kwallet6 \
    plasma-activities6 \
    kpty6 \
    phonon-kf6 \
    purpose6 \
    qca-kf6 \
"

EXTRA_OECMAKE += " \
    -DOKULAR_UI=both \
    -DFORCE_NOT_REQUIRED_DEPENDENCIES='KExiv2Qt6;LibSpectre;DjVuLibre;EPub;Discount' \
"

# dirty hack to force the mobile version and hide the desktop one
do_install:append() {
    sed -i 's/Exec=okular %U$/Exec=okularkirigami %U/g' ${D}${datadir}/applications/org.kde.okular.desktop
    sed -i 's/Exec=okular %U$/Exec=okularkirigami %U/g' ${D}${datadir}/applications/okularApplication_*.desktop
}

RDEPENDS:${PN} += " \
    ${PN}-shared \
    kirigami6-addons6 \
"
