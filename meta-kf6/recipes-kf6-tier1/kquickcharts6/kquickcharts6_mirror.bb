SUMMARY = "High-performance Qt Quick charting components for KDE applications"
DESCRIPTION = "KQuickCharts provides a Qt Quick module for displaying \
high-performance charts and data visualizations in KDE and Qt applications. \
It supports line, bar, and pie charts, along with customizable axes, \
legends, and data models. The framework uses hardware-accelerated rendering \
to efficiently visualize dynamic and large datasets through QML components."

HOMEPAGE = "https://api.kde.org/frameworks/kquickcharts/html/index.html"
SECTION = "libs"

LICENSE = "MIT AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"

inherit kf6 features_check

## Lets look a bit deeper into this on the next pass.
REQUIRED_DISTRO_FEATURES = "opengl"

DEPENDS:append = " \
    qtdeclarative6 \
    qttools6-native \
    qtshadertools6-native \
"
