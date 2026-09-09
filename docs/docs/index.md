# wgsl4k documentation

`wgsl4k` is an incubating Kotlin Multiplatform toolkit for working with WebGPU
Shading Language (WGSL). It parses WGSL into a typed intermediate representation
(IR), validates and transforms that representation, then emits WGSL, GLSL, HLSL,
MSL, or a JSON IR representation.

## Project structure

The WGSL implementation is split into five Gradle projects under `wgsl/`:

| Project | Role |
| --- | --- |
| `:wgsl:wgsl-core` | IR, arenas, validation, layout, and backend contracts. |
| `:wgsl:wgsl-parser` | Lexer, AST, parser diagnostics, resolution, and lowering. |
| `:wgsl:wgsl-generator` | WGSL, GLSL, HLSL, and MSL writers. |
| `:wgsl:wgsl-tests` | JVM golden corpus and coverage reports for every backend. |
| `:wgsl:wgsl-cli` | JVM CLI for shader conversion. |

Public Kotlin packages remain under `org.graphiks.wgsl.*`. The starter sample
has been removed; only the WGSL modules are built and tested.

## Useful commands

```bash
./gradlew :wgsl:wgsl-core:jvmTest \
  :wgsl:wgsl-parser:jvmTest \
  :wgsl:wgsl-generator:jvmTest \
  :wgsl:wgsl-tests:jvmTest \
  :wgsl:wgsl-cli:jvmTest --no-daemon
```

Generate and embed the Dokka API modules, then build this site:

```bash
./gradlew :docs:embedDokkaIntoMkDocs --no-daemon
mkdocs build -f docs/mkdocs.yml
```

See [Getting Started](getting-started.md) for the migration strategy, local
development workflow, and CLI commands.

## Supported targets

The library modules compile for JVM, Android, iOS Arm64, iOS Simulator Arm64,
and iOS X64. Golden tests and CLI smoke tests run on JVM. JS, WasmJs, watchOS,
macOS, Linux, MinGW, and Android Native are not declared because the CLI does
not yet provide the required platform file implementations; non-Apple Native
targets also lack a compatible CI runner.
