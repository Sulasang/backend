package univ.suwon.sulasang

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import univ.suwon.sulasang.core.CrawlerCore
import univ.suwon.sulasang.core.CrawlerCoreV2

@Disabled
@ActiveProfiles("test")
@SpringBootTest(classes = [SuLaSangCrawlerApplication::class])
class CrawlerCoreTest(
    @Autowired val crawlerCore: CrawlerCore,
    @Autowired val crawlerCoreV2: CrawlerCoreV2
) {

    @Test
    fun main() {
        crawlerCore.executeAceEducationCenter()
    }

    @Test
    fun main2() {
        crawlerCoreV2.executeAceEducationCenter()
    }
}