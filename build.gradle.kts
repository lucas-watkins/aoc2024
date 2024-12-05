plugins {
    kotlin("jvm") version "2.0.20"
    id("application")
}

group = "com.lucaspowered"
version = "1.0-SNAPSHOT"

val day = 5
application {
    mainClass = "com.lucaspowered.aoc2024.Day${day}Kt"
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