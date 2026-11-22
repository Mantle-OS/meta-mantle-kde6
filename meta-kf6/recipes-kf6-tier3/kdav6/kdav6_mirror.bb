SUMMARY = "WebDAV protocol support for KDE and Qt applications"
DESCRIPTION = "KDAV provides a framework for accessing and managing remote \
calendar, contact, and other personal information resources through \
WebDAV-based protocols. It supports CalDAV and CardDAV for synchronizing \
calendar events, tasks, and address book data with remote servers. \
The framework integrates with KIO and KJob to provide asynchronous \
network operations, authentication, and standardized resource access."

HOMEPAGE = "https://api.kde.org/frameworks/kdav/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.0-or-later"

inherit kf6

DEPENDS:append = " \
    kio6 \
"

PACKAGECONFIG:append = " \
    kcoreaddons \
    ki18n \
    kconfig \
"
