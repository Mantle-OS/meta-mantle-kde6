# KDE OpenEmbedded Release Updater

A local, cache-first updater for the three KDE release families maintained by `meta-mantle-kde6`:

- KDE Frameworks 6 (`frameworks`)
- KDE Plasma 6 (`plasma`)
- KDE Release Service (`release_services`)

The tool discovers upstream releases, stores verified release snapshots, and generates candidate OpenEmbedded checksum classes from developer-supplied checksum lists. It **does not** edit the checksum classes in the layers or create recipes.

## 1. Scope and principles

1. **Discovery, caching, and generation are separate operations.** A complete local cache can be used for generation without contacting the KDE mirror.
2. **The developer chooses the release versions.** Discovery must not silently change the versions selected by Mantle.
3. **A release snapshot is the authoritative cached upstream data.** The compressed binary contains typed package records with validated upstream SHA-256 values. A JSON manifest is a human-readable, integrity-checkable companion.
4. **The developer-supplied input class is authoritative for local checksum keys.** The tool updates matching hashes; it does not invent new local keys, silently add recipes, or silently enable previously absent packages.
5. **Input and generated classes contain only checksum assignments.** No comments, `SRCREV`, headings, or other metadata are copied into a generated checksum class.
6. **Unknown mappings and invalid data fail explicitly.** Cache and output writes must not replace valid files with partial results.
7. The initial scope is these three KDE release families. Standalone KDE projects, KDE Attic management, and hooked-release version mappings are future work.

## 2. Project layout

```text
update-tool/
├── kde-oe-update.py
├── kde-oe-update-tests.py
├── README.md
├── requirements.txt
├── versions.json
├── kde_oe_update/
│   ├── __init__.py
│   ├── schema.py
│   ├── utils.py
│   ├── cache.py
│   ├── manifest.py
│   ├── kde.py
│   ├── frameworks.py
│   ├── plasma.py
│   └── release_services.py
├── in/
│   ├── kf6-checksums.bbclass
│   ├── plasma6-checksums.bbclass
│   └── rs6-checksums.bbclass
├── cache/
│   ├── last.json
│   ├── versions.msgpack.zst
│   ├── frameworks/
│   │   └── <full-release-version>/
│   │       ├── packages.msgpack.zst
│   │       └── packages.manifest.json
│   ├── plasma/
│   │   └── <full-release-version>/
│   │       ├── packages.msgpack.zst
│   │       └── packages.manifest.json
│   └── release-service/
│       └── <full-release-version>/
│           ├── packages.msgpack.zst
│           └── packages.manifest.json
├── out/
│   ├── frameworks/<full-release-version>/kf6-checksums.bbclass
│   ├── plasma/<full-release-version>/plasma6-checksums.bbclass
│   └── release-service/<full-release-version>/rs6-checksums.bbclass
└── tests/
    ├── __init__.py
    ├── test_schema.py
    ├── test_cache.py
    ├── test_utils.py
    ├── test_manifest.py
    ├── test_kde.py
    ├── test_frameworks.py
    ├── test_plasma.py
    ├── test_release_services.py
    └── test_workflow.py
```

The `in/` files are **manually copied by the developer**, never synthesized by the updater. The release directories shown under `cache/` are release identities, not temporary scratch directories. Discovery creates version-directory placeholders, including Frameworks major.minor directory names such as `6.32/`. Complete Frameworks snapshots use a **different, full release version directory** such as `6.32.0/`. Directory existence alone does not mean a release is cached.

The `cache/versions.msgpack.zst` index is the agreed binary version-index output. The root `versions.json` remains the human-edited/currently selected version configuration. A separate `packages.report.json` is deliberately **not** part of the initial tree.

## 3. CLI workflow

`--target` defaults to `all`; supported family selectors are `framework`, `plasma`, `release-services`, and `all`. The three independent operations may be combined and must execute in this order. At least one operation must be specified:

