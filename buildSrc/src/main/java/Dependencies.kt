object Versions {
    const val kotlin = "1.7.10"
    const val ktx = "1.8.0"
    const val kotlinCoroutines = "1.6.3"
    const val lifecycle = "2.5.0"
    const val androidAppCompat = "1.4.2"
    const val androidMaterial = "1.6.1"
    const val androidConstraint = "2.1.4"
    const val jUnit = "4.13.2"
    const val androidExtJUnit = "1.1.3"
    const val androidEspresso = "3.4.0"
    const val androidNavigation = "2.5.0"
    const val koin = "3.2.0"
    const val room = "2.4.2"
    const val timber = "5.0.1"
    const val androidApplication = "7.2.1"
    const val androidLibrary = "7.2.1"
    const val kotlinMetadataJVM = "0.5.0"
    const val mockk = "1.12.4"
    const val androidCoreTesting = "2.1.0"
    const val androidSwipeRefresh = "1.1.0"
    const val assertk = "0.25"
    const val androidTestCore = "1.4.0"
}

object Libs {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:1.4.3"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
    const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutines}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val androidAppCompat = "androidx.appcompat:appcompat:${Versions.androidAppCompat}"
    const val androidMaterial = "com.google.android.material:material:${Versions.androidMaterial}"
    const val androidConstraint = "androidx.constraintlayout:constraintlayout:${Versions.androidConstraint}"
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val androidExtJUnit = "androidx.test.ext:junit:${Versions.androidExtJUnit}"
    const val androidExtJUnitKtx = "androidx.test.ext:junit-ktx:${Versions.androidExtJUnit}"
    const val androidEspresso = "androidx.test.espresso:espresso-core:${Versions.androidEspresso}"
    const val androidNavigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.androidNavigation}"
    const val androidNavigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.androidNavigation}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinTest = "io.insert-koin:koin-test:${Versions.koin}"
    const val koinTestJunit = "io.insert-koin:koin-test-junit4:${Versions.koin}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val kotlinMetadataJVM = "org.jetbrains.kotlinx:kotlinx-metadata-jvm:${Versions.kotlinMetadataJVM}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    const val androidCoreTesting = "androidx.arch.core:core-testing:${Versions.androidCoreTesting}"
    const val androidSwipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.androidSwipeRefresh}"
    const val assertk = "com.willowtreeapps.assertk:assertk-jvm:${Versions.assertk}"
    const val androidTestCore = "androidx.test:core-ktx:${Versions.androidTestCore}"
}

object Plugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val jetbrainsKotlinAndroid = "org.jetbrains.kotlin.android"
    const val kotlinKapt = "kotlin-kapt"
    const val navigationSafeArgs = "androidx.navigation.safeargs.kotlin"
}

object Configs {
    const val applicationId = "com.tmd.mybudget"
    const val compileSdk = 32
    const val minSdk = 21
    const val targetSdk = 32
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val jvmTarget = "1.8"
}