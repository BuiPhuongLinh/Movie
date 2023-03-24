plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.btplinh.data"
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
            buildConfigField("String", "BASE_URL", "\"https://www.omdbapi.com/\"")
            buildConfigField("String", "API_KEY", "\"b9bd48a6\"")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://www.omdbapi.com/\"")
            buildConfigField("String", "API_KEY", "\"b9bd48a6\"")
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

    implementation(project(Module.domain))

    implementation(Libraries.daggerHilt)
    kapt(Libraries.daggerCompiler)

    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitMoshiConverter)
    implementation(Libraries.httpLoggingInterceptor)
    implementation(Libraries.okhttp)

    implementation(KotlinLibraries.moshiKotlin)
    implementation(KotlinLibraries.moshiKotlinCodegen)

    implementation(KotlinLibraries.coreKotlinCoroutine)
    implementation(KotlinLibraries.androidKotlinCoroutine)

}