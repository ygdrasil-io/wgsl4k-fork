package org.graphiks.wgsl.conventions

plugins {
    id("com.vanniktech.maven.publish")
}

val artifactId = project.name
val repositoryUrl = "https://github.com/Graphiks-org/wgsl4k"
val hasPublishingCredentials = project.findProperty("mavenCentralUsername")?.toString()?.isNotBlank() == true
    && project.findProperty("mavenCentralPassword")?.toString()?.isNotBlank() == true
val hasSigningCredentials = project.findProperty("signingInMemoryKey")?.toString()?.isNotBlank() == true
    || project.findProperty("signing.keyId")?.toString()?.isNotBlank() == true

mavenPublishing {
    if (hasPublishingCredentials && hasSigningCredentials) {
        publishToMavenCentral()
        signAllPublications()
    }
    coordinates(group.toString(), artifactId, version.toString())

    pom {
        name.set("wgsl4k $artifactId")
        description.set("Kotlin Multiplatform tooling for WebGPU Shading Language (WGSL)")
        url.set(repositoryUrl)

        licenses {
            license {
                name.set("MIT License")
                url.set("$repositoryUrl/blob/master/LICENSE")
            }
        }

        developers {
            developer {
                id.set("graphiks")
                name.set("graphiks contributors")
            }
        }

        scm {
            connection.set("scm:git:git://github.com/Graphiks-org/wgsl4k.git")
            developerConnection.set("scm:git:ssh://github.com/Graphiks-org/wgsl4k.git")
            url.set(repositoryUrl)
        }
    }
}
