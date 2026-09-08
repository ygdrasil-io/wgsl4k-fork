@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

package ygdrasil.conventions

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
    // Kotest powers the common tests and is executed on JVM; Native compiles
    // those sources but has no discoverable Kotlin test runner in this setup.
    failOnNoDiscoveredTests = false
}
