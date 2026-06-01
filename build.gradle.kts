plugins {
    java
    kotlin("jvm")
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_18
    targetCompatibility = JavaVersion.VERSION_18
}

val junitVersion = "5.2.+"
val mockitoVersion = "2.23.+"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.opencsv:opencsv:5.5.2")
    // HTTP client used to fetch public/published Google Docs exports
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    // HTML parser to extract text from published /pub Google Docs
    implementation("org.jsoup:jsoup:1.16.1")

    testImplementation(
        "org.junit.jupiter:junit-jupiter-api:$junitVersion",
        "org.mockito:mockito-core:$mockitoVersion",
        "org.mockito:mockito-junit-jupiter:$mockitoVersion",
        "org.junit.jupiter:junit-jupiter-params:$junitVersion",
    )
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

application {
    // Kotlin top-level main is compiled to <fileName>Kt class
    mainClass.set("assesment1.AppKt")
}

tasks.compileKotlin {
}
