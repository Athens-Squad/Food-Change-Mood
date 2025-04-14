plugins {
    kotlin("jvm") version "2.1.10"
}

group = "com.thechance"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.insert-koin:koin-core:4.0.4")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}