| Operation | Requires `in/*.bbclass`? | Mirror access | Primary result |
| --- | --- | --- | --- |
| `--update-versions` | No | When discovery is needed, subject to refresh policy | Discovered version index, directories, discovery timestamp |
| `--update-cache` | No | For missing snapshots, and for resolving actual Frameworks patch releases during selection | Version selection and complete release snapshots |
| `--generate` | Yes, for every selected family | **Never** | Candidate checksum-only `.bbclass` files |

Examples (using the full script path, independent of the working directory):

```sh
python3 /home/jmills/git/opensource/exos/build/layers/meta-mantle-kde6/update-tool/kde-oe-update.py --update-versions
python3 /home/jmills/git/opensource/exos/build/layers/meta-mantle-kde6/update-tool/kde-oe-update.py --update-cache
python3 /home/jmills/git/opensource/exos/build/layers/meta-mantle-kde6/update-tool/kde-oe-update.py --generate
python3 /home/jmills/git/opensource/exos/build/layers/meta-mantle-kde6/update-tool/kde-oe-update.py --target plasma --update-cache --generate
python3 /home/jmills/git/opensource/exos/build/layers/meta-mantle-kde6/update-tool/kde-oe-update.py --update-versions --update-cache --generate
```

`--update-cache` presents a selection based on the **locally cached version index**:

- **Latest:** select the newest available version for each requested family.
- **Advanced:** choose an indexed version per requested family. For Frameworks, choose the full patch release from the actual archives within the chosen major.minor directory. This also leaves room for a future hooked-release mapping.
- **All:** populate the cache for **every** indexed release of the requested families. For Frameworks, inspect each major.minor directory and populate every advertised full patch version. Reuse complete cached snapshots; do not change the root `versions.json`. This mode may download many checksums and is an explicit bulk operation.

Latest and Advanced selections for the requested families are gathered and validated **before a single atomic update** to the root `versions.json` without dropping unrelated keys such as `plasma_wayland_protocols`. Subsequent steps use those selected versions. All mode does not change the selected versions; when combined with `--generate`, generation still uses the existing root `versions.json` selection. `--generate` reads the already selected versions and their cached releases; it does not perform discovery or checksum downloads. An absent or incomplete cache is a hard error for generation.

If `--update-cache` is invoked without a usable cached version index, it must fail with an instruction to run `--update-versions`, rather than silently introducing unexpected discovery requests.

### Failures and exit codes

The CLI exits with status 0 on success and nonzero on any failed operation or invalid selection. Errors identify the failed operation or family where available. In an `all` run, discovery and version selection are validated before committing changes. Generation preflights all selected input classes and release snapshots before writing candidates. Failures never authorize generation from incomplete or corrupt cached data. The tool does not promise cross-file rollback if a later cache fill or output write fails.

## 4. Paths and module ownership

The entry-point script resolves its own directory and initializes shared paths **once**, in `kde_oe_update.utils`, before entering the command dispatcher. Other modules use those initialized paths; they do not independently derive the project root from their own `__file__`.

| File | Responsibility |
| --- | --- |
| `kde-oe-update.py` | Argument parsing, project-path initialization, version-selection interaction |
| `kde-oe-update-tests.py` | Standard-library `unittest` discovery entry point (no separate test framework) |
| `kde_oe_update/__init__.py` | Dispatch targets and requested operations |
| `schema.py` | Typed serialized structures and schema-version definitions |
| `utils.py` | Shared paths, HTTP access, KDE directory parsing, URL rules, SHA-256 validation, version sorting |
| `cache.py` | Cache reads/writes, MessagePack, Zstandard, atomic persistence, integrity and completeness checks |
| `manifest.py` | Strict checksum-class parsing, upstream/local comparison, candidate checksum-class generation |
| `kde.py` | Shared release workflow (`check_version`, `update_version`, `local_names`, `update_cache`, `build_final_list`, `list_missing`, `list_removed`) |
| `frameworks.py` | Frameworks release conventions and name mappings |
| `plasma.py` | Plasma release conventions and name mappings |
| `release_services.py` | Release Service conventions and name mappings |

Avoid duplicating orchestration and persistence code across the three family classes.

## 5. Data and serialization schema

Use `msgspec.Struct` for typed objects, MessagePack for binary encoding, and Zstandard for compression (`.msgpack.zst`). Use a schema version in every serialized top-level object so incompatible formats are detected instead of silently misread.

