plugins {
    kotlin("multiplatform") version "1.6.10"
    id("com.google.devtools.ksp") version "1.6.10-1.0.2"
}

repositories {
    mavenCentral()
}

val fritz2Version = "0.14"

kotlin {
    jvm ()
    js(IR) {
        browser()
    }.binaries.executable()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("dev.fritz2:core:$fritz2Version")
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

dependencies {
    add("kspMetadata", "dev.fritz2:lenses-annotation-processor:$fritz2Version")
    //add("kspJs", "dev.fritz2:lenses-annotation-processor:$fritz2Version")
}

// Generate common code with ksp instead of per-platform, hopefully this won't be needed in the future.
// https://github.com/google/ksp/issues/567
kotlin.sourceSets.commonMain {
    kotlin.srcDir("build/generated/ksp/commonMain/kotlin")
    //kotlin.srcDir("build/generated/ksp/jsMain/kotlin")
}
tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().all {
    if (name != "kspKotlinMetadata") {
        dependsOn("kspKotlinMetadata")
    }

    // Does not work -> circular dependencies -> but also stand alone does not work eiter -> Lense in App.kt gets not recognized
    /*
    if (name != "kspKotlinJs") {
        dependsOn("kspKotlinJs")
    }
     */
}