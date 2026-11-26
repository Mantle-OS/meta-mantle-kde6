SUMMARY = "KDE API documentation generation tools"
DESCRIPTION = "KApiDox provides Python-based tools for generating API \
reference documentation for KDE and Qt projects. It integrates with \
Doxygen and provides standardized documentation templates, framework \
metadata processing and dependency diagram generation."

HOMEPAGE = "https://invent.kde.org/frameworks/kapidox"
BUGTRACKER = "https://bugs.kde.org/"
SECTION = "devel"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSES/BSD-2-Clause.txt;md5=63d6ee386b8aaba70b1bf15a79ca50f2"

inherit setuptools3 kf6-checksums

KF6_MODULE ?= "${BPN}"
PV = "${KDE6_KF6_VERSION}"

KAPIDOX_JS_SRCREV = "cafdb9dfe7b37b8060e6a4fcc0709bcc80a42d15"
KAPIDOX_JS_URI = "https://raw.githubusercontent.com/KDE/documentation-develop-kde-org/${KAPIDOX_JS_SRCREV}/assets/js"
KAPIDOX_JS_SUBDIR = "kapidox-${PV}/kapidox/data/htmlresource/js"

SRC_URI = " \
    ${KDE6_KF6_MIRROR}/kapidox-${PV}.tar.xz;name=${BPN} \
    ${KAPIDOX_JS_URI}/base.js;name=basejs;subdir=${KAPIDOX_JS_SUBDIR} \
    ${KAPIDOX_JS_URI}/anchor.js;name=anchorjs;subdir=${KAPIDOX_JS_SUBDIR} \
    ${KAPIDOX_JS_URI}/offline-search.js;name=offlinesearchjs;subdir=${KAPIDOX_JS_SUBDIR} \
    file://0001-Add-upstream-debian-patch-for-install-data.patch \
"

SRC_URI[basejs.sha256sum] = "c2a3b0102a6e3d79eb31657cc12679cad2fa2f7e729aad768e6fe27c549ec2f7"
SRC_URI[anchorjs.sha256sum] = "dc2459cf14e757bb600a10be6422e5cb902afab582511a9500b228e8ce606096"
SRC_URI[offlinesearchjs.sha256sum] = "61e7d47a32bc58ccaa511dd69a14e0390b03e68ae07621133d5d59aacf6e9db7"

S = "${UNPACKDIR}/kapidox-${PV}"

# BUILD TIME
DEPENDS:append = " \
    doxygen \
    graphviz \
    libjs-jquery \
    \
    python3 \
    python3-setuptools \
    python3-pip python3-wheel \
    python3-virtualenv \
    \
    python3-certifi \
    python3-charset-normalizer \
    python3-idna \
    python3-jinja2 \
    python3-markupsafe \
    python3-requests \
    python3-urllib3 \
    python3-doxypypy \
    python3-doxyqml \
    python3-graphviz \
"

BBCLASSEXTEND = "native nativesdk"
