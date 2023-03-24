plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id ("kotlin-parcelize")
}

android {
    namespace = "com.btplinh.domain"
    compileSdk = AndroidVersion.compileSdk

    defaultConfig {
        minSdk = AndroidVersion.minSdk
        targetSdk = AndroidVersion.compileSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
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
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(AndroidLibraries.lifecycleExtensions)
    kapt(AndroidLibraries.lifecycleCompile)
    implementation(KotlinLibraries.moshiKotlin)
    kapt(KotlinLibraries.moshiKotlinCodegen)

    implementation(Libraries.daggerHilt)
    kapt(Libraries.daggerCompiler)

    implementation(KotlinLibraries.coreKotlinCoroutine)
    kapt(KotlinLibraries.androidKotlinCoroutine)
}