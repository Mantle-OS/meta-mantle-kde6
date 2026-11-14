# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2014 Johan Thelin <johan.thelin@pelagicore.com>
# SPDX-FileCopyrightText: 2016 Sébastien Taylor <sebastien@au-zone.com>
# SPDX-FileCopyrightText: 2017-2020 Volker Krause <vkrause@kde.org>
# SPDX-FileCopyrightText: 2020 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
SUMMARY = "Image format plugins for Qt"
HOMEPAGE = "https://api.kde.org/frameworks/kimageformats/html/index.html"
SECTION = "libs"
LICENSE = "BSD-2-Clause AND LGPL-2.0-or-later AND LGPL-2.1-or-later AND (LGPL-2.1-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"

inherit kf6

DEPENDS:append = " karchive6 qtimageformats6"
PACKAGECONFIG[openexr] =  ", , openexr"
PACKAGECONFIG[libavif] = ", , libavif"
PACKAGECONFIG[dds] = "-DKIMAGEFORMATS_DDS=ON, -DKIMAGEFORMATS_DDS=OFF, "
PACKAGECONFIG[heif] = "-DKIMAGEFORMATS_HEIF=ON, -DKIMAGEFORMATS_HEIF=OFF, "
PACKAGECONFIG[jkl] = "-DKIMAGEFORMATS_JXL=ON, -DKIMAGEFORMATS_JXL=OFF, libjxl" 
PACKAGECONFIG[jp2] = "-DKIMAGEFORMATS_JP2=ON, -DKIMAGEFORMATS_JP2=OFF, libjpeg-turbo"
PACKAGECONFIG[libraw] = ", , libraw"
#PACKAGECONFIG[jxr] = "-DKIMAGEFORMATS_JXR=ON, -DKIMAGEFORMATS_JXR=OFF, libjxr"

PACKAGECONFIG:append = " \
    openexr \
    libavif \
    jkl \
    jp2 \
    libraw \
"
