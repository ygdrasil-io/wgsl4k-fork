@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

package org.graphiks.wgsl.conventions

import com.android.build.api.variant.KotlinMultiplatformAndroidComponentsExtension
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation
import org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeTest

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.kotlin.multiplatform.library")
}

kotlin {
    @OptIn(ExperimentalAbiValidation::class)
    abiValidation()

    jvmToolchain(25)

    android {}

    jvm()

    iosArm64()
    iosX64()
    iosSimulatorArm64()

    // JS, WasmJs, watchOS, macOS, Linux, MinGW, and Android Native require
    // PlatformFile actuals for wgsl-cli; retain only targets validated by CI.

    applyDefaultHierarchyTemplate()
}

extensions.configure<KotlinMultiplatformAndroidComponentsExtension> {
    finalizeDsl(
        org.gradle.api.Action<com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryExtension> {
            namespace = "org.graphiks.wgsl.${project.name.removePrefix("wgsl-").replace('-', '.')}"
            compileSdk = 37
            minSdk = 24
        }
    )
}

tasks.withType<KotlinNativeTest>().configureEach {
    val requireNativeTests = providers.gradleProperty("requireNativeTests")
        .map(String::toBoolean)
        .orElse(false)
        .get()

    // Common Kotest sources compile for Native, but this repository currently
    // has no discoverable Native runner. Default CI reports those runtime
    // tasks as skipped; strict callers can require discovery explicitly.
    failOnNoDiscoveredTests = requireNativeTests
    if (!requireNativeTests) {
        doFirst {
            logger.lifecycle(
                "SKIPPED Native runtime tests for $path: no discoverable runner; " +
                    "Native compilation is still checked. Use -PrequireNativeTests=true to require one."
            )
        }
    }
}

// Keep generated ABI text stable with the repository whitespace policy. The
// ABI tool emits an extra blank line at EOF, which is not semantically part of
// the ABI but fails the cumulative `git diff --check` gate.
tasks.matching { it.name == "internalDumpKotlinAbi" }.configureEach {
    doLast {
        layout.buildDirectory.dir("kotlin/abi").get().asFile
            .walkTopDown()
            .filter { it.isFile && it.extension == "api" }
            .forEach { file ->
                val contents = file.readText()
                val normalized = normalizeAbiText(contents)
                if (contents != normalized) {
                    file.writeText(normalized)
                }
            }
    }
}
