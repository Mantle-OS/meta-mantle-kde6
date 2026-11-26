SUMMARY = "Barcode generation and scanning framework for KDE and Qt applications"
DESCRIPTION = "Prison provides a unified framework for generating and scanning \
barcodes in KDE and Qt applications. It supports multiple barcode formats, \
including QR codes, Data Matrix, and other one-dimensional and two-dimensional \
symbologies through libraries such as QRencode, libdmtx, and ZXing-C++. \
The framework provides Qt Widgets and QML integration, along with multimedia \
support for scanning barcodes using camera devices."
HOMEPAGE = "https://api.kde.org/frameworks/prison/html/index.html"
SECTION = "libs"
LICENSE = "BSD-3-Clause AND MIT"

inherit kf6
REUSE_LICENSECHECK_WHITELIST = "CC0-1.0"

DEPENDS:append = " qrencode"
PACKAGECONFIG[dmtx] = "-DWITH_DMTX=ON, -DWITH_DMTX=OFF, libdmtx"
PACKAGECONFIG[qml] = "-DWITH_QUICK=ON, -DWITH_QUICK=OFF, qtdeclarative6"
PACKAGECONFIG[multimedia] = "-DWITH_MULTIMEDIA=ON,-DWITH_MULTIMEDIA=OFF , qtmultimedia6"
PACKAGECONFIG[zxing] = "-DWITH_ZXING=ON, -DWITH_ZXING=OFF, zxing-cpp"


PACKAGECONFIG:append = " \
    qml \
    multimedia \
    dmtx \
    zxing \
"

PACKAGECONFIG:remove:class-native = " qml multimedia"
BBCLASSEXTEND = "native nativesdk"
