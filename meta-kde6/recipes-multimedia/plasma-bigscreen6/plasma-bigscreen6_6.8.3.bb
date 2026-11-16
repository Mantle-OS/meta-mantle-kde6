DESCRIPTION = "Plasma Bigscreen"
HOMEPAGE = "https://invent.kde.org/plasma/plasma-bigscreen"
LICENSE = "(GPL-2.0-only OR GPL-3.0-only OR LicenseRef-KDE-Accepted-GPL) AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"
LIC_FILES_CHKSUM = " \
    file://LICENSES/LGPL-2.1-only.txt;md5=fabba2a3bfeb22a6483d44e9ae824d3f \
    file://LICENSES/LGPL-3.0-only.txt;md5=c51d3eef3be114124d11349ca0d7e117 \
    file://LICENSES/LicenseRef-KDE-Accepted-GPL.txt;md5=b4c280013bbbadfbe92219498dc5228c \
"

inherit plasma6

SRC_URI = "git://invent.kde.org/plasma/plasma-bigscreen;protocol=https;branch=Plasma/6.8;"
SRCREV = "4e3db7de7835c7037fb1bfb2dbec9818d3d6ba1f"

DEPENDS:append = " \
    plasma-activities6 \
    plasma-activities6-stats6 \
    plasma-workspace6 \
    kirigami6 \
    kdeclarative6 \
    knotifications6 \
    kio6 \
    kwayland6 \
    kwindowsystem6 \
    \
    qtmultimedia6 \
"

RDEPENDS:${PN} += " \
    mycroft \
"
