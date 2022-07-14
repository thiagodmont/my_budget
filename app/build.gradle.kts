plugins {
    id(Plugins.androidApplication)
    id(Plugins.jetbrainsKotlinAndroid)
    id(Plugins.navigationSafeArgs)
    id(Plugins.kotlinKapt)
}

android {
    compileSdk = Configs.compileSdk

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk

        applicationId = Configs.applicationId
        versionCode = Configs.versionCode
        versionName = Configs.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    
    kotlinOptions {
        jvmTarget = Configs.jvmTarget
    }
}

dependencies {
    // Core
    implementation(Libs.kotlinStdLib)
    implementation(Libs.ktxCore)

    // Android Base
    implementation(Libs.androidAppCompat)
    implementation(Libs.androidMaterial)
    implementation(Libs.androidConstraint)
    implementation(Libs.androidSwipeRefresh)

    // Navigation
    implementation(Libs.androidNavigationFragment)
    implementation(Libs.androidNavigationUi)

    // Jetpack LifeCycle
    implementation(Libs.lifecycleRuntime)
    implementation(Libs.lifecycleCommon)
    implementation(Libs.lifecycleViewModel)
    implementation(Libs.lifecycleLiveData)

    // Koin
    implementation(Libs.koinAndroid)

    // Coroutine
    implementation(Libs.coroutines)
    implementation(Libs.coroutinesAndroid)

    // Room
    implementation(Libs.roomRuntime)
    implementation(Libs.androidTestCore)
    kapt(Libs.roomCompiler)
    kapt(Libs.kotlinMetadataJVM)
    implementation(Libs.roomKtx)

    // Log
    implementation(Libs.timber)

    testImplementation(Libs.jUnit)
    testImplementation(Libs.coroutineTest)
    testImplementation(Libs.mockk)
    testImplementation(Libs.androidCoreTesting)
    testImplementation(Libs.assertk)
    testImplementation(Libs.koinTest)
    testImplementation(Libs.koinTestJunit)

    androidTestImplementation(Libs.androidExtJUnit)
    androidTestImplementation(Libs.androidExtJUnitKtx)
    androidTestImplementation(Libs.androidEspresso)
    androidTestImplementation(Libs.mockkAndroid)
}