plugins {
    id("ygdrasil.conventions.wgsl-library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":wgsl:wgsl-core"))
        }
        commonTest.dependencies {}
        jvmTest.dependencies {}
    }
}
