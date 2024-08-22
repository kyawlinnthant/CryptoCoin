plugins {
    alias(libs.plugins.kyawlinnthant.android.library)
    alias(libs.plugins.junit5)
}
android {
    namespace = "com.kyawlinnthant.util"
}
dependencies {
    implementation(libs.jupiter.api)
    implementation(libs.jupiter.param)
    testRuntimeOnly(libs.jupiter.engine)
    implementation(libs.assertk)
    testImplementation(libs.coroutines.test)
}
