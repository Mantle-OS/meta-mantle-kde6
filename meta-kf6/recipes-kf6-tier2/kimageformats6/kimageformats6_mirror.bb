SUMMARY = "Additional image format plugins for KDE and Qt applications"
DESCRIPTION = "KImageFormats extends Qt's image loading and saving capabilities \
through a collection of image format plugins. It provides support for \
additional raster and specialized image formats, including OpenEXR, \
AVIF, HEIF, JPEG XL, JPEG 2000, and RAW image formats. The framework \
integrates with Qt's image I/O system, enabling KDE and Qt applications \
to read and write supported image formats without requiring application-specific \
decoders."

HOMEPAGE = "https://api.kde.org/frameworks/kimageformats/html/index.html"
SECTION = "libs"

LICENSE = "BSD-2-Clause AND LGPL-2.0-or-later AND LGPL-2.1-or-later AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"

inherit kf6

DEPENDS:append = " \
    karchive6 \
    qtimageformats6 \
"

PACKAGECONFIG[openexr] =  ", , openexr"
PACKAGECONFIG[libavif] = ", , libavif"
PACKAGECONFIG[dds] = "-DKIMAGEFORMATS_DDS=ON, -DKIMAGEFORMATS_DDS=OFF, "
PACKAGECONFIG[heif] = "-DKIMAGEFORMATS_HEIF=ON, -DKIMAGEFORMATS_HEIF=OFF, "
PACKAGECONFIG[jkl] = "-DKIMAGEFORMATS_JXL=ON, -DKIMAGEFORMATS_JXL=OFF, libjxl" 
PACKAGECONFIG[jp2] = "-DKIMAGEFORMATS_JP2=ON, -DKIMAGEFORMATS_JP2=OFF, libjpeg-turbo"
PACKAGECONFIG[libraw] = ", , libraw"
## TODO
#PACKAGECONFIG[jxr] = "-DKIMAGEFORMATS_JXR=ON, -DKIMAGEFORMATS_JXR=OFF, libjxr"

PACKAGECONFIG:append = " \
    openexr \
    libavif \
    jkl \
    jp2 \
    libraw \
"
