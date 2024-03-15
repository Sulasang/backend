package univ.suwon.sulasang.executor

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import univ.suwon.sulasang.component.core.AmarenseCenterCrawler

@Component
class AmarenceCenterCrawlerExecutor(
    private val amarenseCenterCrawler: AmarenseCenterCrawler
) {

    @Scheduled(cron = "0 0 1 * * SUN")
    fun executeAmarenseCenter() {
        amarenseCenterCrawler.execute()
    }
}
