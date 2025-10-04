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

# spectre6 DjVuLibre QMobipocket6 Discount epub
DEPENDS:append = " \
    qtdeclarative6 \
    qtsvg6 \
    qtspeech6 \
    \
    karchive6 \
    kbookmarks6 \
    kcompletion6 \
    kconfigwidgets6 \
    kio6 \
    threadweaver6 \
    kwindowsystem6 \
    kxmlgui6 \
    kwallet6 \
    kdoctools6 kdoctools6-native \
    kparts6 \
    kcolorscheme6 \
    kcrash6 \
    kiconthemes6 \
    kitemviews6 \
    ktextwidgets6 \
    kwidgetsaddons6 \
    purpose6 \
    kirigami6 kirigami6-addons \
    plasma-activities6 \
    phonon-kf6 \
    kpty6 \
    \
    zlib \
    poppler \
    freetype \
    tiff \
    ebook-tools libe-book \
    libkexiv2-kf6 \
"

# kpty6 qca-kf6 

PACKAGECONFIG[dbus] = "-DUSE_DBUS=ON,m -DUSE_DBUS=OFF, "
PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    kauth \
    dbus \
"

EXTRA_OECMAKE += " \
    -DOKULAR_UI=both \
    -DFORCE_NOT_REQUIRED_DEPENDENCIES='KExiv2Qt6;LibSpectre;DjVuLibre;Discount' \
"

FILES:${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/config.kcfg \
    ${datadir}/okular \
"
