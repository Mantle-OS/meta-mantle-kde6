# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2018-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KScreen"
HOMEPAGE = ""
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=9e2385fe012386d34dcc5c9863070881"

inherit plasma6

DEPENDS:append = " \
    kconfigwidgets6 \
    kdbusaddons6 \
    kglobalaccel6 \
    kwidgetsaddons6 \
    kxmlgui6 \
    layer-shell-qt6-kde6 \
    libkscreen6 \
    libplasma6 \
    \
    qtdeclarative6 \
    qtsensors6 \
    qtwayland6-native \
"
