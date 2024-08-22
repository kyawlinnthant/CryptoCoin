import com.android.build.gradle.LibraryExtension
import com.kyawlinnthant.convention.AppConfig
import com.kyawlinnthant.convention.root.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val library = libs.findPlugin("android-library").get().get().pluginId
            val kotlin = libs.findPlugin("jetbrains-kotlin-android").get().get().pluginId
            val serialization = libs.findPlugin("kotlin-serialization").get().get().pluginId
            with(pluginManager) {
                apply(library)
                apply(kotlin)
                apply(serialization)
            }
            extensions.configure<LibraryExtension> {
                configureKotlin(this)
                defaultConfig.targetSdk = AppConfig.TARGET_SDK
            }
            val cores = libs.findBundle("androidx-core").get()
            dependencies {
                add("implementation",cores)
            }
        }
    }
}
