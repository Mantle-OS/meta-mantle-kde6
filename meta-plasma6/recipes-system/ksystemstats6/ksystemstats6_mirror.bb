# SPDX-FileCopyrightText: 2025 Marc Ferland <marc.ferland@sonatest.com>
#
# SPDX-License-Identifier: MIT
SUMMARY = "KSystemStats"
DESCRIPTION = "KSystemStats is a daemon that collects statistics about the running system."
HOMEPAGE = "https://invent.kde.org/plasma/ksystemstats"
LICENSE = "BSD-2-Clause OR BSD-3-Clause OR GPL-2.0-only OR GPL-3.0-only OR LicenseRef-KDE-Accepted-GPL"

inherit plasma6

DEPENDS:append = " \
    solid6 \
    kio6 \
    kcrash6 \
    \
    libksysguard6 \
    networkmanager-qt6 \
    \
    lmsensors \
    libcap \
    libnl \
"
