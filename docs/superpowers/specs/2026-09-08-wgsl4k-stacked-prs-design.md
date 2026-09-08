# Stack de PRs pour la migration wgsl4k

## Objectif

Organiser l’intégration progressive des sources de `ygdrasil-io/wgsl4k` dans le template KMP de `ygdrasil-io/wgsl4k-fork` au moyen de PRs empilées (stacked PRs), chacune ayant un périmètre cohérent, une validation dédiée et une dépendance explicite vers la PR précédente.

La stack doit aboutir à une branche `feat/incubation` contenant l’ensemble de la migration, sans importer prématurément des éléments non nécessaires du dépôt source.

## Contraintes invariantes

- Commit source de référence : `6f6521c00c8bf6ae46288059b007f24575000cf2`.
- Packages publics Kotlin : `org.graphiks.wgsl.*` ; aucun renommage vers `io.ygdrasil`.
- Licence : conserver exactement le `LICENSE` du dépôt source, ainsi que les notices présentes dans les fixtures.
- Dépôt hôte : conserver le template, son wrapper Gradle, `:docs` et ses fichiers de gouvernance.
- Targets initiales : JVM, Android et iOS du template ; les autres targets seront réactivées après stabilisation.
- `buildSrc` spécifique aux générateurs WebGPU/LLM du source : exclu de l’import initial.
- Chaque PR doit utiliser un head branch préfixé par `feat/`, `fix/` ou `chore/`, un titre Conventional Commit et le template de PR du dépôt.
- Aucun checkbox de l’issue ne sera coché avant que le livrable correspondant soit effectivement intégré ou validé.

## Topologie de la stack

La première PR existante utilise un head `codex/phase-0-baseline`, incompatible avec la règle de contribution du dépôt. Elle sera remplacée par une PR équivalente depuis `feat/phase-0-baseline`, puis l’ancienne PR sera fermée comme superseded (remplacée).

| Phase | Head branch | Base branch | Livrable principal |
|---:|---|---|---|
| 0 | `feat/phase-0-baseline` | `feat/incubation` | Cadrage, provenance, licence et baseline |
| 1 | `feat/phase-1-gradle` | `feat/phase-0-baseline` | Structure Gradle du template |
| 2 | `feat/phase-2-core` | `feat/phase-1-gradle` | Module `wgsl-core` |
| 3 | `feat/phase-3-parser` | `feat/phase-2-core` | Module `wgsl-parser` |
| 4 | `feat/phase-4-generator` | `feat/phase-3-parser` | Module `wgsl-generator` |
| 5 | `feat/phase-5-tests-golden` | `feat/phase-4-generator` | Tests golden et intégration |
| 6 | `feat/phase-6-cli` | `feat/phase-5-tests-golden` | Module `wgsl-cli` |
| 7 | `feat/phase-7-docs-ci` | `feat/phase-6-cli` | Documentation, CI et publication |
| 8 | `feat/phase-8-cleanup` | `feat/phase-7-docs-ci` | Nettoyage, suppression de `:shared` et extension des targets |

Une PR enfant montre donc uniquement son delta par rapport à sa base immédiate. Après fusion squash (fusion avec un seul commit) de la PR parente, la PR enfant sera rebasée sur le nouveau commit de sa base et sa cible GitHub sera ajustée si nécessaire.

## Périmètre des PRs

### Phase 0 — cadrage

Contient le rapport `docs/migration/wgsl4k-source-baseline.md`, l’alignement du `LICENSE` et la documentation de la stack. Elle ne contient aucun code WGSL.

### Phase 1 — structure Gradle

Ajoute les projets `:wgsl:wgsl-core`, `:wgsl:wgsl-parser`, `:wgsl:wgsl-generator`, `:wgsl:wgsl-tests` et `:wgsl:wgsl-cli`. Les conventions `buildSrc` du template sont généralisées aux modules nécessaires. Le version catalog du template reste la source d’autorité ; seules les dépendances nécessaires sont ajoutées avec leurs versions compatibles.

