package ygdrasil.conventions

import java.net.URI

plugins {
    id("org.jetbrains.dokka")
}

val repositoryUrl = "https://github.com/ygdrasil-io/wgsl4k-fork"
val projectPath = project.projectDir.relativeTo(project.rootDir).invariantSeparatorsPath

dokka {
    moduleName.set(project.name)
    dokkaSourceSets.configureEach {
        sourceLink {
            localDirectory.set(project.file("src/commonMain/kotlin"))
            remoteUrl.set(URI("$repositoryUrl/blob/master/$projectPath/src/commonMain/kotlin"))
            remoteLineSuffix.set("#L")
        }
    }
}
