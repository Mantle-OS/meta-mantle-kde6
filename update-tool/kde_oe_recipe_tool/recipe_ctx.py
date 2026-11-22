from dataclasses import dataclass
from kde_oe_update.schema import Package, Release

@dataclass(frozen=True)
class RecipeContext:
    family: str
    version: str
    release: Release
    package: Package | None = None
