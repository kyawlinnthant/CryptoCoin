import com.android.build.api.dsl.ApplicationExtension
import com.kyawlinnthant.convention.root.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class ComposeApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val app = libs.findPlugin("android-application").get().get().pluginId
            val compose = libs.findPlugin("jetbrains-kotlin-compose").get().get().pluginId
            with(pluginManager) {
                apply(app)
                apply(compose)
            }
            extensions.configure<ApplicationExtension> {
                configureCompose(this)
            }
            val composeBundle = libs.findBundle("androidx-compose").get()
            val composeDebugBundle = libs.findBundle("androidx-compose-debug").get()
            dependencies {
                add("implementation", composeBundle)
                add("debugImplementation", composeDebugBundle)
            }
        }
    }
}
