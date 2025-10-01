# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2018 Alistair Francis <alistair.francis@wdc.com>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
# SPDX-FileCopyrightText: 2025 Joseph Mills <josephjamesmills@gmail.com>
# SPDX-License-Identifier: MIT
SUMMARY = "Extensible daemon for providing system level services"
HOMEPAGE = "https://api.kde.org/frameworks/kded/html/index.html"
LICENSE = "LGPL-2.0-only"

inherit kf6

DEPENDS:append = " \
    kcrash6 \
    kdbusaddons6 \
    kservice6 \
"

PACKAGECONFIG:append = " \
    kauth \
    kcoreaddons \
    kconfig \
    kdoctools \
"

do_configure:prepend() {
    sed -i \
        "s#KCONF_UPDATE_EXE=\".*\"#KCONF_UPDATE_EXE=\"${libexecdir}/kf6/kconf_update\"#g" \
        ${S}/src/CMakeLists.txt
}

BBCLASSEXTEND = "native nativesdk"
