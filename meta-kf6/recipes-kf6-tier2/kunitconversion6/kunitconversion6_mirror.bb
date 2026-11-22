SUMMARY = "Unit conversion framework for KDE and Qt applications"
DESCRIPTION = "KUnitConversion provides a framework for converting values \
between different units of measurement in KDE and Qt applications. It \
supports a wide range of unit categories, including length, area, volume, \
mass, temperature, time, speed, energy, pressure, and data storage. \
The framework provides localized unit names, symbols, and conversion \
functions, enabling applications to present measurements in different \
unit systems and languages."

HOMEPAGE = "https://api.kde.org/frameworks/kunitconversion/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.0-or-later"

inherit kf6

DEPENDS:append = " \
    ki18n6 \
    ki18n6-native \
" 

PACKAGECONFIG[py-bindings] = "BUILD_PYTHON_BINDINGS=ON, -DBUILD_PYTHON_BINDINGS=OFF,\
    python3-pyside6-native \
    python3-native \
    python3-shiboken6 \
"
