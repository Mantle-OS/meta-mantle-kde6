inherit qt6-cmake
inherit pkgconfig
inherit python3native
inherit kde6-packaging

DEPENDS:class-nativesdk += " \
    nativesdk-qtbase6 \
    nativesdk-qttools6 \
"
DEPENDS:append = " \
    qtbase6 qttools6-native \
"

## force to .dbg
CFLAGS:append   = " ${DEBUG_PREFIX_MAP}"
CXXFLAGS:append = " ${DEBUG_PREFIX_MAP}"
CPPFLAGS:append = " ${DEBUG_PREFIX_MAP}"

PACKAGECONFIG ??= ""
PACKAGECONFIG[testing] = "-DBUILD_TESTING=ON,-DBUILD_TESTING=OFF,"
PACKAGECONFIG[examples] = "-DBUILD_EXAMPLES=ON,-DBUILD_EXAMPLES=OFF,"
PACKAGECONFIG[docs] = "-DBUILD_QCH=ON,-DBUILD_QCH=OFF, qttools6-native python3-sphinx-native"

## FIXME add this as a config opt
EXTRA_OECMAKE:append:class-native = " \
    -DBUILD_DESIGNERPLUGIN=OFF \
"

EXTRA_OECMAKE:append:class-target = " \
    -DBUILD_DESIGNERPLUGIN=OFF \
"

# apply base line
EXTRA_OECMAKE:append = " \
    -DCMAKE_INSTALL_PREFIX=${prefix} \
    -DBUILD_SHARED_LIBS=ON \
    -DCMAKE_SKIP_RPATH=ON \
    -DCMAKE_BUILD_WITH_INSTALL_RPATH=OFF \
    -DCMAKE_INSTALL_RPATH= \
    -DCMAKE_INSTALL_RPATH_USE_LINK_PATH=FALSE \
    -DUDEV_RULES_INSTALL_DIR=${sysconfdir}/udev/rules.d \
    -DBUILD_WITH_QT6=ON \
    -DQT_MAJOR_VERSION=6 \
"
## apply kde paths missing KFILETEMPLATESDIR, KAPPTEMPLATESDIR
## emc -> kde-modules/KDEInstallDirs6.cmake  
EXTRA_OECMAKE:append = " \
    -DECM_MKSPECS_INSTALL_DIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_MKSPECSDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_BINDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_BINDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_SBINDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_SBINDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_LIBDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_LIBDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_LIBEXECDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_LIBEXECDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_CMAKEPACKAGEDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_CMAKEPACKAGEDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_QTPLUGINDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_QTPLUGINDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_QMLDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_QMLDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_INCLUDEDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_INCLUDEDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_LOCALSTATEDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_LOCALSTATEDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_SHAREDSTATEDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_SHAREDSTATEDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_DOCBUNDLEDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_DOCBUNDLEDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_KCFGDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_KCFGDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_KCONFUPDATEDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_KCONFUPDATEDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_KXMLGUIDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_KXMLGUI6DIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_KNOTIFYRCDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_KNOTIFY6RCDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_ICONDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_ICONDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_LOCALEDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_LOCALEDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_SOUNDDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_SOUNDDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_TEMPLATEDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_TEMPLATEDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_WALLPAPERDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_WALLPAPERDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_APPDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_APPDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_DESKTOPDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_DESKTOPDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_MIMEDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_MIMEDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_METAINFODIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_METAINFODIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_QTQCHDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_QTQCHDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_QCHDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_QCHDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_MANDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_MANDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_INFODIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_INFODIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_DBUSDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_DBUSDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_DBUSINTERFACEDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_DBUSINTERFACEDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_DBUSSERVICEDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_DBUSSERVICEDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_DBUSSYSTEMSERVICEDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_DBUSSYSTEMSERVICEDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_CONFDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_CONFDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_AUTOSTARTDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_SYSCONFDIRAUTOSTARTDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_LOGGINGCATEGORIESDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_LOGGINGCATEGORIESDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_SYSTEMDUNITDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_SYSTEMDUNITDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_SYSTEMDUSERUNITDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_SYSTEMDUSERUNITDIR'), d.getVar('prefix') + '/')} \
    -DKDE_INSTALL_ZSHAUTOCOMPLETEDIR:PATH=${@os.path.relpath(d.getVar('KDE6_INSTALL_ZSHAUTOCOMPLETEDIR'), d.getVar('prefix') + '/')} \
"


do_compile:prepend() {
    export XDG_DATA_DIRS=${STAGING_DATADIR}:$XDG_DATA_DIRS
}


do_install:append() {
    # imported targets may leak full paths to system targets
    if [ "0" -ne $(find ${D} \( -name "*.cmake" \) | wc -l) ]; then
        find ${D} \( -name "*.cmake" \) -exec \
            sed -i \
                -e 's|${RECIPE_SYSROOT}/usr|\$\{_IMPORT_PREFIX\}|g' \
                {} \;
    fi
    if [ "0" -ne $(find ${D} \( -name "*.pc" \) | wc -l) ]; then
        find ${D} \( -name "*.pc" \) -exec \
            sed -i \
                -e 's|-I${RECIPE_SYSROOT}/usr/|-I\$\{prefix\}/|g' \
                {} \;
    fi
}
