plugins {
    kotlin("jvm") version "2.0.20"
    id("application")
}

group = "com.lucaspowered"
version = "1.0-SNAPSHOT"

application {
    mainClass = "com.lucaspowered.Day1Kt"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}