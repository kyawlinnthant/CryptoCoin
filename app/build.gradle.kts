plugins {
    alias(libs.plugins.kyawlinnthant.android.application)
    alias(libs.plugins.kyawlinnthant.compose.application)
    alias(libs.plugins.kyawlinnthant.hilt)
}

android {
    namespace = "com.kyawlinnthant.crypto"

    defaultConfig {
        applicationId = "com.kyawlinnthant.crypto"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z ( Major.Minor.Patch)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    implementation(projects.core.theme)
    implementation(projects.feature.coins.presentation)
}
