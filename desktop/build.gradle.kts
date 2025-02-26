plugins {
    kotlin("multiplatform")
}

group = "com.linuxcommandlibrary"
version = "1.0"

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.8.1")
                implementation("org.json:json:20230227")
            }
        }
        val jvmTest by getting
    }
}

