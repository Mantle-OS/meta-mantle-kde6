SUMMARY = "Advanced text editing widgets for KDE and Qt applications"
DESCRIPTION = "KTextWidgets provides a collection of advanced text editing \
components that extend Qt Widgets functionality. It includes rich text \
editing, spell checking, text completion, formatting controls, and \
integration with text-to-speech services. The framework provides \
reusable widgets and utilities for building text editors, messaging \
interfaces, and document editing applications with consistent \
text processing capabilities."

HOMEPAGE = "https://api.kde.org/frameworks/ktextwidgets/html/index.html"
SECTION = "libs"

LICENSE = "LGPL-2.1-or-later AND (LGPL-2.0-only OR LGPL-3.0-only OR LicenseRef-KDE-Accepted-LGPL)"

inherit kf6

DEPENDS:append = " \
    kcompletion6 \
    kconfigwidgets6 \
    kservice6 \
    kwidgetsaddons6 \
    sonnet6 \
"

PACKAGECONFIG[text-to-speech] = "-DWITH_TEXT_TO_SPEECH=ON, -DWITH_TEXT_TO_SPEECH=OFF, qtspeech6"
PACKAGECONFIG:append = " \
    kcoreaddons \
    kauth \
    kconfig \
    ki18n \
    text-to-speech \
"
