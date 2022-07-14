plugins {
    id(Plugins.androidApplication) version Versions.androidApplication apply false
    id(Plugins.androidLibrary) version Versions.androidLibrary apply false
    id(Plugins.jetbrainsKotlinAndroid) version Versions.kotlin apply false
    id(Plugins.navigationSafeArgs) version Versions.androidNavigation apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}