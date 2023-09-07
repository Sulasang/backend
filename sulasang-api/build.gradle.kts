tasks.asciidoctor {
    val snippetsDir by extra { file("build/generated-snippets") }

    inputs.dir(snippetsDir)
    //dependsOn(test)
    dependsOn(tasks.test)

    doFirst {
        delete {
            file("src/main/resources/static/docs")
        }
    }
}

tasks.register("copyHTML", Copy::class) {
    dependsOn(tasks.asciidoctor)
    from(file("build/docs/asciidoc"))
    into(file("src/main/resources/static/docs"))
}

tasks.build {
    dependsOn(tasks.getByName("copyHTML"))
}

tasks.bootJar {
    dependsOn(tasks.asciidoctor)
    dependsOn(tasks.getByName("copyHTML"))
}

dependencies {
    api(project(":sulasang-domain"))

    // Validation Annotation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Spring Rest Docs
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.springframework.restdocs:spring-restdocs-asciidoctor")
    testImplementation("com.ninja-squad:springmockk:3.1.1")
}

application {
    mainClass.set("univ.suwon.sulasang.SuLaSangApiApplication")
}
