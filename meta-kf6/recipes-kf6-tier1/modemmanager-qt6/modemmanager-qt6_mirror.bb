SUMMARY = "Qt bindings for the ModemManager API"
DESCRIPTION = "ModemManagerQt provides a Qt-based interface to the \
ModemManager API for managing mobile broadband devices and connections. \
It supports detecting and controlling cellular modems, monitoring signal \
strength and registration status, managing SIM cards, and accessing mobile \
network services through D-Bus. The framework enables KDE and Qt \
applications to integrate cellular connectivity without directly \
interacting with the ModemManager D-Bus API."
HOMEPAGE = "https://api.kde.org/frameworks/modemmanager-qt/html/index.html"
SECTION = "libs"
LICENSE = "BSD-3-Clause AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"

inherit kf6 features_check
REQUIRED_DISTRO_FEATURES = "3g"

DEPENDS:append = " modemmanager"
RDEPENDS:${PN}:append = " modemmanager"
