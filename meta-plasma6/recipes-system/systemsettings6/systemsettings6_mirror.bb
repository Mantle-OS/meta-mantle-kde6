# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "System Settings"
HOMEPAGE = "https://invent.kde.org/plasma/systemsettings"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=9e2385fe012386d34dcc5c9863070881"

inherit plasma6

DEPENDS:append = " \
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
