package com.jb.build_logic

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        compileSdk = VersionConstants.TARGET_SDK

        defaultConfig {
            minSdk = VersionConstants.MIN_SDK

            ndk {
                abiFilters.addAll(
                    setOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
                )
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        extensions.findByType(KotlinAndroidProjectExtension::class.java)?.let { kotlinExtension ->
            kotlinExtension.compilerOptions {
                val warningsAsErrors: String? by project
                allWarningsAsErrors.set(warningsAsErrors?.toBoolean() ?: false)

                freeCompilerArgs.addAll(
                    listOf(
                        "-opt-in=kotlin.RequiresOptIn",
                        // Enable experimental coroutines APIs, including Flow
                        "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                        "-opt-in=kotlinx.coroutines.FlowPreview",
                        "-opt-in=kotlin.Experimental",
                        "-Xcontext-receivers"
                    )
                )
                jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
            }
        } ?: run {
            logger.warn("KotlinAndroidProjectExtension not found; compilerOptions configuration skipped.")
        }
    }
}

internal fun ApplicationExtension.configureBuildFeatures() {
    buildFeatures {
        buildConfig = true
    }
}

internal fun LibraryExtension.configureBuildFeatures() {
    buildFeatures {
        buildConfig = true
    }
}

internal fun Project.configureSourceSet() {
    val androidExtension = extensions.getByName("android") as? BaseExtension
    androidExtension?.sourceSets?.configureEach {
        kotlin.srcDirs("build/generated/ksp/$name/kotlin")
    }
}