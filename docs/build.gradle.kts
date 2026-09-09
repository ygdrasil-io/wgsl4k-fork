import org.gradle.api.tasks.Sync

plugins {
    id("dev.opensavvy.dokka-mkdocs") version "0.6.3"
}

val wgslModules = listOf(
    project(":wgsl:wgsl-core"),
    project(":wgsl:wgsl-parser"),
    project(":wgsl:wgsl-generator"),
    project(":wgsl:wgsl-tests"),
    project(":wgsl:wgsl-cli"),
)

tasks.named<Sync>("dokkaCopyIntoMkDocs") {
    into(layout.buildDirectory.dir("dokka-mkdocs"))
}

val copyWgslDokkaIntoMkDocs = tasks.register<Sync>("copyWgslDokkaIntoMkDocs") {
    dependsOn(wgslModules.map { module ->
        module.tasks.named("dokkaGenerateModuleMkdocs")
    })

    wgslModules.forEach { module ->
        from(module.layout.buildDirectory.dir("dokka-module/mkdocs/module")) {
            into("api/${module.name}")
        }
    }
    into(layout.projectDirectory.dir("docs"))
}

tasks.named("generateMkDocsNavigation") {
    dependsOn(copyWgslDokkaIntoMkDocs)
}
