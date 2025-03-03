plugins {
    alias(libs.plugins.compose.compiler)
    id("myplace.android.library")
    id("myplace.android.library.compose")
    id("myplace.android.hilt")
}

android {
    namespace = "com.jb.data_model"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.material3)
}