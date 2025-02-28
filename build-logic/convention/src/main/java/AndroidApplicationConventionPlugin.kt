import com.android.build.api.dsl.ApplicationExtension
import com.jb.build_logic.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            pluginManager.apply("org.jetbrains.kotlin.android")
            pluginManager.apply("kotlin-parcelize")

            val androidExtension = extensions.getByType(ApplicationExtension::class.java)
            configureKotlinAndroid(androidExtension)
        }
    }
}