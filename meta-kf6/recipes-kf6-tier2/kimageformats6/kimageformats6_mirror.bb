SUMMARY = "Additional image format plugins for KDE and Qt applications"
DESCRIPTION = "KImageFormats extends Qt's image loading and saving capabilities \
through a collection of image format plugins. It provides support for \
additional raster and specialized image formats, including OpenEXR, \
AVIF, HEIF, JPEG XL, JPEG 2000, and RAW image formats. The framework \
integrates with Qt's image I/O system, enabling KDE and Qt applications \
to read and write supported image formats without requiring application-specific \
decoders."
BUGTRACKER = "https://bugs.kde.org/"
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
PACKAGECONFIG[dds] = "-DKIMAGEFORMATS_DDS=ON, -DKIMAGEFORMATS_DDS=OFF"
PACKAGECONFIG[heif] = "-DKIMAGEFORMATS_HEIF=ON, -DKIMAGEFORMATS_HEIF=OFF"
PACKAGECONFIG[jkl] = "-DKIMAGEFORMATS_JXL=ON, -DKIMAGEFORMATS_JXL=OFF, libjxl" 
PACKAGECONFIG[jp2] = "-DKIMAGEFORMATS_JP2=ON, -DKIMAGEFORMATS_JP2=OFF, libjpeg-turbo"
PACKAGECONFIG[libraw] = ", , libraw"

# LibJXR maybe later
# option(KIMAGEFORMATS_EXPOSE_LIBVER "Store the version of the underlying library in the image metadata" OFF)
PACKAGECONFIG[embed-version] = "-DKIMAGEFORMATS_EXPOSE_LIBVER=ON, -DKIMAGEFORMATS_EXPOSE_LIBVER=OFF"

PACKAGECONFIG:append = " \
    openexr \
    libavif \
    jkl \
    jp2 \
    libraw \
"
