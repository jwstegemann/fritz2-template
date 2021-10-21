plugins {
    id("dev.fritz2.fritz2-gradle") version "0.13"
}

repositories {
    mavenCentral()
}

kotlin {
    jvm()
    js(IR) {
        browser()
    }.binaries.executable()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("dev.fritz2:core:0.13")
                // see https://components.fritz2.dev/
                // implementation("dev.fritz2:components:0.13")
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