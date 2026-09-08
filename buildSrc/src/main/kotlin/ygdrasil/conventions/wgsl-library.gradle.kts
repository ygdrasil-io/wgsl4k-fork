@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

package ygdrasil.conventions

import com.android.build.api.variant.KotlinMultiplatformAndroidComponentsExtension
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation

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
    iosSimulatorArm64()

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
