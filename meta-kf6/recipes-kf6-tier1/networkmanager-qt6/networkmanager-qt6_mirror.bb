SUMMARY = "Qt bindings for the NetworkManager API"
DESCRIPTION = "NetworkManagerQt provides a Qt-based interface to the \
NetworkManager API for managing network connections and devices. It \
supports discovering network interfaces, monitoring connection states, \
configuring wired and wireless networks, and managing network settings \
through D-Bus. The framework enables KDE and Qt applications to integrate \
with NetworkManager without directly interacting with its D-Bus API."
HOMEPAGE = "https://api.kde.org/frameworks/networkmanager-qt/html/index.html"
SECTION = "libs"

LICENSE = "BSD-3-Clause AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"

inherit kf6

## TODO nm vs conman in packaging

DEPENDS += " \
    qtbase6 \
    qtdeclarative6 \
    networkmanager \
    glib-2.0 \
"

RDEPENDS:${PN} += " networkmanager"