Initial logical structures:

```python
class Package(msgspec.Struct):
    name: str          # Upstream archive name, without version or extension
    url: str           # Upstream archive URL
    sha_url: str       # Upstream .sha256 URL
    sha: str           # Validated 64-character SHA-256 hexadecimal digest

class Release(msgspec.Struct):
    schema_version: int
    family: str
    version: str       # Full package release version, e.g. 6.32.0
    source_url: str
    fetched_at: str    # UTC timestamp
    packages: list[Package]

class VersionIndex(msgspec.Struct):
    schema_version: int
    fetched_at: str    # UTC timestamp of successful index discovery
    frameworks: list[str]
    plasma: list[str]
    release_services: list[str]
```

The version index records **what upstream actually lists**. For example, the Frameworks directory index can contain `6.32`, while package archives within it use `6.32.0`. During `--update-cache`, the CLI resolves the requested Frameworks directory by inspecting actual matching `.tar.xz` archive filenames. Latest picks the largest full version; Advanced lets the developer select a full version. This can require one upstream directory request even when its version index is fresh. It must not manufacture a `.0` release from a directory name. Versions for Plasma and Release Service are full in the discovery index. Package cache directories are named for the **full release version**.

The exact serialization functions and any additional metadata fields are implemented and tested in Section 1 of the implementation plan; the field meanings above are the minimum contract. Prefer field-name-based decoding to fragile position-dependent array encoding.

### Snapshot and companion manifest

`cache/<family>/<version>/packages.msgpack.zst` stores a complete, validated `Release` object, including all discovered package records for that release.

`packages.manifest.json` is derived from that **same in-memory Release**. It exposes the family, version, schema version, package count, and package checksums for human inspection. It also records the SHA-256 of `packages.msgpack.zst` to bind the two artifacts together. It is not an independently edited source of truth.

Write both through temporary files and atomic replacements, with the JSON manifest published last. A snapshot counts as complete only if both files exist, decode/validate successfully, match the requested family/version, and pass the cross-file integrity check. Do not interpret a partially written pair as complete.

### Discovery and fill timestamps

`cache/last.json` is a small human-readable status record tracking successful upstream discovery and the last successfully filled release for each family, including timestamps and versions. It is used to limit unnecessary mirror requests. Failed or partial operations **must not** advance the corresponding successful timestamp.

Discovery defaults to a **24-hour** interval per family, based on `last.json`. `--refresh-hours N` changes that interval and `--force --update-versions` bypasses it. Skipped fresh families do not contact the mirror or advance their timestamps. Errors do not publish an updated index. A manually requested `--update-cache` may still request release contents when resolving an uncached full Frameworks patch version or filling missing snapshots.

## 6. KDE discovery and checksum validation

- Frameworks mirror root: `https://download.kde.org/stable/frameworks/`
- Plasma mirror root: `https://download.kde.org/stable/plasma/`
- Release Service mirror root: `https://download.kde.org/stable/release-service/`
- Release Service archives are found under the selected version's `src/` directory.
- Frameworks directory naming (e.g. `6.32/`) differs from full archive versions (e.g. `6.32.0`).
- Select `.tar.xz` archives matching the **full selected package version**, excluding signatures and unrelated files.
- Fetch the `.tar.xz.sha256` sidecar when checksum data is missing; validate the 64-hex-character digest and ensure the sidecar filename matches its archive.
- For an already complete and valid release snapshot, neither directory scraping nor checksum requests are necessary.

`--update-versions` stores the discovered versions and creates appropriate version directory placeholders. `--update-cache` processes the selected releases and persists complete snapshots. Cache readers must not treat an empty placeholder directory as a cache hit.

## 7. Strict checksum-class input

The developer manually copies **checksum lines only** from the layer's existing class to the corresponding `in/` file:

| Family | Required input file |
| --- | --- |
| Frameworks | `in/kf6-checksums.bbclass` |
| Plasma | `in/plasma6-checksums.bbclass` |
| Release Service | `in/rs6-checksums.bbclass` |

Every input line must begin **at column zero** with `SRC_URI[` and match the checksum assignment grammar:

