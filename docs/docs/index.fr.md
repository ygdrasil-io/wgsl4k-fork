# Documentation wgsl4k

`wgsl4k` est une boîte à outils Kotlin Multiplatform, actuellement en
incubation, pour travailler avec le WebGPU Shading Language (WGSL). Elle analyse
le WGSL dans une représentation intermédiaire typée (IR), la valide et la
transforme, puis produit du WGSL, GLSL, HLSL, MSL ou une représentation JSON de
l'IR.

## Structure du projet

L'implémentation WGSL est organisée dans cinq projets Gradle sous `wgsl/` :

| Projet | Rôle |
| --- | --- |
| `:wgsl:wgsl-core` | IR, arenas, validation, layout et contrats de backend. |
| `:wgsl:wgsl-parser` | Lexer, AST, diagnostics, parser, résolution et lowering. |
| `:wgsl:wgsl-generator` | Générateurs WGSL, GLSL, HLSL et MSL. |
| `:wgsl:wgsl-tests` | Corpus golden JVM et rapports de couverture des backends. |
| `:wgsl:wgsl-cli` | CLI JVM de conversion de shaders. |

Les packages Kotlin publics restent sous `org.graphiks.wgsl.*`. Le sample de
démarrage a été supprimé : seuls les modules WGSL sont construits et testés.

## Commandes utiles

```bash
./gradlew :wgsl:wgsl-core:jvmTest \
  :wgsl:wgsl-parser:jvmTest \
  :wgsl:wgsl-generator:jvmTest \
  :wgsl:wgsl-tests:jvmTest \
  :wgsl:wgsl-cli:jvmTest --no-daemon
```

Générer et intégrer les modules API Dokka, puis construire le site :

```bash
./gradlew :docs:embedDokkaIntoMkDocs --no-daemon
mkdocs build -f docs/mkdocs.yml
```

Consultez [Bien démarrer](getting-started.md) pour la stratégie de migration,
le flux de développement local et les commandes de la CLI.

## Targets supportées

Les modules de bibliothèque compilent pour JVM, Android, iOS Arm64, iOS
Simulator Arm64 et iOS X64. Les tests golden et les smoke tests de la CLI
s’exécutent sur JVM. JS, WasmJs, watchOS, macOS, Linux, MinGW et Android Native
ne sont pas déclarées : la CLI ne fournit pas encore les implémentations de
fichiers spécifiques requises, et les targets Native hors Apple n’ont pas de
runner CI compatible.
