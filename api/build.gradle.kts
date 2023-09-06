dependencies {
    api(project(":domain"))

    // Validation Annotation
    implementation("org.springframework.boot:spring-boot-starter-validation")
}

application {
    mainClass.set("com.mashup.shorts.ShortsApiApplicationKt")
}
