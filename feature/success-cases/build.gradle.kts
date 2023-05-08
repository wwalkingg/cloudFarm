plugins {
    id("convention.android.feature")
    id("convention.android.library.compose")
}

android {
    namespace = "com.example.android.feature.success_cases"
}

dependencies {
    implementation(libs.androidx.activity.compose)
}