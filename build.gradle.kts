plugins {
    kotlin("multiplatform") version "1.7.0"
    // KSP support
    id("com.google.devtools.ksp") version "1.7.0-1.0.6"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/") // new repository here
}

val fritz2Version = "1.0-SNAPSHOT"

//group = "my.fritz2.app"
//version = "0.0.1-SNAPSHOT"

kotlin {
    jvm()
    js(IR) {
        browser()
    }.binaries.executable()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("dev.fritz2:core:$fritz2Version")
                // implementation("dev.fritz2:headless:$fritz2Version") // optional
            }
        }
        val jvmMain by getting {
            dependencies {
            }
        }
        val jsMain by getting {
            dependencies {
            }
        }
    }
}

/**
 * KSP support - start
 */
dependencies {
    add("kspCommonMainMetadata", "dev.fritz2:lenses-annotation-processor:$fritz2Version")
}
kotlin.sourceSets.commonMain { kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin") }

// Fixes webpack-cli incompatibility by pinning the newest version.
// https://youtrack.jetbrains.com/issue/KTIJ-22030
rootProject.extensions.configure<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension> {
    versions.webpackCli.version = "4.10.0"
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().all {
    if (name != "kspCommonMainKotlinMetadata") dependsOn("kspCommonMainKotlinMetadata")
}
/**
 * KSP support - end
 */

