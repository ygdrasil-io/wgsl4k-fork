allprojects {
    group = "org.graphiks"
}

val releaseVersion = providers.gradleProperty("releaseVersion")
    .orElse(providers.gradleProperty("VERSION"))
    .map { it.trim().ifEmpty { "1.0.0-SNAPSHOT" } }
    .orElse("1.0.0-SNAPSHOT")

version = releaseVersion.get()

subprojects {
    version = releaseVersion.get()
}
