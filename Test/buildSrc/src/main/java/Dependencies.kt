object ApplicationId {
    const val id = "com.btplinh.movies"
}

object AndroidVersion {
    const val gradle = "4.0.2"
    const val android = "7.1.2"
    const val compileSdk = 32
    const val minSdk = 26
}

object Module {
    val domain = mapOf("path" to ":domain")
    val data = mapOf("path" to ":data")
}

object Realse {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val multidex = "2.0.1"
    const val appCompat = "1.4.1"
    const val coreKtx = "1.7.0"
    const val androidTestRunner = "1.4.0"
    const val androidJunit = "1.1.3"
    const val espressoCore = "3.4.0"
    const val constraintLayout = "2.1.3"
    const val materialDesignVersion = "1.5.0"
    const val nav = "2.4.2"
    const val fragment = "1.4.1"
    const val kotlinCoroutine = "1.6.1"
    const val lifecycle = "2.4.1"
    const val lifecycleExtensions = "2.0.0-alpha1"
    const val okHttp = "4.9.3"
    const val retrofit = "2.9.0"
    const val retrofitMoshi = "2.9.0"
    const val moshiVersion = "1.13.0"
    const val logginInterceptor = "4.9.3"
    const val daggerHilt = "2.42"
    const val roomVersion = "2.4.0-alpha03"
    const val sdp = "1.1.0"
    const val ssp = "1.1.0"
    const val mockk = "1.12.2"
    const val coreTesting = "2.1.0"
}

object Libraries {
    //RESPONESIVE
    const val sdp = "com.intuit.sdp:sdp-android:${Versions.sdp}"
    const val ssp = "com.intuit.ssp:ssp-android::${Versions.ssp}"

    // RETROFIT
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofitMoshi}"
    const val httpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.logginInterceptor}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"

    const val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    const val daggerCompiler = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"

    //ROOM
    const val room = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
}

object AndroidLibraries {
    // ANDROID
    const val multidex = "androidx.multidex:multidex:${Versions.multidex}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val navigation = "androidx.navigation:navigation-ui-ktx:${Versions.nav}"
    const val navigationFrag = "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val fragment = "androidx.fragment:fragment:${Versions.fragment}"

    const val materialDesign = "com.google.android.material:material:${Versions.materialDesignVersion}"

    // ViewModel and LiveData
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
    const val lifecycleCompile = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
}

object KotlinLibraries {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val coreKotlinCoroutine =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutine}"
    const val androidKotlinCoroutine =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutine}"

    // MOSHI
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshiVersion}"
    const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshiVersion}"
}

object TestLibraries {
    // ANDROID TEST
    const val androidTestRunner = "androidx.test:runner:${Versions.androidTestRunner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espressoCore}"
    const val junit = "androidx.test.ext:junit:${Versions.androidJunit}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"

}
