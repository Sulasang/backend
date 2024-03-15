package univ.suwon.sulasang

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import univ.suwon.sulasang.executor.AmarenceCenterCrawlerExecutor

@Disabled
@ActiveProfiles("test")
@SpringBootTest(classes = [SuLaSangCrawlerApplication::class])
class AmarenseCenterCrawlerExecutorTest(
    @Autowired val amarenceCenterCrawlerExecutor: AmarenceCenterCrawlerExecutor,
) {

    @Test
    fun main() {
        amarenceCenterCrawlerExecutor.executeAmarenseCenter()
    }
}
