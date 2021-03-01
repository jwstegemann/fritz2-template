plugins {
    id("dev.fritz2.fritz2-gradle") version "0.9-SNAPSHOT"
}

repositories {
    jcenter()
    maven("https://dl.bintray.com/jwstegemann/fritz2")
    maven("https://oss.jfrog.org/artifactory/jfrog-dependencies")
}

kotlin {
    kotlin {
        jvm()
        js().browser() // working
        // not working
//        js(IR) {
//            browser()
//        }.binaries.executable()

        sourceSets {
            val commonMain by getting {
                dependencies {
                    implementation("dev.fritz2:core:0.9-SNAPSHOT")
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
}