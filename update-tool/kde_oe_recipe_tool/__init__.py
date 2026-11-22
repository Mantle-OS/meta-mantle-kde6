"""Interactive startup and package selection for the KDE OE recipe tool.

Release discovery, checksums, and package comparison are owned by
kde_oe_update. This module only initializes shared paths, selects
packages, and validates existing cached data.
"""

import argparse
from pathlib import Path

from kde_oe_update import utils
from kde_oe_update.cache import Cache
from kde_oe_update.frameworks import Frameworks
from kde_oe_update.plasma import Plasma
from kde_oe_update.release_services import ReleaseServices
from kde_oe_update.schema import Package, Release

from .recipe_ctx import RecipeContext


MODULES = {
    "frameworks": Frameworks,
    "plasma": Plasma,
    "release-services": ReleaseServices,
}

MODULE_NAMES = {
    "frameworks": "Frameworks",
    "plasma": "Plasma",
    "release-services": "Release Services",
}

VERSION_KEYS = {
    "frameworks": "frameworks",
    "plasma": "plasma",
    "release-services": "release_services",
}


def initialize_paths(root: Path | None = None) -> None:
    """Initialize shared KDE paths without creating files or directories.

    Existing paths initialized by kde_oe_update are preserved unless an
    explicit project root is provided.
    """
    if root is None:
        root = Path(__file__).resolve().parent.parent

        paths = {
            "CACHE_DIR": root / "cache",
            "INPUT_DIR": root / "in",
            "OUTPUT_DIR": root / "out",
            "VERSION_FILE": root / "versions.json",
        }

        for key, path in paths.items():
            if getattr(utils, key) is None:
                setattr(utils, key, path)

    else:
        root = root.resolve()

        utils.CACHE_DIR = root / "cache"
        utils.INPUT_DIR = root / "in"
        utils.OUTPUT_DIR = root / "out"
        utils.VERSION_FILE = root / "versions.json"

    if not utils.VERSION_FILE.is_file():
        raise FileNotFoundError(
            f"KDE versions configuration missing: {utils.VERSION_FILE}"
        )

    if not utils.CACHE_DIR.is_dir():
        raise FileNotFoundError(
            f"KDE release cache directory missing: {utils.CACHE_DIR}"
        )


def select_module(
    module: str | None = None,
    allow_all: bool = False,
) -> str:
    """Resolve a KDE module from a CLI argument or interactive selection."""
    available = list(MODULES)

    if allow_all:
        available.append("all")

    if module is not None:
        if module not in available:
            raise ValueError(f"Unsupported KDE module: {module}")
        return module

    print("\nSelect KDE module:\n")

    for index, name in enumerate(available, 1):
        label = "All KDE modules" if name == "all" else MODULE_NAMES[name]
        print(f"  [{index}] {label}")

    while True:
        choice = input("\nSelection: ").strip()

        if choice in available:
            return choice

        if choice.isdigit():
            index = int(choice)
            if 1 <= index <= len(available):
                return available[index - 1]

        print("Invalid module selection.")


def select_version(family: str, version: str | None = None) -> str:
    """Confirm one KDE release version using versions.json as the preset."""
    if family not in MODULES:
        raise ValueError(f"Unsupported KDE module: {family}")

    versions = utils.local_versions()
    configured = versions[VERSION_KEYS[family]]

    preset = configured if version is None else version.strip()

    if not preset:
        raise ValueError("KDE version cannot be empty")

    print(f"\n{MODULE_NAMES[family]} version")
    print(f"Configured version: {configured}")

    while True:
        selected = input(f"Target version [{preset}]: ").strip()
        selected = selected or preset

        try:
            utils.get_source_url(VERSION_KEYS[family], selected)
            return selected
        except ValueError as exc:
            print(f"Invalid version: {exc}")


def load_release(cache: Cache, family: str, version: str) -> Release:
    """Load and validate a cached release without contacting KDE mirrors."""
    if family not in MODULES:
        raise ValueError(f"Unsupported KDE module: {family}")

    cache_family = VERSION_KEYS[family]

    utils.get_source_url(cache_family, version)

    release = cache.load_release(cache_family, version)

    if release.family != cache_family or release.version != version:
        raise ValueError(
            f"Cached release identity mismatch: "
            f"expected {cache_family}/{version}, "
            f"got {release.family}/{release.version}"
        )

    if not release.packages:
        raise ValueError(
            f"Cached release contains no packages: {cache_family}/{version}"
        )

    names = [package.name for package in release.packages]

    if len(names) != len(set(names)):
        raise ValueError(
            f"Duplicate upstream package names in cached release: "
            f"{cache_family}/{version}"
        )

    return release


