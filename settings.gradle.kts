@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "kt-utils"

private fun Settings.project(
    baseProject: String, initializer: IncludeContext.() -> Unit = {}
): IncludeContext = IncludeContext(baseProject, this).apply(initializer)

private class IncludeContext(private val baseProject: String, private val delegate: Settings) {

    fun project(
        baseProject: String, initializer: IncludeContext.() -> Unit = {}
    ): IncludeContext = IncludeContext("${this.baseProject}:$baseProject", this.delegate).apply(initializer)

    fun include(vararg project: String) {
        project.forEach {
            delegate.include("$baseProject:$it")
        }
    }

}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        mavenLocal()
    }

    val zenhelixGradleVersion: String by settings

    versionCatalogs {
        create("zenhelixPlugins") {
            from("io.github.zenhelix:gradle-magic-wands-catalog:$zenhelixGradleVersion")
        }
    }
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenLocal()
    }

    val zenhelixGradleVersion: String by settings
    val mavenCentralPublishVersion: String by settings

    plugins {
        id("io.github.zenhelix.maven-central-publish") version mavenCentralPublishVersion

        id("io.github.zenhelix.kotlin-jvm-library") version zenhelixGradleVersion
    }
}