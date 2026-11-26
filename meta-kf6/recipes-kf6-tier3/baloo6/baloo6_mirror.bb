SUMMARY = "File indexing and search framework for KDE and Qt applications"
DESCRIPTION = "Baloo provides a framework for indexing and searching files \
and their metadata in KDE and Qt applications. It supports full-text \
search, metadata extraction, and efficient content retrieval using \
an LMDB-backed database. The framework includes background indexing \
services, filesystem monitoring, and integration with KDE Plasma \
and Dolphin, enabling fast file discovery and desktop search."

HOMEPAGE = "https://api.kde.org/frameworks/baloo/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.1-only"

inherit kf6

DEPENDS:append = " \
    qtdeclarative6 \
    libxml2 \
    lmdb \
    \
    kdbusaddons6 \
    kidletime6 \
    solid6 \
    kfilemetadata6 \
    kcrash6 \
    kio6 \
"

DEPENDS:class-target += " \
    libxml2-native \
    inotify-tools \
"

PACKAGECONFIG[experimental] = "-DBUILD_EXPERIMENTAL=ON, -DBUILD_EXPERIMENTAL=OFF, "
PACKAGECONFIG[indexer] = "-DBUILD_INDEXER_SERVICE=on, -DBUILD_INDEXER_SERVICE=OFF, "

PACKAGECONFIG:append = " \
    kauth \
    kconfig \
    kcoreaddons \
    ki18n \
    indexer \
"

RDEPENDS:${PN} += " \
    lmdb \
"

FILES:${PN}:append = " \
    ${datadir}/config.kcfg/baloosettings.kcfg \
"
