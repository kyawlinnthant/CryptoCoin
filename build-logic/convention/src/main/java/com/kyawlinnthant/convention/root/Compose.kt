package com.kyawlinnthant.convention.root


import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureCompose(
    extension: CommonExtension<*, *, *, *, *, *>
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    val composeBom = libs.findLibrary("androidx-compose-bom").get()
    extension.apply {
        buildFeatures {
            compose = true
        }
        defaultConfig {
            vectorDrawables {
                useSupportLibrary = true
            }
        }
        dependencies {
            add("implementation", platform(composeBom))
            add("androidTestImplementation", platform(composeBom))
        }
    }
}