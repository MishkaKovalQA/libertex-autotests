plugins {
    id("java")
    id("io.freefair.lombok") version "8.11"
    id("io.qameta.allure") version "2.12.0"
}

group = "com.libertex.qa"
version = "1.0-SNAPSHOT"

allure {
    report {
        version.set("2.24.0")
    }
    adapter {
        aspectjWeaver.set(true)
        frameworks {
            junit5 {
                adapterVersion.set("2.24.0")
            }
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("com.codeborne:selenide:7.6.1")
    implementation("io.qameta.allure:allure-selenide:2.29.1")

    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
    testImplementation("ch.qos.logback:logback-classic:1.5.16")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
    testImplementation("org.assertj:assertj-core:3.27.2")
    implementation("com.github.javafaker:javafaker:1.0.2")

    implementation("org.aeonbits.owner:owner:1.0.12")
}

tasks.withType<Test> {
    systemProperty("user.timezone", "GMT+2")
    systemProperty("user.language", "en")
    systemProperty("user.country", "ME")
    useJUnitPlatform {
        systemProperty("junit.jupiter.extensions.autodetection.enabled", "true")

        val includedTags = project.properties["includeTags"] as String?
        val excludedTags = project.properties["excludeTags"] as String?
        if (!includedTags.isNullOrBlank()) {
            includeTags(includedTags)
        }
        if (!excludedTags.isNullOrBlank()) {
            excludeTags(excludedTags)
        }
    }

    ignoreFailures = true
    systemProperties(getSystemPropertiesAsMap())
/*    systemProperty("junit.jupiter.execution.parallel.enabled", "true")
    systemProperty("junit.jupiter.execution.parallel.mode.default", "concurrent")
    systemProperty("junit.jupiter.execution.parallel.mode.classes.default", "concurrent")
    systemProperty("junit.jupiter.execution.parallel.config.strategy", "fixed")
    systemProperty("junit.jupiter.execution.parallel.config.fixed.parallelism", "5")*/

    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

fun getSystemPropertiesAsMap(): Map<String, String> {
    val propertiesMap: Map<String, String> = System.getProperties().entries
        .filterIsInstance<Map.Entry<String, String>>()
        .associate { it.key to it.value }
    return propertiesMap
}