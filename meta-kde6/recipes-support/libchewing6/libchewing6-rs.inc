HOMEPAGE = ""
SECTION = "libs"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = " \
    git://github.com/chewing/libchewing.git;branch=release/0.10.x;protocol=https \
"
SRCREV = "93a9a24bae6173f23c620df314b55c596c7622dd"

inherit cargo cargo-update-recipe-crates pkgconfig

SRC_URI += " \
    crate://crates.io/anstream/0.6.20 \
    crate://crates.io/anstyle-parse/0.2.7 \
    crate://crates.io/anstyle-query/1.1.4 \
    crate://crates.io/anstyle-wincon/3.0.10 \
    crate://crates.io/anstyle/1.0.11 \
    crate://crates.io/anyhow/1.0.99 \
    crate://crates.io/bitflags/2.9.3 \
    crate://crates.io/cfg-if/1.0.3 \
    crate://crates.io/clap/4.5.45 \
    crate://crates.io/clap_builder/4.5.44 \
    crate://crates.io/clap_derive/4.5.45 \
    crate://crates.io/clap_lex/0.7.5 \
    crate://crates.io/clap_mangen/0.2.29 \
    crate://crates.io/colorchoice/1.0.4 \
    crate://crates.io/der/0.7.10 \
    crate://crates.io/env_filter/0.1.3 \
    crate://crates.io/env_logger/0.11.8 \
    crate://crates.io/errno/0.3.13 \
    crate://crates.io/fallible-iterator/0.3.0 \
    crate://crates.io/fallible-streaming-iterator/0.1.9 \
    crate://crates.io/fastrand/2.3.0 \
    crate://crates.io/foldhash/0.1.5 \
    crate://crates.io/getrandom/0.3.3 \
    crate://crates.io/hashbrown/0.15.5 \
    crate://crates.io/hashlink/0.10.0 \
    crate://crates.io/heck/0.5.0 \
    crate://crates.io/is_terminal_polyfill/1.70.1 \
    crate://crates.io/libc/0.2.175 \
    crate://crates.io/libsqlite3-sys/0.35.0 \
    crate://crates.io/linux-raw-sys/0.9.4 \
    crate://crates.io/log/0.4.27 \
    crate://crates.io/once_cell/1.21.3 \
    crate://crates.io/once_cell_polyfill/1.70.1 \
    crate://crates.io/pkg-config/0.3.32 \
    crate://crates.io/proc-macro2/1.0.101 \
    crate://crates.io/quote/1.0.40 \
    crate://crates.io/r-efi/5.3.0 \
    crate://crates.io/roff/0.2.2 \
    crate://crates.io/rusqlite/0.37.0 \
    crate://crates.io/rustix/1.0.8 \
    crate://crates.io/smallvec/1.15.1 \
    crate://crates.io/strsim/0.11.1 \
    crate://crates.io/syn/2.0.106 \
    crate://crates.io/tempfile/3.21.0 \
    crate://crates.io/unicode-ident/1.0.18 \
    crate://crates.io/utf8parse/0.2.2 \
    crate://crates.io/vcpkg/0.2.15 \
    crate://crates.io/wasi/0.14.2+wasi-0.2.4 \
    crate://crates.io/windows-link/0.1.3 \
    crate://crates.io/windows-sys/0.60.2 \
    crate://crates.io/windows-targets/0.53.3 \
    crate://crates.io/windows_aarch64_gnullvm/0.53.0 \
    crate://crates.io/windows_aarch64_msvc/0.53.0 \
    crate://crates.io/windows_i686_gnu/0.53.0 \
    crate://crates.io/windows_i686_gnullvm/0.53.0 \
    crate://crates.io/windows_i686_msvc/0.53.0 \
    crate://crates.io/windows_x86_64_gnu/0.53.0 \
    crate://crates.io/windows_x86_64_gnullvm/0.53.0 \
    crate://crates.io/windows_x86_64_msvc/0.53.0 \
    crate://crates.io/wit-bindgen-rt/0.39.0 \
    crate://crates.io/zeroize/1.8.1 \
"
SRC_URI[anstream-0.6.20.sha256sum] = "3ae563653d1938f79b1ab1b5e668c87c76a9930414574a6583a7b7e11a8e6192"
SRC_URI[anstyle-parse-0.2.7.sha256sum] = "4e7644824f0aa2c7b9384579234ef10eb7efb6a0deb83f9630a49594dd9c15c2"
SRC_URI[anstyle-query-1.1.4.sha256sum] = "9e231f6134f61b71076a3eab506c379d4f36122f2af15a9ff04415ea4c3339e2"
SRC_URI[anstyle-wincon-3.0.10.sha256sum] = "3e0633414522a32ffaac8ac6cc8f748e090c5717661fddeea04219e2344f5f2a"
SRC_URI[anstyle-1.0.11.sha256sum] = "862ed96ca487e809f1c8e5a8447f6ee2cf102f846893800b20cebdf541fc6bbd"
SRC_URI[anyhow-1.0.99.sha256sum] = "b0674a1ddeecb70197781e945de4b3b8ffb61fa939a5597bcf48503737663100"
SRC_URI[bitflags-2.9.3.sha256sum] = "34efbcccd345379ca2868b2b2c9d3782e9cc58ba87bc7d79d5b53d9c9ae6f25d"
SRC_URI[cfg-if-1.0.3.sha256sum] = "2fd1289c04a9ea8cb22300a459a72a385d7c73d3259e2ed7dcb2af674838cfa9"
SRC_URI[clap-4.5.45.sha256sum] = "1fc0e74a703892159f5ae7d3aac52c8e6c392f5ae5f359c70b5881d60aaac318"
SRC_URI[clap_builder-4.5.44.sha256sum] = "b3e7f4214277f3c7aa526a59dd3fbe306a370daee1f8b7b8c987069cd8e888a8"
SRC_URI[clap_derive-4.5.45.sha256sum] = "14cb31bb0a7d536caef2639baa7fad459e15c3144efefa6dbd1c84562c4739f6"
SRC_URI[clap_lex-0.7.5.sha256sum] = "b94f61472cee1439c0b966b47e3aca9ae07e45d070759512cd390ea2bebc6675"
SRC_URI[clap_mangen-0.2.29.sha256sum] = "27b4c3c54b30f0d9adcb47f25f61fcce35c4dd8916638c6b82fbd5f4fb4179e2"
SRC_URI[colorchoice-1.0.4.sha256sum] = "b05b61dc5112cbb17e4b6cd61790d9845d13888356391624cbe7e41efeac1e75"
SRC_URI[der-0.7.10.sha256sum] = "e7c1832837b905bbfb5101e07cc24c8deddf52f93225eee6ead5f4d63d53ddcb"
SRC_URI[env_filter-0.1.3.sha256sum] = "186e05a59d4c50738528153b83b0b0194d3a29507dfec16eccd4b342903397d0"
SRC_URI[env_logger-0.11.8.sha256sum] = "13c863f0904021b108aa8b2f55046443e6b1ebde8fd4a15c399893aae4fa069f"
SRC_URI[errno-0.3.13.sha256sum] = "778e2ac28f6c47af28e4907f13ffd1e1ddbd400980a9abd7c8df189bf578a5ad"
SRC_URI[fallible-iterator-0.3.0.sha256sum] = "2acce4a10f12dc2fb14a218589d4f1f62ef011b2d0cc4b3cb1bba8e94da14649"
SRC_URI[fallible-streaming-iterator-0.1.9.sha256sum] = "7360491ce676a36bf9bb3c56c1aa791658183a54d2744120f27285738d90465a"
SRC_URI[fastrand-2.3.0.sha256sum] = "37909eebbb50d72f9059c3b6d82c0463f2ff062c9e95845c43a6c9c0355411be"
SRC_URI[foldhash-0.1.5.sha256sum] = "d9c4f5dac5e15c24eb999c26181a6ca40b39fe946cbe4c263c7209467bc83af2"
SRC_URI[getrandom-0.3.3.sha256sum] = "26145e563e54f2cadc477553f1ec5ee650b00862f0a58bcd12cbdc5f0ea2d2f4"
SRC_URI[hashbrown-0.15.5.sha256sum] = "9229cfe53dfd69f0609a49f65461bd93001ea1ef889cd5529dd176593f5338a1"
SRC_URI[hashlink-0.10.0.sha256sum] = "7382cf6263419f2d8df38c55d7da83da5c18aef87fc7a7fc1fb1e344edfe14c1"
SRC_URI[heck-0.5.0.sha256sum] = "2304e00983f87ffb38b55b444b5e3b60a884b5d30c0fca7d82fe33449bbe55ea"
SRC_URI[is_terminal_polyfill-1.70.1.sha256sum] = "7943c866cc5cd64cbc25b2e01621d07fa8eb2a1a23160ee81ce38704e97b8ecf"
SRC_URI[libc-0.2.175.sha256sum] = "6a82ae493e598baaea5209805c49bbf2ea7de956d50d7da0da1164f9c6d28543"
SRC_URI[libsqlite3-sys-0.35.0.sha256sum] = "133c182a6a2c87864fe97778797e46c7e999672690dc9fa3ee8e241aa4a9c13f"
SRC_URI[linux-raw-sys-0.9.4.sha256sum] = "cd945864f07fe9f5371a27ad7b52a172b4b499999f1d97574c9fa68373937e12"
SRC_URI[log-0.4.27.sha256sum] = "13dc2df351e3202783a1fe0d44375f7295ffb4049267b0f3018346dc122a1d94"
SRC_URI[once_cell-1.21.3.sha256sum] = "42f5e15c9953c5e4ccceeb2e7382a716482c34515315f7b03532b8b4e8393d2d"
SRC_URI[once_cell_polyfill-1.70.1.sha256sum] = "a4895175b425cb1f87721b59f0f286c2092bd4af812243672510e1ac53e2e0ad"
SRC_URI[pkg-config-0.3.32.sha256sum] = "7edddbd0b52d732b21ad9a5fab5c704c14cd949e5e9a1ec5929a24fded1b904c"
SRC_URI[proc-macro2-1.0.101.sha256sum] = "89ae43fd86e4158d6db51ad8e2b80f313af9cc74f5c0e03ccb87de09998732de"
SRC_URI[quote-1.0.40.sha256sum] = "1885c039570dc00dcb4ff087a89e185fd56bae234ddc7f056a945bf36467248d"
SRC_URI[r-efi-5.3.0.sha256sum] = "69cdb34c158ceb288df11e18b4bd39de994f6657d83847bdffdbd7f346754b0f"
SRC_URI[roff-0.2.2.sha256sum] = "88f8660c1ff60292143c98d08fc6e2f654d722db50410e3f3797d40baaf9d8f3"
SRC_URI[rusqlite-0.37.0.sha256sum] = "165ca6e57b20e1351573e3729b958bc62f0e48025386970b6e4d29e7a7e71f3f"
SRC_URI[rustix-1.0.8.sha256sum] = "11181fbabf243db407ef8df94a6ce0b2f9a733bd8be4ad02b4eda9602296cac8"
SRC_URI[smallvec-1.15.1.sha256sum] = "67b1b7a3b5fe4f1376887184045fcf45c69e92af734b7aaddc05fb777b6fbd03"
SRC_URI[strsim-0.11.1.sha256sum] = "7da8b5736845d9f2fcb837ea5d9e2628564b3b043a70948a3f0b778838c5fb4f"
SRC_URI[syn-2.0.106.sha256sum] = "ede7c438028d4436d71104916910f5bb611972c5cfd7f89b8300a8186e6fada6"
SRC_URI[tempfile-3.21.0.sha256sum] = "15b61f8f20e3a6f7e0649d825294eaf317edce30f82cf6026e7e4cb9222a7d1e"
SRC_URI[unicode-ident-1.0.18.sha256sum] = "5a5f39404a5da50712a4c1eecf25e90dd62b613502b7e925fd4e4d19b5c96512"
SRC_URI[utf8parse-0.2.2.sha256sum] = "06abde3611657adf66d383f00b093d7faecc7fa57071cce2578660c9f1010821"
SRC_URI[vcpkg-0.2.15.sha256sum] = "accd4ea62f7bb7a82fe23066fb0957d48ef677f6eeb8215f372f52e48bb32426"
SRC_URI[wasi-0.14.2+wasi-0.2.4.sha256sum] = "9683f9a5a998d873c0d21fcbe3c083009670149a8fab228644b8bd36b2c48cb3"
SRC_URI[windows-link-0.1.3.sha256sum] = "5e6ad25900d524eaabdbbb96d20b4311e1e7ae1699af4fb28c17ae66c80d798a"
SRC_URI[windows-sys-0.60.2.sha256sum] = "f2f500e4d28234f72040990ec9d39e3a6b950f9f22d3dba18416c35882612bcb"
SRC_URI[windows-targets-0.53.3.sha256sum] = "d5fe6031c4041849d7c496a8ded650796e7b6ecc19df1a431c1a363342e5dc91"
SRC_URI[windows_aarch64_gnullvm-0.53.0.sha256sum] = "86b8d5f90ddd19cb4a147a5fa63ca848db3df085e25fee3cc10b39b6eebae764"
SRC_URI[windows_aarch64_msvc-0.53.0.sha256sum] = "c7651a1f62a11b8cbd5e0d42526e55f2c99886c77e007179efff86c2b137e66c"
SRC_URI[windows_i686_gnu-0.53.0.sha256sum] = "c1dc67659d35f387f5f6c479dc4e28f1d4bb90ddd1a5d3da2e5d97b42d6272c3"
SRC_URI[windows_i686_gnullvm-0.53.0.sha256sum] = "9ce6ccbdedbf6d6354471319e781c0dfef054c81fbc7cf83f338a4296c0cae11"
SRC_URI[windows_i686_msvc-0.53.0.sha256sum] = "581fee95406bb13382d2f65cd4a908ca7b1e4c2f1917f143ba16efe98a589b5d"
SRC_URI[windows_x86_64_gnu-0.53.0.sha256sum] = "2e55b5ac9ea33f2fc1716d1742db15574fd6fc8dadc51caab1c16a3d3b4190ba"
SRC_URI[windows_x86_64_gnullvm-0.53.0.sha256sum] = "0a6e035dd0599267ce1ee132e51c27dd29437f63325753051e71dd9e42406c57"
SRC_URI[windows_x86_64_msvc-0.53.0.sha256sum] = "271414315aff87387382ec3d271b52d7ae78726f5d44ac98b4f4030c91880486"
SRC_URI[wit-bindgen-rt-0.39.0.sha256sum] = "6f42320e61fe2cfd34354ecb597f86f413484a798ba44a8ca1165c58d42da6c1"
SRC_URI[zeroize-1.8.1.sha256sum] = "ced3678a2879b30306d323f4542626697a464a97c0a07c9aebf7ebca65cd4dde"
