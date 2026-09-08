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

Les packages Kotlin publics restent sous `org.graphiks.wgsl.*`. Pendant la
migration par étapes, le projet `:shared` du template est conservé uniquement
comme exemple de compatibilité et est testé avec les modules WGSL.

## Commandes utiles

```bash
./gradlew :shared:jvmTest \
  :wgsl:wgsl-core:jvmTest \
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
