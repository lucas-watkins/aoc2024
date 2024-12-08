plugins {
    kotlin("jvm") version "2.0.20"
    id("application")
}

group = "com.lucaspowered"
version = "1.0-SNAPSHOT"

val day = 7
application {
    mainClass = "com.lucaspowered.aoc2024.Day${day}Kt"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // Because I'm bad at coding lol
    implementation("com.michael-bull.kotlin-itertools:kotlin-itertools:1.0.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}