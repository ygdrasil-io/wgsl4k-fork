# Changelog

All notable changes to `wgsl4k` will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [Unreleased]

### Added

- `wgsl-core`: typed intermediate representation, arenas, validation, layout,
  and backend abstractions.
- `wgsl-parser`: WGSL lexer, AST, diagnostics, parser, type resolution, and
  lowering to the intermediate representation.
- `wgsl-generator`: WGSL, GLSL, HLSL, and MSL code generators, with a JSON IR
  view.
- `wgsl-tests`: JVM golden corpus, round-trip tests, and backend coverage
  reporting.
- `wgsl-cli`: JVM command-line conversion tool.
- Kotlin Multiplatform targets for JVM, Android, iOS Arm64, iOS X64, and iOS
  Simulator Arm64.

### Changed

- Organized the project into the `wgsl-core`, `wgsl-parser`, `wgsl-generator`,
  `wgsl-tests`, and `wgsl-cli` modules.
- Public Kotlin packages are now under the `org.graphiks.wgsl.*` namespace.
- Shader programs are processed through a typed intermediate representation
  between parsing and code generation.
