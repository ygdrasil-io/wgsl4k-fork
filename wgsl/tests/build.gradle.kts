plugins {
    id("ygdrasil.conventions.wgsl-library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":wgsl:wgsl-core"))
            implementation(project(":wgsl:wgsl-parser"))
            implementation(project(":wgsl:wgsl-generator"))
        }
        commonTest.dependencies {}
        jvmTest.dependencies {}
    }
}
