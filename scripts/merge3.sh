#!/bin/bash
# kcolorscheme
#T1 was done by hand

# T2="kcompletion kcontacts kcrash kdoctools kfilemetadata kimageformats kjobwidgets knotifications kpackage \
# kpty kstatusnotifieritem kunitconversion syndication"


T3="baloo kbookmarks kcmutils kconfigwidgets kdav kdeclarative kded kdesu kiconthemes kio knewstuff knotifyconfig kparts kpeople krunner kservice ksvg ktexteditor ktextwidgets kwallet kxmlgui purpose qqc2-desktop-style "


for i in $T3;
do
    cat "${i}_metainfo.inc" >> ${i}6_6.17.0.bb
    rm "${i}_metainfo.inc"

    cat "${i}.inc" >> ${i}6_6.17.0.bb
    rm "${i}.inc"
done


