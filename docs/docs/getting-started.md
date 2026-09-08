# Getting started with wgsl4k

## Repository layout

The migration keeps the WGSL code in `wgsl/` and leaves the template's `:shared`
project in place until the final cleanup phase. Work on the WGSL projects by
their Gradle paths:

```text
wgsl/
├── core/       :wgsl:wgsl-core
├── parser/     :wgsl:wgsl-parser
├── generator/  :wgsl:wgsl-generator
├── tests/      :wgsl:wgsl-tests
└── cli/        :wgsl:wgsl-cli
```

The dependency flow is `core → parser → generator`; `tests` and `cli` consume
those three modules. The golden corpus is stored at `tests/golden/` so it is
shared by the JVM test module without becoming a published runtime dependency.

## Run the JVM workflow

```bash
./gradlew :shared:jvmTest \
  :wgsl:wgsl-core:jvmTest \
  :wgsl:wgsl-parser:jvmTest \
  :wgsl:wgsl-generator:jvmTest \
  :wgsl:wgsl-tests:jvmTest \
  :wgsl:wgsl-cli:jvmTest --no-daemon
```

The golden coverage report is available with:

```bash
./gradlew :wgsl:wgsl-tests:goldenCoverageReport --no-daemon
```

## Import strategy

Each migration phase preserves the source package namespace
`org.graphiks.wgsl.*` and its corresponding module boundary. Import source and
tests into the matching module, keep the generated ABI files aligned with the
compiled public API, and run the module's JVM task before advancing the stack.

The source baseline, golden fixtures, and Kotlin package names are deliberately
kept stable. Later cleanup removes the starter sample only after all WGSL
modules, documentation, CI, and publication metadata have been validated.

## Build documentation

The `:docs` project collects Dokka output from all five WGSL modules and embeds
it into MkDocs Material:

```bash
./gradlew :docs:embedDokkaIntoMkDocs --no-daemon
mkdocs build -f docs/mkdocs.yml
```

## Publication

WGSL artifacts use the `org.graphiks` group and the `wgsl-*` artifact names.
Publication runs only from the protected release branch after ABI validation and
only when Maven Central and signing credentials are available.
