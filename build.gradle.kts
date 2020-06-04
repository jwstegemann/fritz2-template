buildscript {
    repositories {
        mavenLocal() //FIXME: nicht einchecken
        jcenter()
    }

    dependencies {
        classpath(kotlin("gradle-plugin"))
        classpath("io.fritz2:fritz2-gradle-plugin:0.5")
    }
}

plugins {
    kotlin("multiplatform") version("1.3.72")
    kotlin("kapt") version("1.3.72")
}

apply(plugin = "io.fritz2.fritz2-gradle")

repositories {
    mavenLocal()
    jcenter()
}

kapt {
    correctErrorTypes = true
    this.includeCompileClasspath = true
    this.generateStubs = true
}

kotlin {
    kotlin {
        jvm()
        js().browser()

        sourceSets {
            val commonMain by getting {
                dependencies {
                    implementation(kotlin("stdlib"))
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

