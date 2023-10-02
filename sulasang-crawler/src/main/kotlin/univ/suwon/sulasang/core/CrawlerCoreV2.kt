package univ.suwon.sulasang.core

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import univ.suwon.sulasang.component.v1.AceEducationCenterCrawler
import univ.suwon.sulasang.component.v1.AmaraenseCrawler
import univ.suwon.sulasang.component.v2.AceEducationCenterCrawlerV2
import univ.suwon.sulasang.component.v2.AmaraenseCrawlerV2

@Component
class CrawlerCoreV2(
    private val aceEducationCenterCrawler: AceEducationCenterCrawlerV2,
    private val amaraenseCrawler: AmaraenseCrawlerV2
) {

    @Scheduled(cron = "0 0 1 * * SUN")
    fun executeAceEducationCenter() {
        aceEducationCenterCrawler.execute()
    }

    @Scheduled(cron = "0 0 1 * * SUN")
    fun executeAmaraense() {
        amaraenseCrawler.execute()
    }
}
