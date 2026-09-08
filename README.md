# wgsl4k

[![Kotlin](https://img.shields.io/badge/Kotlin-2.4.10-purple?logo=kotlin)](https://kotlinlang.org)
[![Gradle](https://img.shields.io/badge/Gradle-9.6.1-blue?logo=gradle)](https://gradle.org)
[![CI/CD](https://img.shields.io/badge/CI%2FCD-GitHub%20Actions-blue?logo=github-actions)](https://github.com/ygdrasil-io/wgsl4k-fork/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Project status: Incubating](https://img.shields.io/badge/Status-Incubating-orange)](https://github.com/ygdrasil-io/wgsl4k-fork)

`wgsl4k` is an incubating Kotlin Multiplatform toolkit for reading, validating,
transforming, and emitting WebGPU Shading Language (WGSL) programs. It keeps a
typed intermediate representation (IR) between parsing and code generation so
the same shader program can target WGSL, GLSL, HLSL, MSL, and a JSON IR view.

## Modules

| Gradle project | Artifact | Purpose |
| --- | --- | --- |
| `:wgsl:wgsl-core` | `org.graphiks:wgsl-core` | IR, arenas, validation, layout, and backend abstractions. |
| `:wgsl:wgsl-parser` | `org.graphiks:wgsl-parser` | Lexer, AST, diagnostics, parser, resolution, and lowering to the IR. |
| `:wgsl:wgsl-generator` | `org.graphiks:wgsl-generator` | Generators for WGSL, GLSL, HLSL, and MSL. |
| `:wgsl:wgsl-tests` | `org.graphiks:wgsl-tests` | JVM golden-test corpus and backend coverage tooling. |
| `:wgsl:wgsl-cli` | `org.graphiks:wgsl-cli` | JVM command-line conversion tool. |

The project is incubating: public APIs and supported targets may evolve while
the migration and ABI baselines are completed.

## Development

Run the fast JVM suite for the currently integrated modules:

```bash
./gradlew :shared:jvmTest \
  :wgsl:wgsl-core:jvmTest \
  :wgsl:wgsl-parser:jvmTest \
  :wgsl:wgsl-generator:jvmTest \
  :wgsl:wgsl-tests:jvmTest \
  :wgsl:wgsl-cli:jvmTest --no-daemon
```

Generate API documentation and build the documentation site:

```bash
./gradlew :docs:embedDokkaIntoMkDocs --no-daemon
mkdocs build -f docs/mkdocs.yml
```

The published documentation is built from the `:docs` project. See the site
for the [getting-started guide](https://ygdrasil-io.github.io/wgsl4k-fork/getting-started/)
and generated API references.

## 🤝 Contribuer / Contributing

Les contributions sont les bienvenues ! Consultez :

Contributions are welcome! See:

- [🇬🇧 Contributing Guide](CONTRIBUTING.md)
- [🇬🇧 Code of Conduct](CODE_OF_CONDUCT.md) / [🇫🇷 Code de Conduite](CODE_OF_CONDUCT.fr.md)
- [🇬🇧 Security Policy](SECURITY.md) / [🇫🇷 Politique de Sécurité](SECURITY.fr.md)
- [🇬🇧 Support](SUPPORT.md) / [🇫🇷 Assistance](SUPPORT.fr.md)
- [Changelog](CHANGELOG.md)
