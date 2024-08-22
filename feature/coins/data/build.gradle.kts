plugins {
    alias(libs.plugins.kyawlinnthant.android.library)
    alias(libs.plugins.kyawlinnthant.hilt)
}
android {
    namespace = "com.kyawlinnthant.coins.data"
}
dependencies {
    implementation(projects.core.util)
    implementation(projects.core.network)
    implementation(projects.core.dispatcher)
    implementation(libs.compose.paging)
}
