# wgsl4k

[![Kotlin](https://img.shields.io/badge/Kotlin-2.4.10-purple?logo=kotlin)](https://kotlinlang.org)
[![Gradle](https://img.shields.io/badge/Gradle-9.6.1-blue?logo=gradle)](https://gradle.org)
[![CI/CD](https://img.shields.io/badge/CI%2FCD-GitHub%20Actions-blue?logo=github-actions)](https://github.com/Graphiks-org/wgsl4k/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Project status: Incubating](https://img.shields.io/badge/Status-Incubating-orange)](https://github.com/Graphiks-org/wgsl4k)

<!-- ==========================================
     BADGES DE STATUT DE PROJET PERSONNALISABLES
     Décommentez/copiez simplement le badge correspondant au statut actuel de votre projet.
     ========================================== -->

<!-- STATUT : EN PLANIFICATION (PLANNING) -->
<!-- [![Projet: Planning](https://img.shields.io/badge/Statut-Planning-blue)](https://github.com) -->

<!-- STATUT : INCUBATION / EN DÉVELOPPEMENT (INCUBATING) -->
<!-- [![Projet: Incubating](https://img.shields.io/badge/Statut-Incubating-orange)](https://github.com) -->

<!-- STATUT : STABLE / PRÊT PRODUCTION (STABLE) -->
<!-- [![Projet: Stable](https://img.shields.io/badge/Statut-Stable-green)](https://github.com) -->

<!-- STATUT : DEPRÉCIÉ (DEPRECATED) -->
<!-- [![Projet: Deprecated](https://img.shields.io/badge/Statut-Deprecated-red)](https://github.com) -->

<!-- STATUT : ARCHIVÉ (ARCHIVED) -->
<!-- [![Projet: Archived](https://img.shields.io/badge/Statut-Archived-lightgrey)](https://github.com) -->

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

Run the fast JVM suite:

```bash
./gradlew :wgsl:wgsl-core:jvmTest \
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
for the [getting-started guide](https://graphiks-org.github.io/wgsl4k/getting-started/)
and generated API references.

## Supported targets

All modules support JVM, Android, iOS Arm64, iOS Simulator Arm64, and iOS X64.
The `wgsl-tests` golden corpus and the `wgsl-cli` smoke tests are JVM-only.
JS, WasmJs, watchOS, macOS, Linux, MinGW, and Android Native are intentionally
not declared: the current CLI lacks their platform file implementations, and
the non-Apple Native runners are not available in the project CI environment.

## 🤝 Contribuer / Contributing

Les contributions sont les bienvenues ! Consultez :

Contributions are welcome! See:

- [🇬🇧 Contributing Guide](CONTRIBUTING.md)
- [🇬🇧 Code of Conduct](CODE_OF_CONDUCT.md) / [🇫🇷 Code de Conduite](CODE_OF_CONDUCT.fr.md)
- [🇬🇧 Security Policy](SECURITY.md) / [🇫🇷 Politique de Sécurité](SECURITY.fr.md)
- [🇬🇧 Support](SUPPORT.md) / [🇫🇷 Assistance](SUPPORT.fr.md)
- [Changelog](CHANGELOG.md)
