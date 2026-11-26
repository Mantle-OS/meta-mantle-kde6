SUMMARY = "KDE Oxygen icon theme"
DESCRIPTION = "Provides the Oxygen icon theme, including application, action, \
device, status, MIME type and system icons for KDE and other desktop \
environments."
HOMEPAGE = "https://invent.kde.org/frameworks/oxygen-icons"
BUGTRACKER = "https://bugs.kde.org/"
SECTION = "x11/icons"

LICENSE = "LGPL-3.0-or-later"

inherit kf6 gtk-icon-cache

DEPENDS:append = " hicolor-icon-theme"
