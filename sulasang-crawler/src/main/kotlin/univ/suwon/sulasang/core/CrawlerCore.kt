package univ.suwon.sulasang.core

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import univ.suwon.sulasang.component.AceEducationCenterCrawler
import univ.suwon.sulasang.component.AmaraenseCrawler

@Component
class CrawlerCore(
    private val aceEducationCenterCrawler: AceEducationCenterCrawler,
    private val amaraenseCrawler: AmaraenseCrawler
) {

    @Scheduled(cron = "0 0 9 * * MON")
    fun executeAceEducationCenter() {
        aceEducationCenterCrawler.execute()
    }

    @Scheduled(cron = "0 0 9 * * MON")
    fun executeAmaraenseu() {
        amaraenseCrawler.execute()
    }
}
