dependencies {
    api(project(":domain"))

    // JSoup Dependency
    implementation("org.jsoup:jsoup:1.15.4")

    // Spring Retry
    implementation("org.springframework.retry:spring-retry")
}

application {
    mainClass.set("univ.suwon.shorts.ShortsCrawlerApplicationKt")
}
