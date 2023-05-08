pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
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
rootProject.name = "cloudFarm"
include(":app")


include(":core:common")
include(":core:data")
include(":core:datastore")
include(":core:design-system")
include(":core:model")
include(":core:network")
include(":core:ui")
include(":core:component-base")

include(":feature")
include(":feature:home")
include(":feature:all-articles")
include(":feature:article-detail")
include(":feature:product-detail")
include(":feature:address-management")
include(":feature:collection-product")
include(":feature:modifier-userinfo")
include(":feature:login")
include(":feature:order-management")
include(":feature:reward-points")
include(":feature:store-detail")
include(":feature:success-cases")