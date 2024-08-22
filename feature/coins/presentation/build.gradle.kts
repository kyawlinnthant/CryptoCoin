plugins {
    alias(libs.plugins.kyawlinnthant.android.library)
    alias(libs.plugins.kyawlinnthant.hilt)
    alias(libs.plugins.kyawlinnthant.compose.library)
}
android {
    namespace = "com.kyawlinnthant.coins.presentation"
}
dependencies {
    implementation(projects.feature.coins.domain)
    implementation(projects.core.util)
    implementation(projects.core.theme)
    implementation(libs.compose.paging)
}
