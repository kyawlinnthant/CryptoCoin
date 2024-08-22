import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class DaggerHiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val hilt = libs.findPlugin("google-hilt").get().get().pluginId
            val ksp = libs.findPlugin("google-ksp").get().get().pluginId
            with(pluginManager) {
                apply(hilt)
                apply(ksp)
            }
            val hiltAndroid = libs.findLibrary("google-hilt-android").get()
            val hiltCompiler = libs.findLibrary("google-hilt-compiler").get()
            dependencies {
                add("implementation", hiltAndroid)
                add("ksp", hiltCompiler)
            }
        }
    }
}