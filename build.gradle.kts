import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.3"
    id("io.spring.dependency-management") version "1.1.3"
    id("application")
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22"
    kotlin("kapt") version "1.6.21"

}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

application {
    mainClass.set("univ.suwon.sulasang.SuLaSangApiApplicationKt")
}

allprojects {
    group = "univ.suwon"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "kotlin")
    apply(plugin = "org.asciidoctor.jvm.convert")
    apply(plugin = "java-library")
    apply(plugin = "kotlin-jpa")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "application")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web:3.1.0")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
        implementation("io.github.microutils:kotlin-logging:3.0.5")

        implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.10")
        testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.0")

        // SpringMockk
        testImplementation("com.ninja-squad:springmockk:4.0.2")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}
