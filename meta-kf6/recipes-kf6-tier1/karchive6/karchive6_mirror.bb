SUMMARY = "File compression and archive handling framework for KDE and Qt applications"
DESCRIPTION = "KArchive provides a framework for reading, creating, and \
managing compressed archives in KDE and Qt applications. It supports \
common archive formats including ZIP, TAR, and 7-Zip, along with \
compression algorithms such as gzip, bzip2, LZMA, and Zstandard. \
The framework provides reusable APIs for accessing archive contents, \
extracting files, and creating compressed archives."

HOMEPAGE = "https://api.kde.org/frameworks/karchive/html/index.html"
SECTION = "libs"

LICENSE = "BSD-2-Clause AND LGPL-2.0-or-later"
REUSE_LICENSECHECK_WHITELIST = "LGPL-2.0 LGPL-3.0 LicenseRef-KDE-Accepted-LGPL"

inherit kf6

PACKAGECONFIG[bzip] = "-DWITH_BZIP2=ON,-DWITH_BZIP2=OFF, bzip2"
PACKAGECONFIG[lzma] = "-DWITH_LIBLZMA=ON,-DWITH_LIBLZMA=OFF, xz"
PACKAGECONFIG[openssl] = "-DWITH_OPENSSL=ON, -DWITH_OPENSSL=OFF, openssl"
PACKAGECONFIG[zstd] = "-DWITH_LIBZSTD=ON,-DWITH_LIBZSTD=OFF, zstd"

PACKAGECONFIG:append = " \
    bzip \
    lzma \
    openssl \
    zstd \
"
## FIXME SPLIT THE PACKAGES

BBCLASSEXTEND = "native nativesdk"
