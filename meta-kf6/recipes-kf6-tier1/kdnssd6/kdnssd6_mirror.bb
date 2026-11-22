SUMMARY = "DNS-based service discovery framework for KDE and Qt applications"
DESCRIPTION = "KDNSSD provides an abstraction layer for DNS-based Service \
Discovery (DNS-SD), enabling KDE and Qt applications to discover and \
advertise services on local networks. It supports automatic detection \
of network services such as printers, file shares, and other devices \
using Zeroconf technologies, including Avahi. The framework simplifies \
service discovery and registration without requiring applications to \
interact directly with platform-specific DNS-SD implementations."

HOMEPAGE = "https://api.kde.org/frameworks/kdnssd/html/index.html"
SECTION = "libs"

LICENSE = "BSD-3-Clause AND LGPL-2.0-or-later"

inherit kf6 features_check
ANY_OF_DISTRO_FEATURES = "zeroconf avahi"

DEPENDS += " avahi"
