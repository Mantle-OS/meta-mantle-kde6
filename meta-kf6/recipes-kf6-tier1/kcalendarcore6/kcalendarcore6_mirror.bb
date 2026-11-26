SUMMARY = "Calendar data management framework for KDE and Qt applications"
DESCRIPTION = "KCalendarCore provides a framework for creating, managing, \
and processing calendar data in KDE and Qt applications. It supports \
events, tasks, journals, recurrence rules, time zones, and calendar \
serialization using the iCalendar standard. Built on libical, the \
framework provides reusable APIs for scheduling, calendar synchronization, \
and personal information management applications."

HOMEPAGE = "https://api.kde.org/frameworks/kcalendarcore/html/index.html"
SECTION = "libs"

LICENSE = "BSD-3-Clause AND LGPL-2.0-or-later"
REUSE_LICENSECHECK_WHITELIST = "LGPL-3.0-or-later"

inherit kf6

PACKAGECONFIG[qml] =  "-DUSE_QML=ON, -DUSE_QML=OFF, qtdeclarative6"
PACKAGECONFIG[py-bindings] = "-DBUILD_PYTHON_BINDINGS=ON, -DBUILD_PYTHON_BINDINGS=OFF, \
    python3-native \
    python3-pyside6 \
    python3-shiboken6-native \
"


PACKAGECONFIG:append = " qml"
DEPENDS:append = " libical"
