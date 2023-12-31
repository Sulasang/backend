package univ.suwon.sulasang.core

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import univ.suwon.sulasang.component.v1.AceEducationCenterCrawler
import univ.suwon.sulasang.component.v1.AmaraenseCrawler

@Component
class CrawlerCore(
    private val aceEducationCenterCrawler: AceEducationCenterCrawler,
    private val amaraenseCrawler: AmaraenseCrawler
) {

    // @Scheduled(cron = "0 0 1 * * SUN")
    fun executeAceEducationCenter() {
        aceEducationCenterCrawler.execute()
    }

    // @Scheduled(cron = "0 0 1 * * SUN")
    fun executeAmaraense() {
        amaraenseCrawler.execute()
    }
}
