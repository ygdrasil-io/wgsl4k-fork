plugins {
    id("ygdrasil.conventions.wgsl-library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {}
        commonTest.dependencies {}
        jvmTest.dependencies {}
    }
}
