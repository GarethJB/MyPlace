import com.android.build.api.dsl.LibraryExtension
import com.jb.build_logic.VersionConstants
import com.jb.build_logic.configureBuildFeatures
import com.jb.build_logic.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                configureBuildFeatures()
                lint.targetSdk = VersionConstants.TARGET_SDK
            }
        }
    }
}