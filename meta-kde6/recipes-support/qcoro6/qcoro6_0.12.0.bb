# SPDX-FileCopyrightText: none
# SPDX-License-Identifier: CC0-1.0
# SPDX-FileCopyrightText: 2021-2022 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT
DESCRIPTION = "QCoro"
HOMEPAGE = "https://github.com/danvratil/qcoro"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSES/MIT.txt;md5=387248cdf922178ee3fdc379e28542dc"
PR = "r0"

inherit kde6-cmake kde6-packaging

SRC_URI = "git://github.com/danvratil/qcoro.git;nobranch=1;protocol=https"
SRCREV = "ef1231ae490465b4f592ca326eee6d56a3d802ae"
S = "${WORKDIR}/git"

PACKAGECONFIG[examples] = "-DQCORO_BUILD_EXAMPLES=ON, -DQCORO_BUILD_EXAMPLES=OFF, "
PACKAGECONFIG[testing] = "-DQCORO_BUILD_TESTING=ON, -DQCORO_BUILD_TESTING=OFF, "
PACKAGECONFIG[asan] = "-DQCORO_ENABLE_ASAN=ON, -DQCORO_ENABLE_ASAN=OFF, "
PACKAGECONFIG[no-deprecated-task-h] = "-DQCORO_DISABLE_DEPRECATED_TASK_H=ON, -DQCORO_DISABLE_DEPRECATED_TASK_H=OFF, "
PACKAGECONFIG[dbus] = "-DQCORO_WITH_QTDBUS=ON, -DQCORO_WITH_QTDBUS=OFF, "
PACKAGECONFIG[qt6-networking] = "-DQCORO_WITH_QTNETWORK=ON, -DQCORO_WITH_QTNETWORK=OFF, "
PACKAGECONFIG[qt6-websockets] = "-DQCORO_WITH_QTWEBSOCKETS=ON, -DQCORO_WITH_QTWEBSOCKETS=OFF, qtwebsockets6"
PACKAGECONFIG[qml] = "-DQCORO_WITH_QML=ON, -DQCORO_WITH_QML=OFF, qtdeclarative6 qtdeclarative6-native"
PACKAGECONFIG[quick] = "-DQCORO_WITH_QTQUICK=ON, -DQCORO_WITH_QTQUICK=OFF, qtdeclarative6 qtdeclarative6-native"
PACKAGECONFIG[qtests] = "-DQCORO_WITH_QTTEST=ON, -DQCORO_WITH_QTTEST=OFF, qttools6-native"

PACKAGECONFIG:append = " \
    dbus \
    qt6-networking \
    qt6-websockets \
    qml quick \
"

EXTRA_OECMAKE += " \
    -DCMAKE_POSITION_INDEPENDENT_CODE=ON \
"
