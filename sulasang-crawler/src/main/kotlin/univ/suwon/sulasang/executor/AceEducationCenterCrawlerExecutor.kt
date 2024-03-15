package univ.suwon.sulasang.executor

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import univ.suwon.sulasang.component.core.AceEducationCenterCrawler

@Component
class AceEducationCenterCrawlerExecutor(
    private val aceEducationCenterCrawler: AceEducationCenterCrawler,
) {

    @Scheduled(cron = "0 0 1 * * SUN")
    fun executeAceEducationCenter() {
        aceEducationCenterCrawler.execute()
    }
}
