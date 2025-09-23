# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2024 Maxime Roussin-Bélanger <maxime.roussinbelanger@gmail.com>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Kde6 future Sql"
DESCRIPTION = "A non-blocking database framework for Qt"
HOMEPAGE = "https://invent.kde.org/libraries/futuresql"
LICENSE = "(LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL)"
LIC_FILES_CHKSUM = " \
    file://LICENSES/LGPL-2.1-only.txt;md5=41890f71f740302b785c27661123bff5 \
    file://LICENSES/LGPL-3.0-only.txt;md5=852ecadc0ac7e6f4d7144d5544a3815b \
    file://LICENSES/LicenseRef-KDE-Accepted-LGPL.txt;md5=e4b79a181b6483b37d39a27f4d75e60a \
"
PR = "r0"

inherit kde6-ecm

SRC_URI = "git://invent.kde.org/libraries/futuresql;nobranch=1;protocol=https"
SRCREV = "0fb3e7e202ea4d6cf170b3ea7edd6ea955648c50"
S = "${WORKDIR}/git"

DEPENDS:append = " \
    qcoro6 \
"

# all packageconfig is set already in parent classes.
