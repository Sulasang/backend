package univ.suwon.sulasang

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.retry.annotation.EnableRetry
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@EnableRetry
@SpringBootApplication
class SuLaSangCrawlerApplication

fun main(args: Array<String>) {
    System.setProperty("spring.config.location", "classpath:/domain-config/,classpath:/")
    runApplication<SuLaSangCrawlerApplication>(*args)
}
