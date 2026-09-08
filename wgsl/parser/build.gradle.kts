plugins {
    id("ygdrasil.conventions.wgsl-library")
    alias(libs.plugins.kover)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":wgsl:wgsl-core"))
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

kover {
    reports {
        variant("jvm") {
            verify {
                rule("WGSL parser JVM line coverage") {
                    minBound(65)
                }
            }
        }
    }
}

tasks.named<org.gradle.api.tasks.testing.Test>("jvmTest") {
    useJUnitPlatform()
    filter {
        failOnNoDiscoveredTests = false
    }
    reports {
        junitXml.required.set(true)
        html.required.set(true)
    }
    testLogging {
        showExceptions = true
        showStandardStreams = true
        events = setOf(
            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
        )
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

tasks.named("check") {
    dependsOn("koverVerifyJvm")
}
