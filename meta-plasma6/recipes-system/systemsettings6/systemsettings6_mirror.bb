# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "System Settings"
HOMEPAGE = "https://invent.kde.org/plasma/systemsettings"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=9e2385fe012386d34dcc5c9863070881"
PR = "r0"

inherit cmake_plasma

SRC_URI += "https://download.kde.org/stable/plasma/6.4.4/systemsettings-6.4.4.tar.xz"
SRC_URI[sha256sum] = "6b14788bf19397f58f0231cb9ccd57c3218ca71afd2d9f1c745581e13fa57748"
DEPENDS += " \
    kcmutils6 \
    kconfig6 \
    kdbusaddons6 \
    kiconthemes6 \
    kio6 \
    kirigami6 \
    kitemviews6 \
    kitemmodels6 \
    kjobwidgets6 \
    krunner6 \
    kservice6 \
    kwidgetsaddons6 \
    kwindowsystem6 \
    kxmlgui6 \
    \
    plasma-activities6 \
"


FILES:${PN} += " \
    ${datadir}/kpackage/genericqml/org.kde.systemsettings.sidebar \
    ${datadir}/kglobalaccel/systemsettings.desktop \
    ${datadir}/zsh/site-functions/_systemsettings \
"
