plugins {
    alias(libs.plugins.kyawlinnthant.android.library)
    alias(libs.plugins.kyawlinnthant.hilt)
}
android {
    namespace = "com.kyawlinnthant.coins.domain"
}
dependencies {
    implementation(projects.core.util)
    implementation(libs.compose.paging)
    implementation(projects.feature.coins.data)
}
