# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2019-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "Qt Cryptographic Architecture (QCA)"
HOMEPAGE = "https://invent.kde.org/libraries/qca/"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

inherit kde6-ecm kde6-packaging

SRC_URI = "${KDE6_MIRROR}/stable/qca/${PV}/qca-${PV}.tar.xz"
SRC_URI[sha256sum] = "1c5b722da93d559365719226bb121c726ec3c0dc4c67dea34f1e50e4e0d14a02"

S = "${WORKDIR}/qca-${PV}"

DEPENDS:append = " \
    libgcrypt \
    nss \
"

DEPENDS:append:class-target = " \
    cyrus-sasl \
    botan \
"

PACKAGECONFIG[qt6] = "-DBUILD_WITH_QT6=ON, -DBUILD_WITH_QT6=OFF, qt5compat6" 
PACKAGECONFIG[tests] = "-DBUILD_TESTS=ON, -DBUILD_TESTS=OFF, "
PACKAGECONFIG[tools] = "-DBUILD_TOOLS=ON, -DBUILD_TOOLS=OFF, "
PACKAGECONFIG[plugins] = "-DBUILD_PLUGINS=ON, -DBUILD_PLUGINS=OFF, " 
PACKAGECONFIG[devmode] = "-DDEVELOPER_MODE=ON, -DDEVELOPER_MODE=OFF, " 
PACKAGECONFIG:append = " qt6 tools plugins"

EXTRA_OECMAKE += " \
    -DQCA_FEATURE_INSTALL_DIR=${KDE6_INSTALL_MKSPECSDIR}/features \
    -DLIB_SUFFIX=${@d.getVar('baselib').replace('lib', '')} \
    -DQCA_PLUGINS_INSTALL_DIR=${KDE6_INSTALL_QTPLUGINDIR} \
"

# This sucks needs a patch and or talking to upstream to see what they have to say.
do_install:append() {
    dirs_to_fix="
        ${D}${libdir}/cmake/Qca-qt6
        ${D}${prefix_native}${libdir_native}/cmake/Qca-qt6
    "

    for d in $dirs_to_fix; do
        [ -d "$d" ] || continue
        for f in "$d"/Qca-qt6Targets.cmake "$d"/Qca-qt6Targets-*.cmake; do
            [ -f "$f" ] || continue
            if grep -qE '^set\(_IMPORT_PREFIX "/usr"\)$' "$f"; then
                sed -i \
                    -e 's|^set(_IMPORT_PREFIX "/usr")$|set(_IMPORT_PREFIX "${CMAKE_CURRENT_LIST_DIR}")|' \
                    -e '/^set(_IMPORT_PREFIX "\${CMAKE_CURRENT_LIST_DIR}")$/a get_filename_component(_IMPORT_PREFIX "${_IMPORT_PREFIX}" PATH)\nget_filename_component(_IMPORT_PREFIX "${_IMPORT_PREFIX}" PATH)\nget_filename_component(_IMPORT_PREFIX "${_IMPORT_PREFIX}" PATH)' \
                    "$f"
            fi

            sed -i -E 's|"/usr/|"\${_IMPORT_PREFIX}/|g' "$f"
            sed -i -E 's|(libqca-qt6\.so)(\.[0-9.]+)|\1|g' "$f"
        done
    done
}

FILES:${PN}-dev += " ${libdir}/cmake/Qca-qt6/* "
