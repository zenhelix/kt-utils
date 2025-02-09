plugins {
    id("io.github.zenhelix.kotlin-jvm-library") apply false
    id("io.github.zenhelix.maven-central-publish") apply false
}

allprojects {
    group = "io.github.zenhelix"
}

subprojects {
    apply(plugin = "io.github.zenhelix.maven-central-publish")
    apply(plugin = "io.github.zenhelix.kotlin-jvm-library")
}
