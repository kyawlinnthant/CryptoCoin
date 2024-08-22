plugins {
    `kotlin-dsl`
}
group = "com.kyawlinnthant.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("android.application") {
            id = "com.kyawlinnthant.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }
        register("compose.application") {
            id = "com.kyawlinnthant.compose.application"
            implementationClass = "ComposeApplicationPlugin"
        }
        register("android.library") {
            id = "com.kyawlinnthant.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }
        register("compose.library") {
            id = "com.kyawlinnthant.compose.library"
            implementationClass = "ComposeLibraryPlugin"
        }
        register("dagger.hilt") {
            id = "com.kyawlinnthant.dagger.hilt"
            implementationClass = "DaggerHiltPlugin"
        }
        register("retrofit.network") {
            id = "com.kyawlinnthant.retrofit.network"
            implementationClass = "NetworkPlugin"
        }
    }
}
