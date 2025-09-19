#!/usr/bin/env bash
out=checksums.inc
echo '# Auto-generated checksums for KF6 tarballs' > "$out"
find . -type f -name '*_mirror.bb' -exec \
  awk '
    /SRC_URI\[sha256sum\]/ {
      n = split(FILENAME, a, "/"); pkg = a[n-1];
      if (match($0, /"([0-9a-fA-F]{64})"/, m))
        printf "SRC_URI[%s.sha256sum] = \"%s\"\n", pkg, m[1];
    }
    /SRC_URI\[[^]]+\.sha256sum\]/ {
      if (match($0, /SRC_URI\[([^]]+)\.sha256sum\].*"(.*)"/, m))
        printf "SRC_URI[%s.sha256sum] = \"%s\"\n", m[1], m[2];
    }
  ' {} + | sort -u >> "$out"

