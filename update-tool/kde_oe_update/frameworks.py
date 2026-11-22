"""KDE Frameworks release-family configuration.

Common discovery, caching, checksum comparison, and generation belong to KDE.
Frameworks source archives use major.minor release directories, handled by
KDE._indexed_directory() and utils.get_source_url().
"""

from . import utils
from .kde import KDE


class Frameworks(KDE):
    """Frameworks naming rules and release-family identity."""

    base_name = "frameworks"
    base_url = utils.FRAMEWORKS_URL

    override_map = {
        "oxygen-icons-kf6": "oxygen-icons",
        "phonon-backend-vlc-kf6": "phonon-backend-vlc",
        "libkexiv2-kf6": "libkexiv2",
        "phonon-kf6": "phonon",
        "plasma-wayland-protocols-kf6": "plasma-wayland-protocols",
        "qca-kf6": "qca",
        "zxing-cpp-kf6": "zxing-cpp",
        "libdmtx-kf6": "libdmtx",
        "ecm-kf6": "extra-cmake-modules",
    }
