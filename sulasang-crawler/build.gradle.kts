dependencies {
    api(project(":sulasang-domain"))

    // JSoup Dependency
    implementation("org.jsoup:jsoup:1.15.4")

    // Spring Retry
    implementation("org.springframework.retry:spring-retry")
}

application {
    mainClass.set("univ.suwon.sulasang.SuLaSangCrawlerApplication")
}
