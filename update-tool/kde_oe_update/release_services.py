"""KDE Release Service release-family configuration.

Common discovery, caching, checksum comparison, and generation belong to KDE.
Release Service source archives live under the version's src/ directory,
handled by utils.get_source_url().
"""

from . import utils
from .kde import KDE


class ReleaseServices(KDE):
    """Release Service naming rules and release-family identity."""

    base_name = "release_services"
    base_url = utils.RELEASE_SERVICES_URL

    override_map = {
        "libkexiv2-kf6": "libkexiv2",
    }
