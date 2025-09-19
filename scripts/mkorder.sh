
LIST1="attica6 bluez-qt6 breeze-icons6 ecm-kf6 karchive6 kcalendarcore6 kcodecs6 kconfig6 kcoreaddons6 \
kdbusaddons6 kdnssd6 kglobalaccel6 kguiaddons6 kholidays6 ki18n6 kidletime6 kirigami6 kitemmodels6 \
kitemviews6 kplotting6 kquickcharts6 ktexttemplate6 kuserfeedback6 kwidgetsaddons6 kwindowsystem6 \
modemmanager-qt6 networkmanager-qt6 prison6 solid6 sonnet6 syntax-highlighting6 threadweaver6"

LIST2="kauth6 kcolorscheme6 kcompletion6 kcontacts6 kcrash6 kdoctools6 kfilemetadata6 \
kimageformats6 kjobwidgets6 knotifications6 kpackage6 kpty6 kstatusnotifieritem6 kunitconversion6 syndication6"

LIST3="baloo6 kbookmarks6 kcmutils6 kconfigwidgets6 kdav6 kdeclarative6 kded6 kdesu6 kiconthemes6 kio6 \
knewstuff6 knotifyconfig6 kparts6 kpeople6 krunner6 kservice6 ksvg6 ktexteditor6 ktextwidgets6 kwallet6 \
kxmlgui6 purpose6 qqc2-desktop-style6"

LIST4="frameworkintegration6"

for i in ${LIST3};
do
  mkdir $i;
  mv ${i}_mirror.bb ${i}/
done
