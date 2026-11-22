SUMMARY = "Qt bindings for the BlueZ 5 Bluetooth API"
DESCRIPTION = "BluezQt provides a Qt-based interface to the BlueZ 5 D-Bus \
API for managing Bluetooth devices and services in KDE and Qt applications. \
It supports Bluetooth adapter discovery, device pairing, connection \
management, service discovery, and device property monitoring. \
The framework provides asynchronous APIs and QML integration, enabling \
applications to interact with the Linux Bluetooth stack without directly \
managing D-Bus communication."

HOMEPAGE = "https://api.kde.org/frameworks/bluez-qt/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.1-or-later AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"

inherit kf6 features_check
REQUIRED_DISTRO_FEATURES = "bluetooth"

PACKAGECONFIG:append = " qml"
PACKAGECONFIG[qml] =  ",, qtdeclarative6"

RDEPENDS:${PN} += " \
    bluez5 \
    bluez5-obex \
"
