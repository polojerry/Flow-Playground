plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

android {
    defaultConfig {
        applicationId = "com.samples.flow"

        compileSdk = AndroidSdk.compileSdkVersion
        minSdk = AndroidSdk.minSdkVersion
        targetSdk = AndroidSdk.targetSdkVersion

        versionCode = AndroidSdk.versionCode
        versionName = AndroidSdk.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //Kotlin
    implementation(Libraries.kotlinStandardLibrary)
    implementation(Libraries.ktxCore)

    //UI
    implementation(Libraries.appCompat)
    implementation(Libraries.materialComponents)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.fragment)
    implementation(Libraries.legacySupport)

    //Logging: Timber
    implementation(Libraries.timber)

    //Hilt
    implementation(Libraries.hilt)
    kapt(Libraries.hiltKapt)

    //LifeCycle
    implementation(Libraries.legacySupport)
    implementation(Libraries.liveDataKtx)
    implementation(Libraries.viewModelKtx)
    implementation(Libraries.lifecycleRuntime)

    //Navigation
    implementation(Libraries.navigationFragmentKtx)
    implementation(Libraries.navigationUiKtx)

    //Retrofit
    implementation(Libraries.retrofit)
    implementation(Libraries.moshi)
    implementation(Libraries.loggingInterceptor)

    //Room
    implementation(Libraries.roomKtx)
    implementation(Libraries.roomRuntime)
    kapt(Libraries.roomKapt)

    //Firebase
    implementation(platform(Libraries.firebaseBom))
    implementation(Libraries.firebaseFirestoreKtx)
    implementation(Libraries.firebaseAnalyticsKtx)

    //ImageLoading: Coil
    implementation(Libraries.coil)

    //TEST
    testImplementation(TestLibraries.junit4)
    testImplementation(TestLibraries.testRunner)
    testImplementation(TestLibraries.core)
    testImplementation(TestLibraries.extJunit)

    testImplementation(TestLibraries.truth)
    testImplementation(TestLibraries.mockWebServer)
    testImplementation(TestLibraries.roboelectric)

    androidTestImplementation(TestLibraries.extJunit)
    androidTestImplementation(TestLibraries.espresso)
}