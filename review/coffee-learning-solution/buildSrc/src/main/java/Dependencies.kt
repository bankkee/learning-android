object App {
    const val id = "com.learning.coffeeapp"
    const val versionCode = 1
    const val versionName = "1.0.0"

    const val compileSdk = 36
    const val minSdk = 29
    const val targetSdk = 36
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val networks = ":networks"
    const val apilayer = ":apilayer"
    const val coffee = ":coffee"
}

object Versions {
    const val gradle = "8.6.0"
    const val kotlin = "1.9.22"

    const val appCompat = "1.7.1"
    const val constraintLayout = "2.2.1"
    const val coreTest = "2.1.0"
    const val extjunit = "1.2.1"
    const val espressoCore = "3.6.1"
    const val gson = "2.8.6"
    const val junit = "4.13.2"
    const val koin = "2.2.3"
    const val kotlinTest = "1.8.22"
    const val kotlinxCoroutines = "1.7.3"
    const val ktxCore = "1.13.0"
    const val lifecycleExtension = "2.4.0"
    const val materialDesign = "1.2.1"
    const val mockito = "4.2.0"
    const val mockitoKotlin = "2.1.0"
    const val mockitoInline = "4.2.0"
    const val okhttp = "4.12.0"
    const val recyclerView = "1.3.2"
    const val retrofit = "2.9.0"
}

object Libraries {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktxCore}"

    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleExtension}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleExtension}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleExtension}"

    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    const val kotlinxCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinxCoroutines}"

    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinScope = "io.insert-koin:koin-androidx-scope:${Versions.koin}"
    const val koinViewModel = "io.insert-koin:koin-androidx-viewmodel:${Versions.koin}"
}

object SupportLibraries {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val design = "com.google.android.material:material:${Versions.materialDesign}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
}

object TestLibraries {
    const val junit = "junit:junit:${Versions.junit}"
    const val extjunit = "androidx.test.ext:junit:${Versions.extjunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinTest}"
    const val kotlinxCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinxCoroutines}"
    const val coreTest = "androidx.arch.core:core-testing:${Versions.coreTest}"
}
