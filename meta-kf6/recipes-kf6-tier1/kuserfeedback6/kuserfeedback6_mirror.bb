SUMMARY = "User feedback and telemetry framework for KDE and Qt applications"
DESCRIPTION = "KUserFeedback provides a framework for collecting and managing \
user feedback, application usage statistics, and telemetry data in KDE and \
Qt applications. It supports configurable data collection, user consent, \
surveys, and anonymous usage reporting. The framework includes client-side \
components and server-side tools for analyzing collected feedback, helping \
developers improve software quality while respecting user privacy."
HOMEPAGE = "https://api.kde.org/frameworks/kuserfeedback/html/index.html"
SECTION = "libs"

LICENSE = "BSD-3-Clause AND MIT"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=61be4d272e55cc2609d58596cf084908"

PACKAGECONFIG[survey] = "-DENABLE_SURVEY_TARGET_EXPRESSIONS=ON, -DENABLE_SURVEY_TARGET_EXPRESSIONS=OFF, flex-native bison-native"
PACKAGECONFIG[php] = "-DENABLE_PHP=ON, -DENABLE_PHP=OFF, php"
PACKAGECONFIG[console] = "-DENABLE_CONSOLE=ON, -DENABLE_CONSOLE=OFF"

EXTRA_OECMAKE:append = " \
    -DENABLE_PHP_UNIT=OFF \
"

PACKAGECONFIG:append = " \
    survey \
    console \
"

inherit kf6
DEPENDS:append = " \
    qtsvg6 \
    qtdeclarative6 \
    qtcharts6 \
    qttools6-native \
"
