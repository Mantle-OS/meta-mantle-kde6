#!/usr/bin/env python3
"""Command-line entry point for the KDE OpenEmbedded release updater."""

import argparse
import sys
from pathlib import Path

# Resolve paths relative to this script, regardless of the working directory.
CWD_DIR = Path(__file__).resolve().parent
sys.path.insert(0, str(CWD_DIR))

from kde_oe_update import DEFAULT_REFRESH_HOURS, run
from kde_oe_update import utils
from kde_oe_update.cache import CacheError
from kde_oe_update import _frameworks_release_versions

utils.SCRIPT_DIR = CWD_DIR
utils.VERSION_FILE = CWD_DIR / "versions.json"
utils.INPUT_DIR = CWD_DIR / "in"
utils.CACHE_DIR = CWD_DIR / "cache"
utils.OUTPUT_DIR = CWD_DIR / "out"


def select_versions(index, updaters, cache):
    """Choose full versions or request every indexed release without modifying versions.json."""
    print("\nKDE release selection: [1] Latest  [2] Advanced  [3] All")
    mode = input("Selection (latest/advanced/all): ").strip().lower()
    if mode in ("3", "all"):
        return "all"
    if mode in ("1", "latest", "l"):
        advanced = False
    elif mode in ("2", "advanced", "a"):
        advanced = True
    else:
        raise ValueError(f"Unknown release selection: {mode!r}")

    selections = {}
    for updater in updaters:
        family = updater.base_name
        available = getattr(index, family)
        if not available:
            raise CacheError(f"No indexed versions for {family}; run --update-versions first")
        if advanced:
            print(f"\n{family} versions: {', '.join(available)}")
            directory = input(f"Choose {family} directory: ").strip()
            if directory not in available:
                raise ValueError(f"Unknown indexed {family} version: {directory!r}")
        else:
            directory = max(available, key=lambda value: tuple(map(int, value.split("."))))

        # Frameworks has major.minor directories but full patch archive versions.
        if family == "frameworks":
            patches = _frameworks_release_versions(directory)
            if advanced:
                print(f"Available Frameworks releases: {', '.join(patches)}")
                version = input("Choose full Frameworks release: ").strip()
                if version not in patches:
                    raise ValueError(f"Unknown Frameworks release: {version!r}")
            else:
                version = patches[-1]
        else:
            version = directory
        selections[family] = version
        print(f"{family}: {version}")

    return selections


def main() -> int:
    parser = argparse.ArgumentParser(description="KDE OpenEmbedded release updater")
    parser.add_argument("--target", default="all",
                        choices=["all", "framework", "plasma", "release-services"],
                        help="KDE component group (default: all)")
    operations = parser.add_argument_group("operations")
    operations.add_argument("--update-versions", action="store_true",
                            help="Refresh discovered upstream version index")
    operations.add_argument("--update-cache", action="store_true",
                            help="Select versions and fill complete cached releases")
    operations.add_argument("--generate", action="store_true",
                            help="Generate checksum-only classes entirely offline")
    parser.add_argument("--force", action="store_true",
                        help="Force --update-versions to bypass its refresh interval")
    parser.add_argument("--refresh-hours", type=int, default=DEFAULT_REFRESH_HOURS,
                        help=f"Minimum discovery refresh interval (default: {DEFAULT_REFRESH_HOURS} hours)")
    args = parser.parse_args()
    if not (args.update_versions or args.update_cache or args.generate):
        parser.error("choose at least one of --update-versions, --update-cache, --generate")
    if args.force and not args.update_versions:
        parser.error("--force requires --update-versions")
    try:
        run(args.target, update_versions=args.update_versions,
            update_cache=args.update_cache, generate=args.generate,
            force=args.force, refresh_hours=args.refresh_hours,
            select_versions=select_versions if args.update_cache else None)
    except (OSError, ValueError, KeyboardInterrupt) as exc:
        print(f"KDE updater failed: {exc}", file=sys.stderr)
        return 1
    return 0


if __name__ == "__main__":
    sys.exit(main())
