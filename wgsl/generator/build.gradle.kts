plugins {
    id("ygdrasil.conventions.wgsl-library")
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
