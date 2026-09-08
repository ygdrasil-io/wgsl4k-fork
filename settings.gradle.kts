pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "wgsl4k-fork"
include(":shared", ":docs")

listOf("core", "parser", "generator", "tests", "cli").forEach { module ->
    include(":wgsl:$module")
    project(":wgsl:$module").apply {
        name = "wgsl-$module"
        projectDir = file("wgsl/$module")
    }
}