def select_package(
    packages: list[Package],
    name: str | None = None,
) -> Package:
    """Select one upstream package from the provided package list."""
    by_name = {package.name: package for package in packages}

    if len(by_name) != len(packages):
        raise ValueError("Duplicate upstream package names in package list")

    if name is not None:
        name = name.strip()

        if not name:
            raise ValueError("Package name cannot be empty")

        package = by_name.get(name)

        if package is None:
            raise ValueError(
                f"Package {name!r} is not in the available package list"
            )

        return package

    if not packages:
        raise ValueError("No upstream packages available for selection")

    ordered = sorted(packages, key=lambda package: package.name)

    print(f"\nAvailable upstream packages ({len(ordered)}):\n")

    for index, package in enumerate(ordered, 1):
        print(f"  [{index:3}] {package.name}")

    while True:
        choice = input("\nPackage selection: ").strip()

        if choice in by_name:
            return by_name[choice]

        if choice.isdigit():
            index = int(choice)
            if 1 <= index <= len(ordered):
                return ordered[index - 1]

        print("Invalid package selection.")


def select_packages(
    packages: list[Package],
    names: list[str] | None = None,
    all_packages: bool = False,
) -> list[Package]:
    """Resolve and validate a package queue from one cached release.

    With all_packages=True, return every package in deterministic order.
    Otherwise, resolve the explicitly requested package names.
    """
    by_name = {package.name: package for package in packages}

    if len(by_name) != len(packages):
        raise ValueError("Duplicate upstream package names in package list")

    if all_packages:
        if names:
            raise ValueError("Cannot combine all_packages with package names")

        return sorted(packages, key=lambda package: package.name)

    if not names:
        return [select_package(packages)]

    selected = []
    seen = set()

    for name in names:
        name = name.strip()

        if name not in by_name:
            raise ValueError(f"Unknown upstream package: {name!r}")

        if name in seen:
            raise ValueError(f"Duplicate package selection: {name}")

        selected.append(by_name[name])
        seen.add(name)

    return selected


def _validate_selected_package(
    release: Release,
    package: Package,
) -> Package:
    """Ensure a selected package belongs to the cached release."""
    upstream = {entry.name: entry for entry in release.packages}
    cached_package = upstream.get(package.name)

    if cached_package is None:
        raise ValueError(
            f"Selected package {package.name!r} does not exist in "
            f"the cached {release.family}/{release.version} release"
        )

    if cached_package != package:
        raise ValueError(
            f"Package metadata mismatch for {package.name!r} "
            f"in {release.family}/{release.version}"
        )

    return cached_package


def _release_contexts(
    cache: Cache,
    family: str,
    version: str,
) -> list[RecipeContext]:
    """Build the complete acquisition queue for one cached KDE release."""
    release = load_release(cache, family, version)
    packages = select_packages(release.packages, all_packages=True)

    print(f"\nModule:              {MODULE_NAMES[family]}")
    print(f"Version:             {release.version}")
    print(f"Packages selected:   {len(packages)}")

    return [
        RecipeContext(
            family=family,
            version=version,
            release=release,
            package=_validate_selected_package(release, package),
        )
        for package in packages
    ]


def initialize_context(
    args: argparse.Namespace,
    cache: Cache | None = None,
) -> RecipeContext | list[RecipeContext] | None:
    """Resolve source acquisition selections from the existing KDE cache.

    Single-package operations return one RecipeContext.
    Download-all returns a list of RecipeContext objects.
    Cleanup-all returns None.

    No network requests, downloads, extraction, cleanup, or recipe
    generation are performed here.
    """
    initialize_paths()

    # Cleanup-all operates across the complete research cache.
    if args.cleanup_all:
        return None

    if cache is None:
        cache = Cache(utils.CACHE_DIR)

    download_all = getattr(args, "download_all", False)

    family = select_module(args.type, allow_all=download_all)

    # Download-all can select every module, but each module has its
    # own independent KDE release version.
    if download_all:
        if args.name is not None:
            raise ValueError("--name cannot be combined with --download-all")

        if family == "all":
            if args.kde_version is not None:
                raise ValueError(
                    "--kde-version cannot be combined with "
                    "--type all; each module has its own version"
                )

            contexts = []

            for selected_family in MODULES:
                version = select_version(selected_family)
                contexts.extend(
                    _release_contexts(cache, selected_family, version)
                )

            print(f"\nTotal packages selected: {len(contexts)}")
            return contexts

        version = select_version(family, args.kde_version)
        return _release_contexts(cache, family, version)

    # Normal operations select exactly one KDE module and version.
    version = select_version(family, args.kde_version)
    release = load_release(cache, family, version)

    print(f"\nModule:              {MODULE_NAMES[family]}")
    print(f"Version:             {release.version}")
    print(f"Packages in release: {len(release.packages)}")

    # Cleanup operates on the selected release directory.
    if args.cleanup:
        return RecipeContext(
            family=family,
            version=version,
            release=release,
        )

    # Single-package operations use the existing KDE comparison.
    updater = MODULES[family](cache)
    comparison = updater.comparison(version)

    package = select_package(comparison.missing, args.name)
    package = _validate_selected_package(release, package)

    print(f"\nSelected package: {package.name}")
    print(f"Archive URL:      {package.url}")
    print(f"SHA-256:          {package.sha}")

    return RecipeContext(
        family=family,
        version=version,
        release=release,
        package=package,
    )
