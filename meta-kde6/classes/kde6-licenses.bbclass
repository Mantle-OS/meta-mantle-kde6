do_populate_lic:prepend() {
    import os
    import re

    srcdir = d.getVar('S')
    licenses_dir = os.path.join(srcdir, 'LICENSES')

    # Unfortunately license text may differ in whitespace and empty lines.
    checksum_map = {
        'LGPL-2.0-only': [
            '6d2d9952d88b50a51a5c73dc431d06c7',
            '41890f71f740302b785c27661123bff5'
        ],
        'LGPL-2.0-or-later': [
            '6d2d9952d88b50a51a5c73dc431d06c7',
            '65d3616852dbf7b1a6d4b53b00626032'
        ],
        'LGPL-2.1-only': [
            'fabba2a3bfeb22a6483d44e9ae824d3f'
        ],
        'LGPL-2.1-or-later': [
            '2a4f4fd2128ea2f65047ee63fbca9f68',
            '41890f71f740302b785c27661123bff5',
            '147a320ed8b16b036829a0c71d424153'
        ],
        'LGPL-3.0-only': [
            'c51d3eef3be114124d11349ca0d7e117'
        ],
        'LGPL-3.0-or-later': [
            'c51d3eef3be114124d11349ca0d7e117'
        ],
        'GPL-2.0-only': [
            '93e64b4a83c7e441e48bbdeeea05c977'
        ],
        'GPL-2.0-or-later': [
            'fed54355545ffd980b814dab4a3b312c',
            '9e2385fe012386d34dcc5c9863070881'
        ],
        'GPL-3.0-only': [
            '1c76c4cc354acaac30ed4d5eefea7245'
        ],
        'GPL-3.0-or-later': [
            '1c76c4cc354acaac30ed4d5eefea7245'
        ],
        'BSD-2-Clause': [
            '63d6ee386b8aaba70b1bf15a79ca50f2',
            'd0f280d1058e77e66264a9b9e10e6c89'
        ],
        'BSD-3-Clause': [
            '954f4d71a37096249f837652a7f586c0'
        ],
        'MIT': [
            '38aa75cf4c4c87f018227d5ec9638d75'
        ],
        'MIT-CMU': [
            '91b70218e0db8e063ed88cd532cb801d',
            'b6936b5be2ab721140d9bf57c37b0b92'
        ],
        'LicenseRef-KDE-Accepted-LGPL': [
            '6a2eced623a7c9d0c8996ce24917d006',
            'e4b79a181b6483b37d39a27f4d75e60a'
        ],
        'LicenseRef-KDE-Accepted-GPL': [
            'b8a14d4a3321110bced049d26f38c352',
            'b4c280013bbbadfbe92219498dc5228c'
        ],
        'Unicode-DFS-2016': [
            '3daac258519ec32945c80ae0f7ba88f4'
        ],
        'CC0-1.0': [
            '6fd064768b8d61c31ddd0540570fbd33',
            '65d3616852dbf7b1a6d4b53b00626032'
        ],
    }

    # Generate a flat list of license identifiers from SPDX expressions.
    # Support legacy BitBake operators (& and |) as well.
    package_license_statement = d.getVar('LICENSE') or ''
    package_license_statement = re.sub(r'[()&|]', ' ', package_license_statement)

    license_operators = {'AND', 'OR', 'WITH'}

    recipe_licenses = list(dict.fromkeys(
        license_id for license_id in package_license_statement.split()
        if license_id.upper() not in license_operators
    ))

    # REUSE license QA whitelist.
    package_license_qa_whitelist = d.getVar('REUSE_LICENSECHECK_WHITELIST') or ''
    recipe_licenses_qa_whitelist = set(package_license_qa_whitelist.split())
    recipe_licenses_qa_whitelist.add('CC0-1.0')

    # Do not override explicit recipe license checksums.
    lic_files_chksum = d.getVar('LIC_FILES_CHKSUM') or ''

    if lic_files_chksum.strip():
        bb.note("Skip LIC_FILES_CHKSUM computation, value already set to: %s" % lic_files_chksum)
    else:
        if not os.path.isdir(licenses_dir):
            bb.fatal("REUSE license directory not found: %s" % licenses_dir)

        checksum_entries = []
        missing_licenses = []

        for license_id in recipe_licenses:
            license_file_path = os.path.join(licenses_dir, license_id + '.txt')

            # Not every alternative in an SPDX OR expression must have
            # a corresponding license file in the source archive.
            if not os.path.isfile(license_file_path):
                missing_licenses.append(license_id)
                continue

            # Reject unknown license identifiers instead of raising KeyError.
            if license_id not in checksum_map:
                bb.fatal(
                    "QA Issue: %s has no accepted checksums in kde6-licenses [reuse_license]" %
                    license_id
                )

            md5chksum = bb.utils.md5_file(license_file_path)

            if md5chksum not in checksum_map[license_id]:
                bb.fatal(
                    "QA Issue: %s has an unrecognized checksum: %s [reuse_license]" %
                    (license_id, md5chksum)
                )

            entry = "file://LICENSES/%s.txt;md5=%s" % (license_id, md5chksum)
            checksum_entries.append(entry)

        if not checksum_entries:
            bb.fatal(
                "Unable to generate LIC_FILES_CHKSUM for %s: no recognized license files found. "
                "LICENSE=%s; missing=%s" %
                (d.getVar('PN'), d.getVar('LICENSE'), ', '.join(missing_licenses))
            )

        d.setVar('LIC_FILES_CHKSUM', ' '.join(checksum_entries))
        bb.note("Set LIC_FILES_CHKSUM from known SPDX files to: %s" % d.getVar('LIC_FILES_CHKSUM'))

    # QA test for complete REUSE license statement.
    if d.getVar('KF6_REUSE_LICENSECHECK_ENABLED') == '1':
        if not os.path.isdir(licenses_dir):
            bb.warn(
                "QA Issue: LICENSES directory not found: %s [reuse_license]" %
                licenses_dir
            )
        else:
            recipe_license_set = set(recipe_licenses)

            for license_file in sorted(os.listdir(licenses_dir)):
                if not license_file.endswith('.txt'):
                    continue

                license_name = license_file[:-4]

                if license_name in recipe_license_set:
                    continue

                if license_name in recipe_licenses_qa_whitelist:
                    continue

                bb.warn(
                    "QA Issue: %s found in SRC but not in package license statement [reuse_license]" %
                    license_name
                )
}
