import com.android.build.gradle.LibraryExtension
import com.kyawlinnthant.convention.root.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class ComposeLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val library = libs.findPlugin("android-library").get().get().pluginId
            val compose = libs.findPlugin("jetbrains-kotlin-compose").get().get().pluginId
            with(pluginManager) {
                apply(library)
                apply(compose)
            }
            extensions.configure<LibraryExtension> {
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
