SUMMARY = "File metadata and text extraction framework for KDE and Qt applications"
DESCRIPTION = "KFileMetaData provides a framework for extracting metadata \
and textual content from files in KDE and Qt applications. It supports \
a wide range of file formats, including documents, images, audio, video, \
and electronic books. The framework uses extensible extraction plugins \
to retrieve information such as titles, authors, timestamps, media \
properties, and embedded text, enabling file indexing, content searching, \
and metadata management."

HOMEPAGE = "https://api.kde.org/frameworks/kfilemetadata/html/index.html"
SECTION = "text"

LICENSE = "BSD-3-Clause AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"

## TODO Look fully into this one

inherit kf6 features_check
REQUIRED_DISTRO_FEATURES += "xattr"

DEPENDS:append = " \
    karchive6 \
    kcodecs6 \
    libkexiv2-kf6 \
    ffmpeg \
    poppler \
    taglib \
    ebook-tools \
    catdoc \
"

PACKAGECONFIG:append = " \
    kcoreaddons \
    kconfig \
    ki18n \
"
