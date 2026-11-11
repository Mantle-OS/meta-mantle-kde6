inherit kde6-paths

PACKAGE_BEFORE_PN = " \
    ${PN}-qmlplugins \
    ${PN}-bin \
    ${PN}-plugins \
    ${PN}-example \
    ${PN}-licenses \
"

ALLOW_EMPTY:${PN} = "1"
ALLOW_EMPTY:${PN}-qmlplugins = "1"
ALLOW_EMPTY:${PN}-bin =        "1"
ALLOW_EMPTY:${PN}-plugins =    "1"
ALLOW_EMPTY:${PN}-example =    "1"
ALLOW_EMPTY:${PN}-licenses =   "1"

RRECOMMENDS:${PN} = " \
    ${PN}-plugins \
    ${PN}-qmlplugins \
    ${PN}-bin \
    ${PN}-licenses \
"
RRECOMMENDS:${PN}:class-native = ""
RRECOMMENDS:${PN}-dev += " \
    ${PN}-staticdev \
"
FILES:${PN}-qmlplugins = " \
    ${KDE6_INSTALL_QMLDIR} \
"

FILES:${PN}-bin = " \
    ${KDE6_INSTALL_BINDIR} \
    ${KDE6_INSTALL_LIBEXECDIR} \
"

FILES:${PN}-plugins = " \
    ${KDE6_INSTALL_QTPLUGINDIR}/*/*${SOLIBSDEV} \
    ${KDE6_INSTALL_QTPLUGINDIR}/*/*/*${SOLIBSDEV} \
    ${KDE6_INSTALL_QTPLUGINDIR}/*/*/*/*${SOLIBSDEV} \
"
FILES:${PN}-plugins:mingw32 = " \
    ${KDE6_INSTALL_QTPLUGINDIR}/*/*.dll \
    ${KDE6_INSTALL_QTPLUGINDIR}/*/*/*.dll \
    ${KDE6_INSTALL_QTPLUGINDIR}/*/*/*/*.dll \
"

FILES:${PN} += " \
    ${KDE6_INSTALL_LIBDIR}/lib*${SOLIBS} \
    ${datadir}/kf6 \
    ${KDE6_INSTALL_ICONDIR} \
    ${KDE6_INSTALL_SYSTEMDUNITDIR} \
    ${KDE6_INSTALL_SYSTEMDUSERUNITDIR} \
    ${KDE6_INSTALL_DBUSDIR} \
    ${KDE6_INSTALL_DBUSINTERFACEDIR} \
    ${KDE6_INSTALL_DBUSSERVICEDIR} \
    ${KDE6_INSTALL_MIMEDIR} \
"

FILES:${PN}-licenses += " \
    ${KDE6_INSTALL_LICENSEDIR} \
"

FILES:${PN}-dev += " \
    ${KDE6_INSTALL_LOGGINGCATEGORIESDIR} \
    ${KDE6_INSTALL_MKSPECSDIR} \
    ${KDE6_INSTALL_INCLUDEDIR}/*/* \
    ${KDE6_INSTALL_LIBDIR}/lib*${SOLIBSDEV} \
    ${KDE6_INSTALL_LIBDIR}/*.prl \
    ${KDE6_INSTALL_LIBDIR}/*.la \
    ${KDE6_INSTALL_LIBDIR}/cmake \
    ${KDE6_INSTALL_LIBDIR}/metatypes \
    ${KDE6_INSTALL_LIBDIR}/pkgconfig \
    ${KDE6_INSTALL_QMLDIR}/*.qmltypes \
    ${KDE6_INSTALL_QMLDIR}/*/*.qmltypes \
    ${KDE6_INSTALL_QMLDIR}/*/*/*.qmltypes \
    ${KDE6_INSTALL_QMLDIR}/*/*/*/*.qmltypes \
    ${KDE6_INSTALL_QMLDIR}/*/*/*/*/*.qmltypes \
    ${KDE6_INSTALL_QMLDIR}/*/designer \
    ${KDE6_INSTALL_QMLDIR}/*/*/designer \
    ${KDE6_INSTALL_QMLDIR}/*/*/*/designer \
"

FILES:${PN}-staticdev += " \
    ${KDE6_INSTALL_LIBDIR}/*.a \
    ${KDE6_INSTALL_QTPLUGINDIR}/*/*.a \
    ${KDE6_INSTALL_QTPLUGINDIR}/*/*.prl \
    ${KDE6_INSTALL_QTPLUGINDIR}/*/*/*.a \
    ${KDE6_INSTALL_QTPLUGINDIR}/*/*/*.prl \
    ${KDE6_INSTALL_QMLDIR}/*/*.a \
    ${KDE6_INSTALL_QMLDIR}/*/*.prl \
    ${KDE6_INSTALL_QMLDIR}/*/*/*.a \
    ${KDE6_INSTALL_QMLDIR}/*/*/*.prl \
    ${KDE6_INSTALL_QMLDIR}/*/*/*/*.a \
    ${KDE6_INSTALL_QMLDIR}/*/*/*/*.prl \
    ${KDE6_INSTALL_QMLDIR}/*/*/*/*/*.a \
    ${KDE6_INSTALL_QMLDIR}/*/*/*/*/*.prl \
    ${KDE6_INSTALL_LIBDIR}/objects* \
    ${KDE6_INSTALL_QTPLUGINDIR}/*/objects* \
    ${KDE6_INSTALL_QMLDIR}/*/objects*/ \
    ${KDE6_INSTALL_QMLDIR}/*/*/objects*/ \
    ${KDE6_INSTALL_QMLDIR}/*/*/*/objects*/ \
    ${KDE6_INSTALL_QMLDIR}/*/*/*/*/objects*/ \
"

FILES:${PN}-examples = " \
    ${KDE6_INSTALL_EXAMPLESDIR} \
"

RDEPENDS:${PN}-examples += " ${PN}"
