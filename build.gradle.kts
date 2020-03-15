import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

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

//TODO: add DCE and closure-compiler
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
    implementation("io.fritz2:fritz2-core-js:0.1")
}

