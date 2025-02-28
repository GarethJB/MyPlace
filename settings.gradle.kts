pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "MyPlace"
include(":app")
include(":data:data-api")
include(":data:data-model")
include(":data:data-store")
include(":presentation:presentation-model")
include(":presentation:presentation-util")
include(":presentation:presentation-designsystem")
include(":domain:domain-api")
include(":domain:domain-model")
