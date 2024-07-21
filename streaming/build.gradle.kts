plugins {
    id("java")
}

group = "org.phnm.kfk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.apache.kafka:kafka-clients:3.7.1")
    implementation("org.slf4j:slf4j-api:2.0.13")
    implementation("org.slf4j:slf4j-simple:2.0.13")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.launchdarkly:okhttp-eventsource:3.0.0")
}

tasks.test {
    useJUnitPlatform()
}