Cette PR conserve `:shared` et `:docs`, conserve le wrapper du template et doit rendre `./gradlew projects` ainsi que la compilation JVM vérifiables.

### Phase 2 — `wgsl-core`

Importe les sources `commonMain`, les tests et les artefacts d’API du module core : IR, arenas, validation, layout et abstractions backend. Les déclarations `package` sont préservées sous `org.graphiks.wgsl.*`. La configuration est adaptée au template et à ses targets initiales sans modifier le comportement métier.

Gates : `:wgsl:wgsl-core:jvmTest` et `:wgsl:wgsl-core:compileKotlinJvm`.

### Phase 3 — `wgsl-parser`

Importe lexer, tokens, AST, parser, diagnostics, error recovery, résolution de types et lowering vers IR, avec leurs tests et leur documentation. Les dépendances restent orientées vers `wgsl-core`.

Gates : `:wgsl:wgsl-parser:jvmTest` et `:wgsl:wgsl-parser:koverVerifyJvm`.

### Phase 4 — `wgsl-generator`

Importe les writers WGSL, GLSL, HLSL et MSL, leurs factories et les tests unitaires. Les interfaces `BackendWriter` et `BackendRegistry` restent compatibles avec le source.

Gate : `:wgsl:wgsl-generator:jvmTest`.

### Phase 5 — tests golden

Importe le corpus de 160 entrées sous `tests/golden/inputs`, les 160 sorties de chacun des backends WGSL, GLSL, HLSL, MSL et IR, ainsi que les tests du module `wgsl-tests`, `goldenDebug` et les rapports de couverture.

Gates : `:wgsl:wgsl-tests:jvmTest` et `:wgsl:wgsl-tests:goldenCoverageReport`, avec comparaison au comportement de la baseline source.

### Phase 6 — CLI

Importe la commande de conversion, ses tests et un smoke test couvrant lecture fichier, stdout, sortie fichier et erreurs de parsing. La CLI dépend des trois modules fonctionnels déjà intégrés.

Gate : `:wgsl:wgsl-cli:jvmTest`.

### Phase 7 — documentation, CI et publication

Remplace la description starter pack du `README.md`, ajoute les exemples parser/generators, adapte Dokka/MkDocs, les workflows et les métadonnées SCM/Maven. Aucun artifact ne sera publié avant validation de l’ABI (interface binaire) et des métadonnées.

### Phase 8 — nettoyage et extension

Supprime `:shared` et les conventions devenues inutiles après validation de tous les modules WGSL. Réactive progressivement JS, Wasm, Linux, macOS, Windows, watchOS et Android Native, en traitant les targets dépréciées explicitement. Termine par le changelog et la documentation de migration.

## Workflow de validation

Pour chaque PR :

1. vérifier le delta avec `git diff` par rapport à la base immédiate ;
2. exécuter les gates de la phase et `./gradlew :shared:jvmTest` tant que `:shared` existe ;
3. appliquer `git diff --check` et vérifier les fichiers autorisés ;
4. ouvrir une PR avec les sections exactes du template (`Description`, `Type of Change`, `Checklist`, `Screenshots (if applicable)`, `Additional Notes`) ;
5. lancer une revue indépendante et corriger les remarques importantes avant de continuer la stack ;
6. mettre à jour l’issue avec le lien de la PR, sans cocher prématurément les critères ;
7. fusionner dans l’ordre parent → enfant avec squash, puis rebaser la PR suivante.

## Critères de réussite

La stack est complète lorsque les neuf PRs ont été fusionnées dans l’ordre, que les cinq modules sont intégrés, que les tests JVM et golden passent, que la CI du template est verte, que les fichiers d’API ont été régénérés sous `org.graphiks.wgsl.*`, que `:shared` a été retiré proprement et que les targets supplémentaires ont été traitées.

Les warnings déjà observés dans le source (Dokka, version Kotlin embarquée par Gradle, targets Native dépréciées et accès JNA restreint) sont suivis séparément et ne bloquent pas la phase 0.
