import com.android.build.api.dsl.ApplicationExtension
import com.kyawlinnthant.convention.AppConfig
import com.kyawlinnthant.convention.root.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {

        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val app = libs.findPlugin("android-application").get().get().pluginId
            val kotlin = libs.findPlugin("jetbrains-kotlin-android").get().get().pluginId
            val serialization = libs.findPlugin("kotlin-serialization").get().get().pluginId
            with(pluginManager) {
                apply(app)
                apply(kotlin)
                apply(serialization)
            }
            extensions.configure<ApplicationExtension> {
                configureKotlin(this)
                defaultConfig.targetSdk = AppConfig.TARGET_SDK
            }
            val cores = libs.findBundle("androidx-core").get()
            val ktlintRule = libs.findLibrary("rule-ktlint").get()
            val detektRule = libs.findLibrary("rule-detekt").get()
            dependencies {
                add("implementation",cores)
                add("ktlint", ktlintRule)
                add("detektPlugins", detektRule)
            }
        }
    }
}
