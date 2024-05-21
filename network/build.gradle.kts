plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.com.google.devtools.ksp)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.dagger.hilt.android)
}

android {
    namespace = "com.lucienguimaraes.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

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

    /*** DI Libraries ***/
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.android.compiler)

    /*** API Libraries ***/
    api(platform(libs.retrofit.bom))
    api(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.okhttp3.logging.interceptor)
    api(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)
}
