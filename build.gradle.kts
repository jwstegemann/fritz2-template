buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
    }

    dependencies {
        classpath(kotlin("gradle-plugin"))
    }
}

plugins {
    kotlin("js") version("1.3.70")
}

kotlin {
    target {
        browser {
        }
    }
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-js"))
    implementation("io.fritz2:fritz2-core-js:0.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.4")
}

