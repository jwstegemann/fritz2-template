import com.google.devtools.ksp.gradle.KspTaskMetadata

plugins {
    //kotlin("multiplatform") version "1.9.22"
    alias(libs.plugins.kotlin.multiplatform)
    // KSP support
    //id("com.google.devtools.ksp") version "1.9.22-1.0.16"
    alias(libs.plugins.google.ksp)
}

repositories {
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/") // new repository here
}

//group = "my.fritz2.app"
//version = "0.0.1-SNAPSHOT"

kotlin {
    jvm()
    js(IR) {
        browser()
    }.binaries.executable()

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.fritz2.core)
                // implementation(libs.fritz2.headless) // optional
            }
        }
        jvmMain {
            dependencies {
            }
        }
        jsMain {
            dependencies {
            }
        }
    }
}

// KSP support for Lens generation
dependencies.kspCommonMainMetadata(libs.fritz2.lenses)
kotlin.sourceSets.commonMain { tasks.withType<KspTaskMetadata> { kotlin.srcDir(destinationDirectory) } }
