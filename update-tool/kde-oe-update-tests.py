#!/usr/bin/python3
import sys
import unittest
from pathlib import Path

CWD_DIR = Path(__file__).resolve().parent
sys.path.insert(0, str(CWD_DIR))

def main():
    suite = unittest.defaultTestLoader.discover(
        start_dir=str(CWD_DIR / "tests"),
        pattern="test_*.py",
        top_level_dir=str(CWD_DIR),
    )

    runner = unittest.TextTestRunner(verbosity=2)
    result = runner.run(suite)

    return 0 if result.wasSuccessful() else 1


if __name__ == "__main__":
    sys.exit(main())
