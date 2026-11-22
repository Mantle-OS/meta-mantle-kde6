"""KDE Plasma release-family configuration.

Common discovery, caching, checksum comparison, and generation belong to KDE.
Plasma source archives use full release-version directories, handled by
utils.get_source_url().
"""

from . import utils
from .kde import KDE


class Plasma(KDE):
    """Plasma naming rules and release-family identity."""

    base_name = "plasma"
    base_url = utils.PLASMA_URL

    override_map = {
        "kde6-cli-tools-kde6": "kde-cli-tools",
        "polkit-kde6-agent-1-kde6": "polkit-kde-agent-1",
    }
