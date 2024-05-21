plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.com.google.devtools.ksp)
}

dependencies {

    /*** DI Libraries ***/
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    /*** API Libraries ***/
    api(platform(libs.retrofit.bom))
    api(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    api(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)
}
