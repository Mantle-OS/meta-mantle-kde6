# SPDX-FileCopyrightText: 2022 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "KPipeWire"
HOMEPAGE = "https://invent.kde.org/plasma/kpipewire"
LICENSE = "LGPL-2.1-only | LGPL-3.0-only | LicenseRef-KDE-Accepted-LGPL"

inherit plasma6
inherit features_check

REQUIRED_DISTRO_FEATURES = "opengl"

DEPENDS:append = " \
    kwayland6 \
    \
    qtdeclarative6 \
    qtwayland6 qtwayland6-native \
    wayland \
    \
    virtual/egl \
    pipewire \
    libav \
    ffmpeg \
    libepoxy \
    libdrm \
    libva \
    pipewire \
"

RDEPENDS:${PN} += " \
    pipewire \
"
