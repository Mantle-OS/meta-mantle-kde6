#!/usr/bin/env python3

"""KDE OpenEmbedded recipe development tool.

Uses the existing KDE release cache to select upstream packages,
download and extract source archives, and eventually generate
OpenEmbedded recipe candidates.
"""

import argparse
import os
import sys
from pathlib import Path


# Resolve the project root independently of the invoking directory.
ROOT_DIR = Path(__file__).resolve().parent
os.chdir(ROOT_DIR)

if str(ROOT_DIR) not in sys.path:
    sys.path.insert(0, str(ROOT_DIR))


from kde_oe_recipe_tool import initialize_context
from kde_oe_recipe_tool.fetcher import MAX_DOWNLOADS, FetchError, fetch, fetch_all
from kde_oe_recipe_tool import MODULES


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(
        description="KDE OpenEmbedded recipe development tool.",
        formatter_class=argparse.ArgumentDefaultsHelpFormatter,
    )

    parser.add_argument(
        "--type",
        choices=(*MODULES.keys(), "all"),
        help="KDE module, or all modules with --download-all.",
    )

    parser.add_argument(
        "--kde-version",
        metavar="VERSION",
        help="Upstream KDE release version. Defaults to the selected version in versions.json.",
    )

    parser.add_argument(
        "--name",
        metavar="PACKAGE",
        help="Upstream KDE package name.",
    )

    actions = parser.add_mutually_exclusive_group()

    actions.add_argument(
        "--generate",
        action="store_true",
        help="Interactively generate an OpenEmbedded recipe candidate.",
    )

    actions.add_argument(
        "--download",
        action="store_true",
        help="Download and extract a verified upstream source archive.",
    )

    actions.add_argument(
        "--download-all",
        action="store_true",
        help="Download and extract every package in the selected release.",
    )

    parser.add_argument(
        "--max-downloads",
        type=int,
        metavar="N",
        default=None,
        help=f"Maximum concurrent source downloads (default: {MAX_DOWNLOADS}).",
    )

    actions.add_argument(
        "--cleanup",
        action="store_true",
        help="Interactively clean downloaded and extracted source files.",
    )

    actions.add_argument(
        "--cleanup-all",
        action="store_true",
        help="Remove all research cache artifacts while preserving release metadata.",
    )

    args = parser.parse_args()

    if args.max_downloads is not None:
        if args.max_downloads < 1:
            parser.error("--max-downloads must be at least 1")

        if not args.download_all:
            parser.error("--max-downloads requires --download-all")

    if args.type == "all" and not args.download_all:
        parser.error("--type all requires --download-all")

    if args.download_all and args.name is not None:
        parser.error("--name cannot be combined with --download-all")

    if args.download_all and args.type == "all" and args.kde_version is not None:
        parser.error("--kde-version cannot be combined with --type all")

    if args.cleanup_all and any((
        args.type is not None,
        args.kde_version is not None,
        args.name is not None,
    )):
        parser.error("--cleanup-all does not accept module, version, or package selection")

    return args


def main() -> int:
    args = parse_args()

    if not any((
        args.generate,
        args.download,
        args.download_all,
        args.cleanup,
        args.cleanup_all,
    )):
        print("No operation selected. Use --help to see available options.")
        return 0

    try:
        context = initialize_context(args)

        if args.download:
            result = fetch(context)

            print("\nSource acquisition complete")
            print(f"  Archive:    {result.archive}")
            print(f"  Source:     {result.source}")
            print(f"  Downloaded: {result.downloaded}")
            print(f"  Extracted:  {result.extracted}")

            return 0

        if args.download_all:
            result = fetch_all(
                context,
                max_downloads=args.max_downloads,
            )

            return 0 if result.successful else 1

        # TODO: Dispatch to kde_oe_recipe_tool:
        #       --cleanup      OWNER -> cleanup.py
        #       --cleanup-all  OWNER -> cleanup.py
        #       --generate     OWNER -> TBD
        #
        # No cleanup, source analysis, or recipe generation
        # is performed by this entry point yet.

        print("Operation not implemented yet.", file=sys.stderr)
        return 1

    except (FetchError, ValueError, FileNotFoundError) as exc:
        print(f"ERROR: {exc}", file=sys.stderr)
        return 1

    except KeyboardInterrupt:
        print("\nInterrupted.", file=sys.stderr)
        return 130


if __name__ == "__main__":
    sys.exit(main())
