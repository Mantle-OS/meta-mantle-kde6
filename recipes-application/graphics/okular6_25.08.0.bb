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

inherit cmake_kdeapp
inherit kf6-kcoreaddons kf6-kconfig kf6-kauth

SRC_URI = "https://download.kde.org/stable/release-service/25.08.0/src/okular-25.08.0.tar.xz"
SRC_URI[sha256sum] = "8c70c448e8aa8c55c3623024ee1d70930888672a503c40c60af2de702ffe64fc"

DEPENDS += " \
    libzip \
    poppler \
    qtbase6 \
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

# note: okularkirigami is the dedicated mobile version
PACKAGES += " \
    ${PN}-desktop \
    ${PN}-shared \
"

FILES:${PN} = " \
    ${bindir}/okularkirigami \
    ${datadir}/applications/org.kde.okular.kirigami.desktop \
"

FILES:${PN}-shared = " \
    ${sysconfdir}/xdg/okular.categories \
    ${datadir}/okular/pics \
    ${datadir}/okular/icons \
    ${datadir}/config.kcfg \
    ${datadir}/icons \
    ${datadir}/kconf_update \
    ${datadir}/metainfo \
    ${datadir}/okular \
    ${datadir}/applications/org.kde.mobile.okular_xps.desktop \
    ${datadir}/applications/okularApplication_dvi.desktop \
    ${datadir}/applications/okularApplication_fax.desktop \
    ${datadir}/applications/okularApplication_xps.desktop \
    ${datadir}/applications/okularApplication_fb.desktop \
    ${datadir}/applications/okularApplication_txt.desktop \
    ${datadir}/applications/org.kde.mobile.okular_fax.desktop \
    ${datadir}/applications/org.kde.mobile.okular_fb.desktop \
    ${datadir}/applications/org.kde.mobile.okular_comicbook.desktop \
    ${datadir}/applications/okularApplication_tiff.desktop \
    ${datadir}/applications/org.kde.mobile.okular_txt.desktop \
    ${datadir}/applications/okularApplication_comicbook.desktop \
    ${datadir}/applications/org.kde.mobile.okular_pdf.desktop \
    ${datadir}/applications/okularApplication_plucker.desktop \
    ${datadir}/applications/org.kde.mobile.okular_tiff.desktop \
    ${datadir}/applications/org.kde.mobile.okular_dvi.desktop \
    ${datadir}/applications/org.kde.mobile.okular_plucker.desktop \
    ${datadir}/applications/okularApplication_pdf.desktop \
    ${datadir}/applications/org.kde.mobile.okular_kimgio.desktop \
    ${datadir}/applications/okularApplication_kimgio.desktop \
    ${libdir} \
"

FILES:${PN}-desktop = " \
    ${bindir}/okular \
    ${datadir}/applications/org.kde.okular.desktop \
"

RDEPENDS:${PN} += " \
    ${PN}-shared \
    kirigami6-addons6 \
"

RDEPENDS:${PN}-desktop += " \
    ${PN}-shared \
"
