SUMMARY = "Heap memory profiler for Linux applications"
DESCRIPTION = "Heaptrack is a heap memory profiling tool that records \
memory allocations and deallocations together with their associated \
stack traces. It helps developers identify memory leaks, excessive \
allocation activity, temporary allocations and peak heap memory usage \
in native applications."

HOMEPAGE = "https://invent.kde.org/sdk/heaptrack"
BUGTRACKER = "https://bugs.kde.org/"
SECTION = "devel"

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-only.txt;md5=41890f71f740302b785c27661123bff5"

inherit kde6-cmake

SRC_URI = " \
    git://invent.kde.org/sdk/heaptrack;branch=master;protocol=https \
"

SRCREV = "b54e92e88b0895f910a5b107f499d1155a086b80"

DEPENDS:append = " \
    zlib \
    zstd \
    boost \
    elfutils elfutils-native \
"

PACKAGECONFIG[track] = "-DHEAPTRACK_BUILD_TRACK=ON, -DHEAPTRACK_BUILD_TRACK=OFF"
PACKAGECONFIG[interpret] = "-DHEAPTRACK_BUILD_INTERPRET=ON, -DHEAPTRACK_BUILD_INTERPRET=OFF"
PACKAGECONFIG[unwind] = "-DHEAPTRACK_USE_LIBUNWIND=ON, -DHEAPTRACK_USE_LIBUNWIND=OFF, libunwind"
PACKAGECONFIG:append  = " \
    unwind \
"
# not allowed when cross compileing or not suggested really
# PACKAGECONFIG[print] = "-DHEAPTRACK_BUILD_PRINT=ON, -DHEAPTRACK_BUILD_PRINT=OFF"
# PACKAGECONFIG[gui] = "-DHEAPTRACK_BUILD_GUI=ON, -DHEAPTRACK_BUILD_GUI=OFF"
# PACKAGECONFIG[system-robinmap] = "-DHEAPTRACK_USE_SYSTEM_ROBINMAP=ON, -DHEAPTRACK_USE_SYSTEM_ROBINMAP=OFF, robinmap"

EXTRA_OECMAKE:append = " \
    -DHEAPTRACK_BUILD_GUI=OFF \
    -DHEAPTRACK_BUILD_PRINT=OFF \
    -DHEAPTRACK_USE_SYSTEM_ROBINMAP=OFF \
    -DHEAPTRACK_USE_QT6=ON \
    -DCMAKE_POLICY_VERSION_MINIMUM=3.5 \
"
