SUMMARY = "Pseudo-terminal abstraction library for KDE and Qt applications"
DESCRIPTION = "KPty provides a platform-independent interface for managing \
pseudo-terminal (PTY) devices in KDE and Qt applications. It supports \
creating and controlling pseudo-terminal sessions, managing terminal \
input and output, and configuring terminal attributes. The framework \
simplifies interaction with Unix terminal facilities and provides \
reusable functionality for terminal emulators, shells, and applications \
requiring interactive process communication."

HOMEPAGE = "https://api.kde.org/frameworks/kpty/html/index.html"
SECTION = "libs"

LICENSE = "BSD-3-Clause AND LGPL-2.0-or-later"

inherit kf6

PACKAGECONFIG[utempter] = " , -DCMAKE_DISABLE_FIND_PACKAGE_UTEMPTER=ON, libutempter"

PACKAGECONFIG:append = " \
    kcoreaddons \
    ki18n \
"
