plugins {
    alias(libs.plugins.kyawlinnthant.android.library)
    alias(libs.plugins.kyawlinnthant.network)
    alias(libs.plugins.kyawlinnthant.hilt)
    alias(libs.plugins.secrets)
}

android {
    namespace = "com.kyawlinnthant.network"
    buildFeatures {
        buildConfig = true
    }
}
dependencies {
    implementation(projects.core.util)
}
secrets {
    propertiesFileName = "secrets.properties"
    defaultPropertiesFileName = "local.defaults.properties"
    ignoreList.add("keyToIgnore") // Ignore the key "keyToIgnore"
    ignoreList.add("sdk.*") // Ignore all keys matching the regexp "sdk.*"
}
