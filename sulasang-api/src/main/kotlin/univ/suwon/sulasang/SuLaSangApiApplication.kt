package univ.suwon.sulasang

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class SuLaSangApiApplication

fun main(args: Array<String>) {
    System.setProperty("spring.config.location", "classpath:/domain-config/,classpath:/")
    runApplication<SuLaSangApiApplication>(*args)
}
