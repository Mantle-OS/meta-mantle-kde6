SUMMARY = "vCard contact management framework for KDE and Qt applications"
DESCRIPTION = "KContacts provides a framework for creating, managing, \
and exchanging contact information in KDE and Qt applications. It \
supports parsing and generating vCard data, including names, addresses, \
phone numbers, email addresses, and other contact properties. The \
framework provides structured APIs for contact management, serialization, \
and integration with address books and personal information management \
applications, along with QML support."

HOMEPAGE = "https://api.kde.org/frameworks/kcontacts/html/index.html"
BUGTRACKER = "https://bugs.kde.org/"
SECTION = "libs"
LICENSE = "BSD-3-Clause AND LGPL-2.0-or-later AND MIT AND Unicode-DFS-2016"

inherit kf6

DEPENDS:append = " kcodecs6"

PACKAGECONFIG[qml] = "-DUSE_QML=ON, -DUSE_QML=OFF, qtdeclarative6"
PACKAGECONFIG[fuzzers] = "-DBUILD_FUZZERS=ON,-DBUILD_FUZZERS=OFF"

PACKAGECONFIG:append = " \
    kconfig \
    kcoreaddons \
    ki18n \
"

PACKAGECONFIG:append:class-target = " \
    qml \
"
