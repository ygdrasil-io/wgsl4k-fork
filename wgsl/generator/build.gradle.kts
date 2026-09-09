plugins {
    id("ygdrasil.conventions.kmp-library")
    id("ygdrasil.conventions.kmp-publish")
    id("ygdrasil.conventions.kmp-dokka")
    id("dev.opensavvy.dokka-mkdocs") version "0.6.3"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":wgsl:wgsl-core"))
            implementation(project(":wgsl:wgsl-parser"))
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.bundles.kotest)
        }
        jvmTest.dependencies {
            implementation(libs.kotest.runner.junit5)
            implementation(kotlin("reflect"))
            implementation(libs.logback.classic)
        }
    }
}

tasks.named<org.gradle.api.tasks.testing.Test>("jvmTest") {
    useJUnitPlatform()
}

tasks.named("internalDumpKotlinAbi") {
    doLast {
        val abiFile = layout.buildDirectory.file("kotlin/abi/jvm/wgsl-generator.api").get().asFile
        val content = abiFile.readText()
        val normalizedContent = content.trimEnd() + "\n"
        if (content != normalizedContent) {
            abiFile.writeText(normalizedContent)
        }
    }
}
