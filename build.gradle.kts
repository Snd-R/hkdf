plugins {
    id("java")
}

group = "at.favre.lib"
version = "2.0.0"

java {
    modularity.inferModulePath = true
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.apache.commons:commons-lang3:3.13.0")
    testImplementation("commons-codec:commons-codec:1.16.0")
    testImplementation("at.favre.lib:bytes:1.6.1")
}

tasks.test {
    useJUnit()
}