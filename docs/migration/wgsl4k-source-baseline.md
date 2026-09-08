# Baseline d’import de wgsl4k

Date de l’audit : 2026-09-08

## Source de référence

- Dépôt : `https://github.com/ygdrasil-io/wgsl4k.git`
- Branche observée : `master`
- Commit gelé : `6f6521c00c8bf6ae46288059b007f24575000cf2`
- Sujet : `fix(parser): for-statement init clause consumed a double semicolon (#13)`
- Date du commit : `2026-06-30T01:04:04+02:00`
- Vérification distante : la branche `master` pointe actuellement sur ce même commit.

Le commit est utilisé comme baseline (état de référence) pour le premier import. Les mises à jour amont seront traitées séparément après l’intégration initiale.

## État du dépôt cible

- Branche de travail : `codex/phase-0-baseline`
- Commit de départ : `d3bd546e68110f3333b8319b8ae08b311d633387`
- Projet actuel : starter pack KMP avec `:shared` et `:docs`
- Wrapper Gradle : `9.6.1`
- Kotlin : `2.4.10`
- AGP : `9.0.0`
- Java toolchain : `25`
- Targets actuelles du convention plugin : JVM, Android, iOS Arm64 et iOS Simulator Arm64

## Modules à importer

| Projet source | Artifact prévu | Dépendances projet | Fichiers Kotlin main |
|---|---|---|---:|
| `wgsl/core` | `wgsl-core` | aucune | 12 |
| `wgsl/parser` | `wgsl-parser` | `wgsl-core` | 11 |
| `wgsl/generator` | `wgsl-generator` | `wgsl-core`, `wgsl-parser` | 10 |
| `wgsl/tests` | `wgsl-tests` | `wgsl-core`, `wgsl-parser`, `wgsl-generator` | 5 |
| `wgsl/cli` | `wgsl-cli` | `wgsl-core`, `wgsl-parser`, `wgsl-generator` | 2 |

Ordre d’intégration retenu :

```text
wgsl-core → wgsl-parser → wgsl-generator → wgsl-tests → wgsl-cli
```

## Packages publics observés

- `org.graphiks.wgsl.arena`
- `org.graphiks.wgsl.back`
- `org.graphiks.wgsl.ir`
- `org.graphiks.wgsl.proc`
- `org.graphiks.wgsl.valid`
- `org.graphiks.wgsl.ast`
- `org.graphiks.wgsl.lexer`
- `org.graphiks.wgsl.parser`
- `org.graphiks.wgsl.generator.*`
- `org.graphiks.wgsl.cli`

Ces packages sont la cible convenue et ne seront pas renommés pendant la migration.

## Tests golden

Le corpus source contient 52 entrées WGSL et 52 sorties attendues pour chacun des backends suivants :

- GLSL (`.glsl`)
- HLSL (`.hlsl`)
- MSL (`.metal`)
- IR (`.json`)

Le module `wgsl-tests` dépend des trois modules fonctionnels et porte les tests golden, le rapport de couverture et l’outil de diagnostic `goldenDebug`.

## Versions et targets source

Le source utilise notamment :

- Gradle `9.5.1`
- Kotlin `2.3.21`
- AGP `8.12.3`
- Java `25`
- Coroutines `1.10.2`
- Kotest `6.1.11`
- Kover `0.9.8`
- Clikt `5.0.2`

Les modules `core`, `parser` et `generator` déclarent JVM, Android Library, JS, WasmJs, iOS, watchOS, macOS, Linux, MinGW et Android Native. Le premier import limitera volontairement la matrice aux targets du template ; l’extension sera traitée après stabilisation.

## Audit des fichiers d’API

Treize fichiers `.api` sont suivis dans les modules source. L’audit montre deux générations de signatures :

- plusieurs fichiers historiques exposent `io/ygdrasil/wgsl/...` ;
- les fichiers `wgsl-*.api` plus récents exposent `org/graphiks/wgsl/...`.

Les sources Kotlin observées exposent `org.graphiks.wgsl.*`. Les fichiers d’API seront donc régénérés après l’adaptation Gradle, puis vérifiés sans changement de namespace public.

## Licence et notices

Le fichier `LICENSE` du dépôt cible a été aligné sur celui du source :

```text
MIT License
Copyright (c) 2026 graphiks contributors
```

Certains shaders du corpus golden contiennent également des notices MIT inline, notamment pour Ian McEwan, Stefan Gustavson et Munrocket. Ces notices devront être conservées lors de l’import des fixtures.

## Baselines de validation

Les commandes suivantes sont les gates de la phase 0 et devront être rejouées depuis la branche d’intégration :

```bash
./gradlew :shared:jvmTest --no-daemon
```

```bash
./gradlew :wgsl:wgsl-cli:jvmTest \
          :wgsl:wgsl-core:jvmTest \
          :wgsl:wgsl-generator:jvmTest \
          :wgsl:wgsl-parser:jvmTest \
          :wgsl:wgsl-parser:koverVerifyJvm \
          :wgsl:wgsl-tests:jvmTest \
          --no-daemon
```

La baseline source exécutée avant l’import est verte. Elle produit néanmoins des warnings de configuration Dokka et de targets Native dépréciées ; ces points sont explicitement hors périmètre de la phase 0.