```bitbake
SRC_URI[bluedevil6.sha256sum] = "7e5d386a7c42a1b4ef00e79469c8f47300944c9c42023647f2efd3a12ecc3175"
```

Hard-stop on any other line (including blank lines, comments, leading whitespace, `SRCREV`, or other metadata), malformed assignments, invalid SHA-256 values, and duplicate checksum keys. No automatic cleanup, filtering, or best-effort parsing.

Retain the original local checksum keys. More than one local key may map to the same upstream archive, as with `layer-shell-qt6-kde6` and `layer-shell-qt6`; both must receive the same upstream checksum when matched.

## 8. Generation (`--generate`)

1. Require and strictly validate the manually supplied input class for each requested family.
2. Require a validated **complete** cached release for that family's selected version.
3. Resolve local checksum keys to upstream archive names using the family-specific name mapping (`override_map` first, suffix handling as appropriate).
4. Compare the local and upstream package sets. Print `list_missing()` and `list_removed()` findings for developer review.
5. For matching entries, write the **existing local checksum key** with the new upstream checksum. Multiple local keys mapping to one upstream archive remain distinct assignments.
6. For local entries absent from the new upstream snapshot, **retain their original checksum** and print a warning that it has not been verified for the selected new release. This is a retained historical value, not a claim of upstream availability.
7. For newly discovered upstream packages with no local key, **do not invent a key or add an assignment**. Print them as missing from the local checksum list for developer review and recipe creation.
8. Preserve the input assignment order. Generate **only** `SRC_URI[<existing-key>.sha256sum] = "<64-hex-digest>"` lines, with no comments, headers, `SRCREV`, or other contents.
9. Write candidates atomically under `out/<family>/<full-release-version>/<original-input-filename>`. Never overwrite the source layer classes or the `in/` files.

If a name mapping is ambiguous or conflicting, fail rather than guessing or inserting an arbitrary checksum.

The generated classes are intentionally checksum-only **fragments**. A developer may integrate the reviewed lines into the corresponding full layer class, which can contain unrelated metadata such as `SRCREV` and comments.

No `packages.report.json` is produced in the initial version. Missing/removed reports are printed to the console.

## 9. Implementation sections and acceptance criteria

Implement and test one section at a time, following this README as the contract:

1. **`schema.py` + `test_schema.py`:** Typed `Package`, `Release`, and `VersionIndex` definitions. Round-trip MessagePack and reject incompatible schema versions.
2. **`cache.py` + `test_cache.py`:** Zstandard compression, integrity-checked release/manifest pairs, index persistence, `last.json`, atomic writes, and incomplete-cache detection.
3. **`utils.py` + `test_utils.py`:** Maintain the existing KDE parser and checksum-validation logic; add correct explicit-version handling and discovery/index conventions without duplicating project-root initialization.
4. **`manifest.py` + `test_manifest.py`:** Strict checksum-only parsing; duplicate detection; mapping validation; retain local order, duplicate-to-one archive mappings, and removed historical checksums; compare and render only checksum assignment lines.
5. **`kde.py`, `frameworks.py`, `plasma.py`, `release_services.py`:** Implement shared workflow and three family-specific conventions using the tested schema/cache/manifest pieces.
6. **CLI and `test_workflow.py`:** Wire the three independent commands, default `--target all`, Latest/Advanced selection, combined-operation ordering, offline generation, and error handling.

Tests use Python standard-library `unittest` via `kde-oe-update-tests.py`. They should use fixed local HTML listings, sample checksums, and sample classes for network-dependent behavior. Confirm idempotence and that generation cannot contact the network.

## 10. Deferred work

- `packages.report.json`, unless structured reporting materially simplifies an implementation step.
- Hooked-release mappings between Frameworks, Plasma, and Release Service versions.
- Standalone KDE projects, individually maintained nonstandard archive URLs, and KDE Attic migration workflows.
- Automatic creation, relocation, activation, or removal of OpenEmbedded recipes.
- Automatic insertion of missing packages into generated checksum classes.

Any change to these contracts should be agreed upon and reflected here before modifying implementation behavior.
